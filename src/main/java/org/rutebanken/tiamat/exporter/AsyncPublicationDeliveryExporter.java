/*
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *   https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */

package org.rutebanken.tiamat.exporter;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.rutebanken.tiamat.exporter.async.ExportJobWorker;
import org.rutebanken.tiamat.exporter.params.ExportParams;
import org.rutebanken.tiamat.model.job.ExportJob;
import org.rutebanken.tiamat.model.job.JobStatus;
import org.rutebanken.tiamat.netex.validation.NetexXmlReferenceValidator;
import org.rutebanken.tiamat.service.BlobStoreService;
import org.rutebanken.tiamat.service.ExportJobsLookupService;
import org.rutebanken.tiamat.time.ExportTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.rutebanken.tiamat.rest.netex.publicationdelivery.AsyncExportResource.ASYNC_JOB_PATH;

@Service
public class AsyncPublicationDeliveryExporter {

    private static final Logger logger = LoggerFactory.getLogger(AsyncPublicationDeliveryExporter.class);

    private static final ExecutorService exportService = Executors.newFixedThreadPool(3, new ThreadFactoryBuilder()
            .setNameFormat("exporter-%d").build());

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("YYYYMMdd-HHmmssSSS");
    private static final DateTimeFormatter DATE_TIME_FORMATTER2 = DateTimeFormatter.ofPattern("YYYYMMddHHmmssSSS");


    private final BlobStoreService blobStoreService;

    private final StreamingPublicationDelivery streamingPublicationDelivery;

    private final NetexXmlReferenceValidator netexXmlReferenceValidator;

    private final ExportTimeZone exportTimeZone;

    private final String localExportPath;

    private final ExportJobsLookupService exportJobsLookupService;

    @Autowired
    public AsyncPublicationDeliveryExporter(BlobStoreService blobStoreService,
                                            @Qualifier("asyncStreamingPublicationDelivery") StreamingPublicationDelivery streamingPublicationDelivery,
                                            NetexXmlReferenceValidator netexXmlReferenceValidator, ExportTimeZone exportTimeZone,
                                            @Value("${async.export.path:/deployments/data/}") String localExportPath, ExportJobsLookupService exportJobsLookupService) {
        this.blobStoreService = blobStoreService;
        this.streamingPublicationDelivery = streamingPublicationDelivery;
        this.netexXmlReferenceValidator = netexXmlReferenceValidator;
        this.exportTimeZone = exportTimeZone;
        this.localExportPath = localExportPath;
        this.exportJobsLookupService = exportJobsLookupService;

        File exportFolder = new File(localExportPath);
        if(!exportFolder.exists() && !exportFolder.mkdirs()) {
            throw new RuntimeException("Cannot find or create export directory from path: " + localExportPath +
                    ". Please create the directory with correct permissions, or configure a different path with the property async.export.path");
        }
        if(!exportFolder.canWrite()) {
            throw new RuntimeException("Cannot write to path: " + localExportPath +
                    ". Please create the directory with correct permissions, or configure a different path with the property async.export.path");
        }
        logger.info("Verified local export path {}", localExportPath);
    }

    /**
     * Start export job with upload to google cloud storage
     * @param exportParams search params for stops
     * @return export job with information about the started process
     */
    public ExportJob startExportJob(ExportParams exportParams) {

        ExportJob exportJob = new ExportJob(JobStatus.PROCESSING);
        final Instant now = Instant.now();
        exportJob.setId(Long.valueOf(now.atZone(exportTimeZone.getDefaultTimeZoneId()).format(DATE_TIME_FORMATTER2)));
        exportJob.setStarted(now);
        exportJob.setExportParams(exportParams);
        exportJob.setSubFolder(generateSubFolderName());

        exportJobsLookupService.addExportJob(exportJob);
        String fileNameWithoutExtention = createFileNameWithoutExtention(exportJob.getStarted());
        exportJob.setFileName(fileNameWithoutExtention + ".zip");

        ExportJobWorker exportJobWorker = new ExportJobWorker(exportJob, streamingPublicationDelivery, localExportPath, fileNameWithoutExtention, blobStoreService, exportJobsLookupService, netexXmlReferenceValidator);
        exportService.submit(exportJobWorker);
        logger.info("Returning started export job {}", exportJob);
        setJobUrl(exportJob);
        return exportJob;
    }

    public String createFileNameWithoutExtention(Instant started) {
        return "tiamat-export-" + started.atZone(exportTimeZone.getDefaultTimeZoneId()).format(DATE_TIME_FORMATTER);
    }

    public ExportJob getExportJob(long exportJobId) {

        Optional<ExportJob> exportJob = exportJobsLookupService.getExportJob(exportJobId);
        return exportJob.map(this::setJobUrl).orElse(null);
    }

    public InputStream getJobFileContent(ExportJob exportJob) {
        return blobStoreService.download(exportJob.getSubFolder() + "/" + exportJob.getFileName());
    }

    public Collection<ExportJob> getJobs() {

        return exportJobsLookupService.getExportJob2List();
    }

    private ExportJob setJobUrl(ExportJob exportJobWithId) {
        exportJobWithId.setJobUrl(ASYNC_JOB_PATH + "/" + exportJobWithId.getId());
        return exportJobWithId;
    }

    private String generateSubFolderName() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        String gcpSubfolder = localDateTime.getYear() + "-" + String.format("%02d", localDateTime.getMonthValue());
        return gcpSubfolder;
    }
}
