package com.yeyaxi.android.numberplayground;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class StringUtilTest {

    @Test
    public void isNullOrWhitespaceTest() {
        assertTrue(StringUtil.isNullOrWhitespace(null));
        assertTrue(StringUtil.isNullOrWhitespace(""));
        assertTrue(StringUtil.isNullOrWhitespace(" "));
        assertFalse(StringUtil.isNullOrWhitespace("5"));

    }
}
