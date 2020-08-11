package com.google.sps.data;

import static com.google.sps.data.UserRoomProto.UserRoom;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for UserRoom.
 */
public class UserRoomTest {
    private static final String USER_EMAIL = "test@mail.com";
    private static final String ROOM_ID = "iddddddd";
    private static final String USER_EMAIL_ROOM = USER_EMAIL + ROOM_ID;

    private UserRoom userRoom;

    @Before
    public void setUp() {
        userRoom = UserRoom.newBuilder().setUserEmail(USER_EMAIL).setRoomId(ROOM_ID)
                .setUserEmailRoom(USER_EMAIL_ROOM).build();
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
        assertEquals(USER_EMAIL_ROOM, userRoom.getUserEmailRoom());
    }
}
