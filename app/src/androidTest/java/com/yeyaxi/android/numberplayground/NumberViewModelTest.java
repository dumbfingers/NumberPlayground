package com.yeyaxi.android.numberplayground;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class NumberViewModelTest {
    NumberViewModel viewModel;

    @Before
    public void init() {
        this.viewModel = new NumberViewModel();
    }

    @Test
    @UiThreadTest
    public void liveDataTest() {
        BigDecimal testValue = new BigDecimal("1");
        Observer<BigDecimal> observer = mock(Observer.class);
        viewModel.getSumValue().observe(mockLifecycleOwner(), observer);
        viewModel.setSumValue(testValue);
        verify(observer).onChanged(testValue);
    }

    private LifecycleOwner mockLifecycleOwner() {
        final LifecycleOwner lifecycleOwner = mock(LifecycleOwner.class);

        LifecycleRegistry registry = new LifecycleRegistry(lifecycleOwner);
        registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);

        doReturn(registry).when(lifecycleOwner).getLifecycle();

        return lifecycleOwner;
    }
}
