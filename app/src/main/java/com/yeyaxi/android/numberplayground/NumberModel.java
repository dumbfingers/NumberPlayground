package com.yeyaxi.android.numberplayground;

public class NumberModel {
    private double numberValue;

    public NumberModel(int numberValue) {
        this.numberValue = numberValue;
    }

    public double getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(int numberValue) {
        this.numberValue = numberValue;
    }
}
