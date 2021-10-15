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

package org.rutebanken.tiamat.model.job;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.rutebanken.tiamat.exporter.params.ExportParams;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.Instant;

import static org.rutebanken.tiamat.rest.netex.publicationdelivery.AsyncExportResource.ASYNC_JOB_PATH;


@XmlRootElement
@ApiModel(description = "Export job model")
public class ExportJob2 {


    @ApiModelProperty("Unique id for the entity")
    private Long id;

    @Deprecated
    @ApiModelProperty("JobUrl property  is deprecated")
    private String jobUrl;

    @ApiModelProperty("File name of exported file")
    private String fileName;

    private String subFolder;

    private String message;

    private Instant started;

    private Instant finished;

    @ApiModelProperty("Job status")
    private JobStatus status;

    private ExportParams exportParams;

    public ExportJob2() {
    }

    public ExportJob2(JobStatus jobStatus) {
        status = jobStatus;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("status", status)
                .add("jobUrl", getJobUrl())
                .add("fileName", fileName)
                .add("subFolder", subFolder)
                .add("started", started)
                .add("finished", finished)
                .add("message", message)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobUrl() {
        return ASYNC_JOB_PATH + '/' + getId();
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getStarted() {
        return started;
    }

    public void setStarted(Instant started) {
        this.started = started;
    }

    public Instant getFinished() {
        return finished;
    }

    public void setFinished(Instant finished) {
        this.finished = finished;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExportParams getExportParams() {
        return exportParams;
    }

    public void setExportParams(ExportParams exportParams) {
        this.exportParams = exportParams;
    }

    public String getSubFolder() {
        return subFolder;
    }

    public void setSubFolder(String subFolder) {
        this.subFolder = subFolder;
    }
}
