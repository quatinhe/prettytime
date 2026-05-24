package org.ocpsoft.prettytime;

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.units.Minute;

import static org.junit.Assert.assertEquals;

// We're using C1 and c2 as the causes and E1 and E2 as the effects (everything is more detailed in the report)


public class GetQuantityRoundedCauseEffectTest {

    private DurationImpl duration;

    @Before
    public void setUp() {
        duration = new DurationImpl();
        duration.setUnit(new Minute()); 
    }

    // 1 - C1=true, C2=true: E1 (rounds up, ceck the report if you do
    @Test
    public void ce_TC1_deltaPresent_thresholdExceedsTolerance_roundsUp() {
        duration.setQuantity(5);
        duration.setDelta(36_000L);
        assertEquals(6, duration.getQuantityRounded(50));
    }

    // 2 - C1=true, C2=false → E2 (no round up)
    @Test
    public void ce_TC2_deltaPresent_thresholdBelowTolerance_noRoundUp() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(5, duration.getQuantityRounded(50));
    }

    // 3 - C1=false (C2 irrelevant) → E2 (no round up)
    @Test
    public void ce_TC3_noDelta_noRoundUp() {
        duration.setQuantity(5);
        duration.setDelta(0L);
        assertEquals(5, duration.getQuantityRounded(50));
    }
}
