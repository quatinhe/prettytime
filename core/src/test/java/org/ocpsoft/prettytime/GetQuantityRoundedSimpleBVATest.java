package org.ocpsoft.prettytime;

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.units.Minute;

import static org.junit.Assert.assertEquals;

// simple boundary value analyzes (every single test is documented on the report on the expected results)
public class GetQuantityRoundedSimpleBVATest {

    private DurationImpl duration;

    @Before
    public void setUp() {
        duration = new DurationImpl();
        duration.setUnit(new Minute());
    }

// Nominal 
    // quantity=5, delta=30000, tolerance=50
    @Test
    public void shouldReturnExactQuantityWithNominalValues_0() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(5, duration.getQuantityRounded(50));
    }

    // ------------------- quatity boundary tests ----------------

    @Test
    public void shouldHandleExtremeNegativeQuantityBoundary_1() {
        duration.setQuantity(-10_000);
        duration.setDelta(30_000L);
        assertEquals(10000, duration.getQuantityRounded(50));
    }

    @Test
    public void shouldHandleJustAboveNegativeQuantityBoundary_2() {
        duration.setQuantity(-9999);
        duration.setDelta(30_000L);
        assertEquals(9999, duration.getQuantityRounded(50));
    }

    @Test
    public void shouldHandleJustBelowMaximumQuantityBoundary_3() {
        duration.setQuantity(9999);
        duration.setDelta(30_000L);
        assertEquals(9999, duration.getQuantityRounded(50));
    }

    @Test
    public void shouldHandleMaximumQuantityBoundary_4() {
        duration.setQuantity(10_000);
        duration.setDelta(30_000L);
        assertEquals(10000, duration.getQuantityRounded(50));
    }

    // ---------------- delta boundary tests -------------------

    @Test
    public void shouldNotRoundUpWhenDeltaIsZero_5() {
        duration.setQuantity(5);
        duration.setDelta(0L);
        assertEquals(5, duration.getQuantityRounded(50));
    }

    @Test
    public void shouldNotRoundUpWithMinimalDelta_6() {
        duration.setQuantity(5);
        duration.setDelta(1L);
        assertEquals(5, duration.getQuantityRounded(50));
    }

    @Test
    public void shouldRoundUpWhenDeltaIsJustBelowThreshold_7() {
        duration.setQuantity(5);
        duration.setDelta(59_999L);
        assertEquals(6, duration.getQuantityRounded(50));
    }

    @Test
    public void shouldRoundUpWithMaximumDelta_8() {
        duration.setQuantity(5);
        duration.setDelta(60_000L);
        assertEquals(6, duration.getQuantityRounded(50));
    }

    // --------tolerance boundary tests -------------------

    @Test
    public void shouldRoundUpWhenToleranceIsZero_9() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(6, duration.getQuantityRounded(0));
    }

    @Test
    public void shouldRoundUpWithMinimalTolerance_10() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(6, duration.getQuantityRounded(1));
    }

    @Test
    public void shouldNotRoundUpWhenToleranceIsJustBelowMax_11() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(5, duration.getQuantityRounded(99));
    }

    @Test
    public void shouldNotRoundUpWithMaximumTolerance_12() {
        duration.setQuantity(5);
        duration.setDelta(30_000L);
        assertEquals(5, duration.getQuantityRounded(100));
    }
}