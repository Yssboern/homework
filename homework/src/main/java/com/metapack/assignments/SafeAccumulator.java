package com.metapack.assignments;

/**
 * Fail-fast overflow-safe implementation of {@link Accumulator}
 * <p>
 * Does not allow totalSum to exceed Integer min or max boundaries.
 * Throws {@link ArithmeticException} if sum of accumulated values exceed Integer
 * or when accumulation would exceed Integer for totalSum
 */
public class SafeAccumulator implements Accumulator {

    private long totalSum = 0;

    /**
     * {@inheritDoc}
     * <p>
     * Throws ArithmeticException if sum of accumulated values exceed Integer
     * or when adding accumulated values to totalSum would exceed Integer
     */
    @Override
    public int accumulate(int... values) {

        long sum = 0;
        for (int value : values) {
            sum += value;
        }

        if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
            throw new ArithmeticException("Sum of provided values exceeds Integer boundaries!");
        }

        long tempTotal = sum + totalSum;
        if (tempTotal < Integer.MIN_VALUE || tempTotal > Integer.MAX_VALUE) {
            throw new ArithmeticException("Operation exceeds Integer boundaries for total sum!");
        }

        totalSum = tempTotal;
        return (int) sum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotal() {
        return (int) totalSum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        totalSum = 0;
    }

}
