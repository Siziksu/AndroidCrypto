package com.siziksu.crypto.common.utils;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DatesTest {

    @Test
    public void testDateFormat() throws Exception {
        String string = "2018-04-03T10:54:02+00:00";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = format.parse(string);
        assertEquals("2018-04-03T10:54:02+00:00", DatesUtils.getDateFormatted(date.getTime()));
    }
}
