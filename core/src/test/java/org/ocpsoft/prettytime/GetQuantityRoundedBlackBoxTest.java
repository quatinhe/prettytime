package org.ocpsoft.prettytime;

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.units.Minute;

import static org.junit.Assert.assertEquals;

public class GetQuantityRoundedBlackBoxTest {

    private DurationImpl duration;

    @Before //run before each method teste
    public void setUp() {
        duration = new DurationImpl(); // objeto duração para dar hold de tudo
        duration.setUnit(new Minute()); // in this weak tests the unit to be compared will be 1 min
    }

        //quantity=-3, delta=0 and threshold=0% not > 50% os should stay 3    
    @Test
    public void weak_W1_negativeQuantity_zeroDelta() {
        duration.setQuantity(-3);
        duration.setDelta(0);
        assertEquals(3, duration.getQuantityRounded(50));
    }

    //quantity=0, delta=36000 and threshold=60% > 50%, so 0+1 = 1
    @Test
    public void weak_W2_zeroQuantity_deltaPresent_roundsUp() {
        duration.setQuantity(0);
        duration.setDelta(36_000L);
        assertEquals(1, duration.getQuantityRounded(50));
    }

    //quantity=5, delta=30000 and threshold=50% not > 50%, so should stay 5
    @Test
    public void weak_W3_positiveQuantity_deltaPresent_noRoundUp() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(5, duration.getQuantityRounded(50));
    }

    // ---------------- Strong equivalence tets ---

    // quantity=-3, delta=0 
    @Test
    public void strong_S1_negativeQuantity_zeroDelta() {
        duration.setQuantity(-3);
        duration.setDelta(0);
        assertEquals(3, duration.getQuantityRounded(50));
    }

    // quantity=-3, delta=36000, threshold=60% > 50 
    @Test
    public void strong_S2_negativeQuantity_nonZeroDelta_roundsUp() {
        duration.setQuantity(-3);
        duration.setDelta(36_000L);
        assertEquals(4, duration.getQuantityRounded(50));
    }

    // quantity=-3, delta=30000, threshold=50% not > 50
    @Test
    public void strong_S3_negativeQuantity_nonZeroDelta_noRoundUp() {
        duration.setQuantity(-3);
        duration.setDelta(30_000L);
        assertEquals(3, duration.getQuantityRounded(50));
    }

    // quantity=0, delta=0
    @Test
    public void strong_S4_zeroQuantity_zeroDelta() {
        duration.setQuantity(0);
        duration.setDelta(0);
        assertEquals(0, duration.getQuantityRounded(50));
    }

    // quantity=0, delta=36000, threshold=60% > 50
    @Test
    public void strong_S5_zeroQuantity_nonZeroDelta_roundsUp() {
        duration.setQuantity(0);
        duration.setDelta(36_000L);
        assertEquals(1, duration.getQuantityRounded(50));
    }

    // quantity=0, delta=30000, threshold=50% not > 50
    @Test
    public void strong_S6_zeroQuantity_nonZeroDelta_noRoundUp() {
        duration.setQuantity(0);
        duration.setDelta(30_000L);
        assertEquals(0, duration.getQuantityRounded(50));
    }

    // quantity=5, delta=0
    @Test
    public void strong_S7_positiveQuantity_zeroDelta() {
        duration.setQuantity(5);
        duration.setDelta(0);
        assertEquals(5, duration.getQuantityRounded(50));
    }

    // quantity=5, delta=36000, threshold=60% > 50
    @Test
    public void strong_S8_positiveQuantity_nonZeroDelta_roundsUp() {
        duration.setQuantity(5);
        duration.setDelta(36_000L);
        assertEquals(6, duration.getQuantityRounded(50));
    }

    // quantity=5, delta=30000, threshold=50% not > 50
    @Test
    public void strong_S9_positiveQuantity_nonZeroDelta_noRoundUp() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(5, duration.getQuantityRounded(50));
    }
}
