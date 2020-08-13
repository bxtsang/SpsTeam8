package com.google.sps.data;

import static org.junit.Assert.assertEquals;

import com.google.sps.util.TimestampUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Message.
 */
public class MessageTest {
    private static final String USER = "test user";
    private static final String MESSAGE = "test message";
    private static final String TYPE = "test type";
    private static final String FORMATTED_TIME = TimestampUtil.getFormattedTime();

    private Message message;

    @Before
    public void setUp() {
        message = new Message(USER, MESSAGE, TYPE, FORMATTED_TIME);
    }

    @Test
    public void getUser_returnsUser() {
        assertEquals(USER, message.getUser());
    }

    @Test
    public void getTime_returnsTime() {
        assertEquals(FORMATTED_TIME, message.getTime());
    }

    @Test
    public void getMessage_returnsMessage() {
        assertEquals(MESSAGE, message.getMessage());
    }

    @Test
    public void getType_returnsType() {
        assertEquals(TYPE, message.getType());
    }
}
