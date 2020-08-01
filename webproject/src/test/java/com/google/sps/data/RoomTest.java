package com.google.sps.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Room.
 */
public class RoomTest {
    private static final String TITLE = "test room";
    private static final String LINK = "hello.com";
    private static final String DESCRIPTION = "my test room";
    private static final int DELIVERY_LOCATION = 123456; 
    private static final int PHONE_NUMBER = 87654321;
    private static final Category CATEGORY = Category.CHINESE;
    private static final double MIN_PRICE = 1.0;
    private static final double DELIVERY_FEE = 10.0;
    private static final String CREATOR = "kevin@gmail.com";
    private static final String USER = "user@hotmail.com";
    private static final double ORDERS_VALUE_1 = 1.0;
    private static final double ORDERS_VALUE_2 = 1.0;
    private static final double DELTA = 1E-15;

    private Room room;

    @Before
    public void setUp() {
        room = Room.newBuilder()
                .setTitle(TITLE)
                .setLink(LINK)
                .setDescription(DESCRIPTION)
                .setDeliveryLocation(DELIVERY_LOCATION)
                .setPhoneNumber(PHONE_NUMBER)
                .setCategory(CATEGORY)
                .setMinPrice(MIN_PRICE)
                .setDeliveryFee(DELIVERY_FEE)
                .setCreator(CREATOR)
                .build();
        room.addUser(CREATOR);
        room.addOrdersValue(ORDERS_VALUE_1);
    }

    @Test
    public void getTitle_returnsTitle() {
        assertEquals(TITLE, room.getTitle());
    }

    @Test
    public void getLink_returnsLink() {
        assertEquals(LINK, room.getLink());
    }

    @Test
    public void getDescription_returnsDescription() {
        assertEquals(DESCRIPTION, room.getDescription());
    }

    @Test
    public void getDeliveryLocation_returnsDeliveryLocation() {
        assertEquals(DELIVERY_LOCATION, room.getDeliveryLocation());
    }

    @Test
    public void getPhoneNumber_returnsPhoneNumber() {
        assertEquals(PHONE_NUMBER, room.getPhoneNumber());
    }

    @Test
    public void getCategory_returnsCategory() {
        assertEquals(CATEGORY, room.getCategory());
    }

    @Test
    public void getMinPrice_returnsMinPrice() {
        assertEquals(MIN_PRICE, room.getMinPrice(), DELTA);
    }

    @Test
    public void getDeliveryFee_returnsDeliveryFee() {
        assertEquals(DELIVERY_FEE, room.getDeliveryFee(), DELTA);
    }

    @Test
    public void getUsers_returnsUsers() {
        List<String> users = new ArrayList<>();
        users.add(CREATOR);
        assertEquals(users, room.getUsers());
    }

    @Test
    public void getCreator_returnsCreator() {
        assertEquals(CREATOR, room.getCreator());
    }

    @Test
    public void getOrdersValue_returnsOrdersValue() {
        assertEquals(ORDERS_VALUE_1, room.getOrdersValue(), DELTA);
    }

    @Test
    public void addUser_addsUser() {
        List<String> users = new ArrayList<>();
        users.add(CREATOR);
        users.add(USER);
        room.addUser(USER);
        assertEquals(users, room.getUsers());
    }

    @Test
    public void addOrdersValue_addsOrdersValue() {
        room.addOrdersValue(ORDERS_VALUE_2);
        assertEquals(ORDERS_VALUE_1 + ORDERS_VALUE_2, room.getOrdersValue(), DELTA);
    }
}
