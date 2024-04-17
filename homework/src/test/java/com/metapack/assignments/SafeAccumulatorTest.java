package com.metapack.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SafeAccumulatorTest {

    @Test
    void testAccumulate() {
        var acu = new SafeAccumulator();

        int[] values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //sum=55

        assertEquals(55, acu.accumulate(values), "accumulate() must return correct sum of values in provided array");
        assertEquals(0, acu.accumulate(), "accumulate() should return 0 for no provided values");
        assertEquals(33, acu.accumulate(33), "accumulate() should return value equal to provided single value");
        assertEquals(2, acu.accumulate(1, -2, 3), "accumulate() should return sum of all accumulated values");
    }

    @Test
    void testGetTotal() {
        var acu = new SafeAccumulator();

        assertEquals(0, acu.getTotal(), "getTotal() should return 0 for newly created accumulator");

        acu.accumulate(55);
        assertEquals(55, acu.getTotal(), "getTotal() should return value equal to sum of accumulated values");

        acu.accumulate(1, 2, 3, -1);
        assertEquals(60, acu.getTotal(), "getTotal() should return value equal to sum of all accumulated values");
    }

    @Test
    void testReset() {
        //given
        var acu = new SafeAccumulator();
        acu.accumulate(1, 2, 3, -1);
        assertNotEquals(0, acu.getTotal(), "total cannot be 0 here!");
        //when
        acu.reset();
        //then
        assertEquals(0, acu.getTotal(), "getTotal() should return 0 after .reset() was invoked");
    }

    @Test
    void accumulateThrowsExceptionWhenAccumulatedValuesExceedIntegerMax() {
        var acu = new SafeAccumulator();
        Exception exception = assertThrows(ArithmeticException.class, () -> acu.accumulate(1, Integer.MAX_VALUE));

        var expectedMsg = "Sum of provided values exceeds Integer boundaries!";
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void accumulateThrowsExceptionWhenAccumulatedValuesExceedIntegerMin() {
        var acu = new SafeAccumulator();
        Exception exception = assertThrows(ArithmeticException.class, () -> acu.accumulate(-1, Integer.MIN_VALUE));

        var expectedMsg = "Sum of provided values exceeds Integer boundaries!";
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void accumulateThrowsExceptionWhenTotalWouldExceedIntegerMax() {
        var acu = new SafeAccumulator();
        acu.accumulate(Integer.MAX_VALUE);
        Exception exception = assertThrows(ArithmeticException.class, () -> acu.accumulate(1));

        var expectedMsg = "Operation exceeds Integer boundaries for total sum!";
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void accumulateThrowsExceptionWhenTotalWouldExceedIntegerMin() {
        var acu = new SafeAccumulator();
        acu.accumulate(Integer.MIN_VALUE);

        Exception exception = assertThrows(ArithmeticException.class, () -> acu.accumulate(-1));

        var expectedMsg = "Operation exceeds Integer boundaries for total sum!";
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void testAccumulatorWithMaxBoundaryValue() {
        var acu = new SafeAccumulator();
        var val = Integer.MAX_VALUE - 1;
        assertEquals(Integer.MAX_VALUE, acu.accumulate(val, 1), "accumulate() should allow values with sum equal to Integer.MAX_VALUE");
        assertEquals(Integer.MAX_VALUE, acu.getTotal(), "getTotal() should return Integer.MAX_VALUE");
    }

    @Test
    void testAccumulatorWithMinBoundaryValue() {
        var acu = new SafeAccumulator();
        var val = Integer.MIN_VALUE + 1;
        assertEquals(Integer.MIN_VALUE, acu.accumulate(val, -1), "accumulate() should allow value with sum equal to Integer.MIN_VALUE");
        assertEquals(Integer.MIN_VALUE, acu.getTotal(), "getTotal() should return Integer.MIN_VALUE");
    }

}