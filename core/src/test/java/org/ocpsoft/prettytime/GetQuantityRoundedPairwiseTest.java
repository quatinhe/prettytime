package org.ocpsoft.prettytime;

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.units.Minute;

import static org.junit.Assert.assertEquals;

public class GetQuantityRoundedPairwiseTest {

    private DurationImpl duration;

    @Before //run before each method teste
    public void setUp() {
        duration = new DurationImpl(); // objeto duração para dar hold de tudo
        duration.setUnit(new Minute()); // in this weak tests the unit to be compared will be 1 min
    }

    //quantity=-3, delta=0 quantity should stay 3
    @Test
    public void pairwise1_negativeQuantity_zeroDelta() {
        duration.setQuantity(-3);
        duration.setDelta(0);
        assertEquals(3, duration.getQuantityRounded(0));
    }

    //quantity=0, delta=36000 and threshold > 50% os should become 1
    @Test
    public void pairwise2_negativeQuantity_zeroDelta() {
        duration.setQuantity(0);
        duration.setDelta(36000);
        assertEquals(1, duration.getQuantityRounded(50));
    }

    //quantity=5, delta=0 quantity should stay 5
    @Test
    public void pairwise3_negativeQuantity_zeroDelta() {
        duration.setQuantity(5);
        duration.setDelta(0);
        assertEquals(5, duration.getQuantityRounded(0));
    }

    //quantity=-3, delta=36000 and threshold < 50% os should stay 3
    @Test
    public void parwise4_negativeQuantity_zeroDelta() {
        duration.setQuantity(-3);
        duration.setDelta(25000);
        assertEquals(3, duration.getQuantityRounded(50));
    }

    //quantity=0, delta=0 quantity should stay 0
    @Test
    public void pairwise5_negativeQuantity_zeroDelta() {
        duration.setQuantity(0);
        duration.setDelta(0);
        assertEquals(0, duration.getQuantityRounded(50));
    }

    //quantity=5, delta=36000 and threshold < 50% os should stay 5
    @Test
    public void pairwise6_negativeQuantity_zeroDelta() {
        duration.setQuantity(5);
        duration.setDelta(25000);
        assertEquals(5, duration.getQuantityRounded(50));
    }

}
