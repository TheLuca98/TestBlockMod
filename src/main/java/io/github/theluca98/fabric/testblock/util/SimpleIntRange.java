package io.github.theluca98.fabric.testblock.util;

public class SimpleIntRange {

    private final int minValue;
    private final int maxValue;

    public SimpleIntRange(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int coerce(int value) {
        return Math.max(Math.min(value, maxValue), minValue);
    }

}
