package ru.vsu.cs.zmaev;

import java.util.Arrays;

public class SortState {
    public enum Type {State, Compare, Change, ChangeBack, Skip}
    private Type type;
    private int[] array;
    private boolean[] fixed;
    private int fixedRow, fixedJ, amountFixedForward, getAmountFixedBehind;
    private int a, b;

    public SortState(Type type, int[] array, boolean[] fixed, int fixedRow, int fixedJ,
                     int amountFixedForward, int getAmountFixedBehind, int a, int b) {
        this.type = type;
        this.array = Arrays.copyOf(array, array.length);
        this.fixed = Arrays.copyOf(fixed, fixed.length);
        this.fixedRow = fixedRow;
        this.fixedJ = fixedJ;
        this.amountFixedForward = amountFixedForward;
        this.getAmountFixedBehind = getAmountFixedBehind;
        this.a = a;
        this.b = b;
    }

    public SortState (Type type, int[] array, int a, int fixedRow) {
        this.type = type;
        this.array = Arrays.copyOf(array, array.length);
        this.fixedRow = fixedRow;
        this.a = a;
    }

    public Type getType() {return type; }
    public int[] getArray() {
        return array;
    }
    public int getFixedRow() {
        return fixedRow;
    }
    public int getFixedJ() {
        return fixedJ;
    }
    public int getAmountFixedForward() {
        return amountFixedForward;
    }
    public int getGetAmountFixedBehind() {
        return getAmountFixedBehind;
    }
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public boolean[] getFixed() {
        return fixed;
    }
}
