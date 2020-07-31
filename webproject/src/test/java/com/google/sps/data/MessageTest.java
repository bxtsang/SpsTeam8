package com.google.sps.data;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Message.
 */
public class MessageTest {
    private static final String USER = "test user";
    private static final String MESSAGE = "test message";
    private static final String TYPE = "test type";

    private Message message;

    @Before
    public void setUp() {
        message = new Message(USER, MESSAGE, TYPE);
    }

    @Test
    public void getUser_returnsUser() {
        assertEquals(USER, message.getUser());
    }

    @Test
    public void getTime_returnsTime() {
        assertEquals(new SimpleDateFormat("HH:mm").format(new Date()), message.getTime());
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
