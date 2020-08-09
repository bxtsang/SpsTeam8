package com.google.sps.Comparators;

import java.util.Comparator;

import com.google.sps.data.RoomProto.Room;

public class RoomListingComparator implements Comparator<Room> {
    public int compare(Room aRoom, Room bRoom) {
        double aRoomAveragePerPersonValue =
                (aRoom.getMinPrice() - aRoom.getOrdersValue() + aRoom.getDeliveryFee()) / aRoom.getUsersCount();
        double bRoomAveragePerPersonValue =
                (bRoom.getMinPrice()  - bRoom.getOrdersValue() + bRoom.getOrdersValue()) / bRoom.getUsersCount();
        return  Double.compare(aRoomAveragePerPersonValue, bRoomAveragePerPersonValue);
    }
}
