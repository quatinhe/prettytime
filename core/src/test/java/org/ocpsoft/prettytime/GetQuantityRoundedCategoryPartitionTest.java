package org.ocpsoft.prettytime;

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.units.Minute;

import static org.junit.Assert.assertEquals;

public class GetQuantityRoundedCategoryPartitionTest {

    private DurationImpl duration;

    @Before
    public void setUp() {
        duration = new DurationImpl();
        duration.setUnit(new Minute());
    }

    // quantity=5, delta=0 -> branch skipped, returns abs(quantity)=5
    @Test
    public void test2_deltaZero() {
        duration.setQuantity(5);
        duration.setDelta(0);
        assertEquals(5, duration.getQuantityRounded(50));
    }

    // quantity=-5, delta=-60000, threshold=100, tolerance=-1 -> expected=6
    @Test
    public void test4_negativeQty_minus60000_tol_negative() {
        duration.setQuantity(-5);
        duration.setDelta(-60000);
        assertEquals(6, duration.getQuantityRounded(-1));
    }

    // quantity=-5, delta=-60000, threshold=100, tolerance=0 -> expected=6
    @Test
    public void test5_negativeQty_minus60000_tol_zero() {
        duration.setQuantity(-5);
        duration.setDelta(-60000);
        assertEquals(6, duration.getQuantityRounded(0));
    }

    // quantity=-5, delta=-60000, threshold=100, tolerance=99 -> expected=6
    @Test
    public void test6_negativeQty_minus60000_lt_threshold() {
        duration.setQuantity(-5);
        duration.setDelta(-60000);
        assertEquals(6, duration.getQuantityRounded(99));
    }

    // quantity=-5, delta=-15000, threshold=25, tolerance=-1 -> expected=6
    @Test
    public void test7_negativeQty_delta_negative_tol_negative() {
        duration.setQuantity(-5);
        duration.setDelta(-15000);
        assertEquals(6, duration.getQuantityRounded(-1));
    }

    // quantity=-5, delta=-15000, threshold=25, tolerance=0 -> expected=6
    @Test
    public void test8_negativeQty_delta_negative_tol_zero() {
        duration.setQuantity(-5);
        duration.setDelta(-15000);
        assertEquals(6, duration.getQuantityRounded(0));
    }

    // quantity=-5, delta=-15000, threshold=25, tolerance=24 -> expected=6
    @Test
    public void test9_negativeQty_delta_negative_lt_threshold() {
        duration.setQuantity(-5);
        duration.setDelta(-15000);
        assertEquals(6, duration.getQuantityRounded(24));
    }

    // quantity=-5, delta=-15000, threshold=25, tolerance=26 -> expected=5
    @Test
    public void test10_negativeQty_delta_negative_gt_threshold() {
        duration.setQuantity(-5);
        duration.setDelta(-15000);
        assertEquals(5, duration.getQuantityRounded(26));
    }

    // quantity=-5, delta=15000, threshold=25, tolerance=-1 -> expected=6
    @Test
    public void test11_negativeQty_delta_positive_tol_negative() {
        duration.setQuantity(-5);
        duration.setDelta(15000);
        assertEquals(6, duration.getQuantityRounded(-1));
    }

    // quantity=-5, delta=15000, threshold=25, tolerance=0 -> expected=6
    @Test
    public void test12_negativeQty_delta_positive_tol_zero() {
        duration.setQuantity(-5);
        duration.setDelta(15000);
        assertEquals(6, duration.getQuantityRounded(0));
    }

    // quantity=-5, delta=15000, threshold=25, tolerance=24 -> expected=6
    @Test
    public void test13_negativeQty_delta_positive_lt_threshold() {
        duration.setQuantity(-5);
        duration.setDelta(15000);
        assertEquals(6, duration.getQuantityRounded(24));
    }

    // quantity=-5, delta=15000, threshold=25, tolerance=26 -> expected=5
    @Test
    public void test14_negativeQty_delta_positive_gt_threshold() {
        duration.setQuantity(-5);
        duration.setDelta(15000);
        assertEquals(5, duration.getQuantityRounded(26));
    }

    // quantity=-5, delta=60000, threshold=100, tolerance=-1 -> expected=6
    @Test
    public void test15_negativeQty_plus60000_tol_negative() {
        duration.setQuantity(-5);
        duration.setDelta(60000);
        assertEquals(6, duration.getQuantityRounded(-1));
    }

    // quantity=-5, delta=60000, threshold=100, tolerance=0 -> expected=6
    @Test
    public void test16_negativeQty_plus60000_tol_zero() {
        duration.setQuantity(-5);
        duration.setDelta(60000);
        assertEquals(6, duration.getQuantityRounded(0));
    }

    // quantity=-5, delta=60000, threshold=100, tolerance=99 -> expected=6
    @Test
    public void test17_negativeQty_plus60000_lt_threshold() {
        duration.setQuantity(-5);
        duration.setDelta(60000);
        assertEquals(6, duration.getQuantityRounded(99));
    }

    // quantity=0, delta=-60000, threshold=100, tolerance=-1 -> expected=1
    @Test
    public void test18_zeroQty_minus60000_tol_negative() {
        duration.setQuantity(0);
        duration.setDelta(-60000);
        assertEquals(1, duration.getQuantityRounded(-1));
    }

    // quantity=0, delta=-60000, threshold=100, tolerance=0 -> expected=1
    @Test
    public void test19_zeroQty_minus60000_tol_zero() {
        duration.setQuantity(0);
        duration.setDelta(-60000);
        assertEquals(1, duration.getQuantityRounded(0));
    }

    // quantity=0, delta=-60000, threshold=100, tolerance=99 -> expected=1
    @Test
    public void test20_zeroQty_minus60000_lt_threshold() {
        duration.setQuantity(0);
        duration.setDelta(-60000);
        assertEquals(1, duration.getQuantityRounded(99));
    }

    // quantity=0, delta=-15000, threshold=25, tolerance=-1 -> expected=1
    @Test
    public void test21_zeroQty_delta_negative_tol_negative() {
        duration.setQuantity(0);
        duration.setDelta(-15000);
        assertEquals(1, duration.getQuantityRounded(-1));
    }
}
