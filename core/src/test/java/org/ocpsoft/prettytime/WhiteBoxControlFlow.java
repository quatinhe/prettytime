package org.ocpsoft.prettytime;

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Hour;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DurationImplEqualsControlFlowTest
{
    private DurationImpl base;

    @Before
    public void setUp()
    {
        base = new DurationImpl();
        base.setUnit(new Minute());
        base.setQuantity(5);
        base.setDelta(1000L);
    }

    // primeiro path
    // this == obj: return true immediately
    @Test
    public void cf_1_sameRef_returnsTrue()
    {
        assertTrue(base.equals(base));
    }

    // second path
    // obj is null, return false
    @Test
    public void cf_2_nullArg_returnsFalse()
    {
        assertFalse(base.equals(null));
    }

    // third path
    // obj is non-null but not a DurationImpl, return false
    @Test
    public void cf_3_otherClass_returnsFalse()
    {
        assertFalse(base.equals("not a DurationImpl"));
    }

    // 4th path
    // Same class, but other.delta != this.delta, return false
    @Test
    public void cf_4_deltaDontMatch_returnsFalse()
    {
        DurationImpl other = new DurationImpl();
        other.setUnit(new Minute());
        other.setQuantity(5);
        other.setDelta(9999L);          // differs from base.delta = 1000
        assertFalse(base.equals(other));
    }

    // 5h path
    // Same class and delta, but other.quantity != this.quantity, return false
    @Test
    public void cf_5_qttyDifferent_returnsFalse()
    {
        DurationImpl other = new DurationImpl();
        other.setUnit(new Minute());
        other.setQuantity(99);          // differs from base.quantity = 5
        other.setDelta(1000L);  
        assertFalse(base.equals(other));
    }

    // 6th path
    // All the othr conditions are false, objects.equals(unit, other.unit) = true
    // Covers the only remaining node and confirms the final return is reached
    @Test
    public void cf_6_allFildsEqual_retrunsTrue()
    {
        DurationImpl other = new DurationImpl();
        other.setUnit(new Minute());    // same unit class as base
        other.setQuantity(5);
        other.setDelta(1000L);
        assertTrue(base.equals(other));
    }


}
