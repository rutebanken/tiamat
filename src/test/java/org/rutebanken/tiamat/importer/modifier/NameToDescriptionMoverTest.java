package org.rutebanken.tiamat.importer.modifier;

import org.junit.Test;
import org.rutebanken.tiamat.importer.modifier.NameToDescriptionMover;
import org.rutebanken.tiamat.model.EmbeddableMultilingualString;
import org.rutebanken.tiamat.model.StopPlace;

import static org.assertj.core.api.Assertions.assertThat;

public class NameToDescriptionMoverTest {

    private NameToDescriptionMover nameToDescriptionMover = new NameToDescriptionMover();

    @Test
    public void kvernstuen() {
        expectNameAndDescriptionToBeMoved("Dam (i Kvernstuveien)", "Dam", "i Kvernstuveien");
    }

    @Test
    public void strømsveien() {
        expectNameAndDescriptionToBeMoved("Alfaset (i Strømsveien)", "Alfaset", "i Strømsveien");
    }

    @Test
    public void linderudsletta() {
        expectNameAndDescriptionToBeMoved("Linderudsletta (nordsiden)", "Linderudsletta", "nordsiden");
    }

    private void expectNameAndDescriptionToBeMoved(String originalName, String expectedName, String expectedDescription) {
        StopPlace stopPlace = new StopPlace(new EmbeddableMultilingualString(originalName));
        nameToDescriptionMover.updateEntityDescriptionFromName(stopPlace);
        assertThat(stopPlace.getName().getValue()).isEqualTo(expectedName);
        assertThat(stopPlace.getDescription().getValue()).isEqualTo(expectedDescription);
    }
}