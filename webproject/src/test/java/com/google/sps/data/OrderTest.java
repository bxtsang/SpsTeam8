package com.google.sps.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Order.
 */
public class OrderTest {
    private static final String USER_EMAIL = "test@gmail.com";
    private static final String PRODUCT = "test product";
    private static final int QUANTITY = 2;
    private static final double UNIT_PRICE = 1.0;
    private static final String ROOM_ID = "123456";
    private static final double DELTA = 1E-15;

    private Order order;

    @Before
    public void setUp() {
        order = Order.newBuilder()
                .setUserEmail(USER_EMAIL)
                .setProduct(PRODUCT)
                .setQuantity(QUANTITY)
                .setUnitPrice(UNIT_PRICE)
                .setRoomId(ROOM_ID)
                .build();
    }

    @Test
    public void getUser_returnsUserEmail() {
        assertEquals(USER_EMAIL, order.getUser());
    }

    @Test
    public void getProduct_returnsProduct() {
        assertEquals(PRODUCT, order.getProduct());
    }

    @Test
    public void getQuantity_returnsQuantity() {
        assertEquals(QUANTITY, order.getQuantity());
    }

    @Test
    public void getUnitPrice_returnsUnitPrice() {
        assertEquals(UNIT_PRICE, order.getUnitPrice(), DELTA);
    }

    @Test
    public void getOrderPrice_returnsOrderPrice() {
        assertEquals(QUANTITY * UNIT_PRICE, order.getOrderPrice(), DELTA);
    }

    @Test
    public void getRoomId_returnsRoomId() {
        assertEquals(ROOM_ID, order.getRoomId());
    }

    @Test
    public void getUserEmailRoomId_returnsUserEmailRoomId() {
        assertEquals(USER_EMAIL + "_" + ROOM_ID, order.getUserEmailRoomId());
    }
}
