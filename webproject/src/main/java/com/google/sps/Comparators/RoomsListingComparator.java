package com.google.sps.Comparators;

import java.util.Comparator;

import com.google.sps.data.RoomProto.Room;

/**
 * A comparator to sort rooms based on how full they are.
 * The "full" value is calculated as follows:
 * 1. The difference of minPrice and ordersValue gives the order amount needed to hit the minPrice.
 * 2. The delivery fee is added to the difference because it is independent of the minPrice and ordersValue.
 * 3. The value calculated in the previous step is divided by the no of users in a room to obtain the average per
 * person order value.
 * A smaller "full" value means that the average per person order value is smaller. This may approximately mean that
 * the room is closer to hitting the minPrice / is very active, thus the order by the users maybe be placed soon.
 * Therefore, it will be listed before rooms with a larger "full" value.
 */
public class RoomsListingComparator implements Comparator<Room> {
    public int compare(Room aRoom, Room bRoom) {
        double aRoomAveragePerPersonValue =
                (aRoom.getMinPrice() - aRoom.getOrdersValue() + aRoom.getDeliveryFee()) / aRoom.getUsersCount();
        double bRoomAveragePerPersonValue =
                (bRoom.getMinPrice()  - bRoom.getOrdersValue() + bRoom.getOrdersValue()) / bRoom.getUsersCount();
        return  Double.compare(aRoomAveragePerPersonValue, bRoomAveragePerPersonValue);
    }
}
