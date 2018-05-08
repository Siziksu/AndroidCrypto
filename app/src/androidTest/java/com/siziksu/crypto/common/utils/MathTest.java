package com.siziksu.crypto.common.utils;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MathTest {

    @Test
    public void testRoundAndFormatNumber() {
        Assert.assertEquals("1,79", MathUtils.roundAndFormatNumber("1.79000000"));
        assertEquals("", MathUtils.roundAndFormatNumber("1.780dfg0"));
        assertEquals("1", MathUtils.roundAndFormatNumber("1"));
        assertEquals("1", MathUtils.roundAndFormatNumber("1.00"));
        assertEquals("1", MathUtils.roundAndFormatNumber("1.0001"));
        assertEquals("1,001", MathUtils.roundAndFormatNumber("1.0006"));
        assertEquals("1.275.386,24", MathUtils.roundAndFormatNumber("1275386.24"));
        assertEquals("-2,49", MathUtils.roundAndFormatNumber("-2.489506"));
        assertEquals("121.025,386", MathUtils.roundAndFormatNumber("121025.385506"));
        assertEquals("12,384", MathUtils.roundAndFormatNumber("12.384456"));
    }

    @Test
    public void testIfNumberIsPositive() {
        assertTrue(!MathUtils.isPositive("-38.8405"));
        assertTrue(!MathUtils.isPositive("-3,54500"));
        assertTrue(MathUtils.isPositive("1.6456"));
        assertTrue(!MathUtils.isPositive("24,1455"));
        assertTrue(MathUtils.isPositive("0.0000"));
        assertTrue(MathUtils.isPositive("0.000000001"));
        assertTrue(!MathUtils.isPositive("-0.000000001"));
        assertTrue(!MathUtils.isPositive("-0.00sdf001"));
    }
}
