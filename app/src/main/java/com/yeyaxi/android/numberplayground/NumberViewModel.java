package com.yeyaxi.android.numberplayground;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

public class NumberViewModel extends ViewModel {

    private MutableLiveData<Double> sumValue;
    private HashMap<Integer, Double> values;

    @NonNull
    public LiveData<Double> getSumValue() {
        if (this.sumValue == null) {
            this.sumValue = new MutableLiveData<>();
            this.sumValue.setValue(0d);
        }
        return this.sumValue;
    }

    public void updateValue(int key, @Nullable String text) throws NumberFormatException {
        Double value = Double.valueOf(StringUtil.isNullOrWhitespace(text) ? "0" : text);
        getValues().put(key, value);
        updateSumValue();
    }

    private void updateSumValue() {
        Double value = 0d;
        for (Double each : getValues().values()) {
            value += (each == null ? 0d : each);
        }
        this.sumValue.setValue(value);
    }

    private HashMap<Integer, Double> getValues() {
        if (this.values == null) {
            this.values = new HashMap<>();
        }
        return this.values;
    }
}
