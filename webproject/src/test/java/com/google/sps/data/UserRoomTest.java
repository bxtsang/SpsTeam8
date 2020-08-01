package com.google.sps.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for UserRoom.
 */
public class UserRoomTest {
    private static final String USER_EMAIL = "test@mail.com";
    private static final String ROOM_ID = "iddddddd";

    private UserRoom userRoom;

    @Before
    public void setUp() {
        userRoom = new UserRoom(USER_EMAIL, ROOM_ID);
    }

    @Test
    public void getUserEmail_returnsUserEmail() {
        assertEquals(USER_EMAIL, userRoom.getUserEmail());
    }

    @Test
    public void getRoomId_returnsRoomId() {
        assertEquals(ROOM_ID, userRoom.getRoomId());
    }

    @Test
    public void getUserEmailRoom_returnsUserEmailRoom() {
        assertEquals(USER_EMAIL + "_" + ROOM_ID, userRoom.getUserEmailRoom());
    }
}
