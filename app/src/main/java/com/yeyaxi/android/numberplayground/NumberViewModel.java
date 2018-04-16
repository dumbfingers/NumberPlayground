package com.yeyaxi.android.numberplayground;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.HashMap;

public class NumberViewModel extends ViewModel {

    private MutableLiveData<BigDecimal> sumValue;
    private HashMap<Integer, BigDecimal> values;

    @NonNull
    public LiveData<BigDecimal> getSumValue() {
        if (this.sumValue == null) {
            this.sumValue = new MutableLiveData<>();
            this.sumValue.setValue(BigDecimal.ZERO);
        }
        return this.sumValue;
    }

    public void updateValue(int key, @Nullable String text) throws NumberFormatException {
        BigDecimal value = BigDecimal.valueOf(Double.valueOf(StringUtil.isNullOrWhitespace(text) ? "0" : text));
        getValues().put(key, value);
        updateSumValue();
    }

    private void updateSumValue() {
        BigDecimal value = BigDecimal.ZERO;
        for (BigDecimal each : getValues().values()) {
            value = value.add(each == null ? BigDecimal.ZERO : each);
        }
        this.sumValue.setValue(value);
    }

    private HashMap<Integer, BigDecimal> getValues() {
        if (this.values == null) {
            this.values = new HashMap<>();
        }
        return this.values;
    }
}
