package com.siziksu.crypto.common.utils;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Class with some math utils.
 */
public final class MathUtils {

    private MathUtils() {}

    /**
     * This method formats a float value into a String with maximum 3 decimals precision using a HALF_UP rounding mode and trailing zeros removed.
     * <p>If the value does not contains a number format will return a empty string.
     *
     * @param value the float value to format
     *
     * @return String formatted
     */
    public static String roundAndFormatNumber(String value) {
        try {
            DecimalFormat formatter = getDecimalFormatter();
            return formatter.format(new BigDecimal(value));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    /**
     * This method tells if a string number is positive or negative (0 will be positive).
     * <p>If the value does not contains a number format will return false.
     *
     * @param value the string value
     *
     * @return true if positive or zero, false if negative
     */
    public static boolean isPositive(String value) {
        if (value == null) {
            return false;
        }
        try {
            double number = Double.parseDouble(value);
            return !(number < 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @NonNull
    private static DecimalFormat getDecimalFormatter() {
        DecimalFormat formatter = new DecimalFormat();
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter;
    }
}
