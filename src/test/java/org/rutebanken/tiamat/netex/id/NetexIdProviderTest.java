package org.rutebanken.tiamat.netex.id;

import org.junit.Test;
import org.rutebanken.tiamat.model.TopographicPlace;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class NetexIdProviderTest {

    @Test
    public void claimValidId() {

        TopographicPlace topographicPlace = new TopographicPlace();
        topographicPlace.setNetexId("KVE:"+TopographicPlace.class.getSimpleName()+":1");

        GaplessIdGeneratorService gaplessIdGeneratorService = mock(GaplessIdGeneratorService.class);

        NetexIdProvider netexIdProvider = new NetexIdProvider(gaplessIdGeneratorService, new ValidPrefixList(new String[] {"KVE"}));

        netexIdProvider.claimId(topographicPlace);
        verify(gaplessIdGeneratorService, times(1)).getNextIdForEntity(TopographicPlace.class.getSimpleName(),1L);
    }
}