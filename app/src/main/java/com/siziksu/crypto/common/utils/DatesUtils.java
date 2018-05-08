package com.siziksu.crypto.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Class with some date utils.
 */
public class DatesUtils {

    private DatesUtils() {}

    /**
     * Formats current date in the format 01/01/1900 00:00:00  2018-04-03T10:54:02+00:00.
     *
     * @param date the date in millis to be formatted
     *
     * @return string formatted
     */
    public static String getDateFormatted(long date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault());
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return formatter.format(calendar.getTime());
    }
}
