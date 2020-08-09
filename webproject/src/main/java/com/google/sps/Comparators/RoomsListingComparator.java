package com.google.sps.Comparators;

import java.util.Comparator;

import com.google.sps.data.RoomProto.Room;

/**
 * A comparator to sort rooms based on how full they are.
 * The estimated number of people needed to hit the minPrice is calculated as follows:
 * 1. The average per person order cost is calculated which is inclusive of the delivery fee.
 * 2. The difference to minPrice order is calculated and divided by the average per person order cost calculated in
 * the previous step. This gives the estimated additional no of people needed to hit the minPrice.
 * A smaller value means that lesser number of people are needed to hit the minPrice.
 * Therefore, it will be listed before rooms with a larger value.
 */
public class RoomsListingComparator implements Comparator<Room> {
    public int compare(Room aRoom, Room bRoom) {
        double aRoomAveragePerPersonValue =
                (aRoom.getOrdersValue() + aRoom.getDeliveryFee()) / aRoom.getUsersCount();
        double aEstimatedNoOfPeopleNeeded = (aRoom.getMinPrice() - aRoom.getOrdersValue()) / aRoomAveragePerPersonValue;

        double bRoomAveragePerPersonValue =
                (bRoom.getOrdersValue() + bRoom.getDeliveryFee()) / bRoom.getUsersCount();
        double bEstimatedNoOfPeopleNeeded = (bRoom.getMinPrice() - bRoom.getOrdersValue()) / bRoomAveragePerPersonValue;

        return  Double.compare(aEstimatedNoOfPeopleNeeded, bEstimatedNoOfPeopleNeeded);
    }
}
