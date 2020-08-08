package com.google.sps.dataManagers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.sps.data.CategoryProto.Category;
import com.google.sps.data.RoomProto.Room;
import com.google.sps.util.FirebaseUtil;

@Singleton
public class RoomManager {
    private FirebaseUtil firebaseUtil;

    @Inject
    public RoomManager(FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
    }

    public Queue<Room> getAllRooms() throws ServletException {
        DatabaseReference ref = firebaseUtil.getRoomsReference();
        List<DataSnapshot> dataSnapshots = firebaseUtil.getAllSnapshotsFromReference(ref);

        Queue<Room> sortedRooms = new PriorityQueue<>(dataSnapshots.size(), (aRoom, bRoom) -> {
            double aRoomOrderValue = Math.max(aRoom.getOrdersValue(), aRoom.getMinPrice());
            double bRoomOrderValue = Math.max(bRoom.getOrdersValue(), bRoom.getMinPrice());
            double aRoomAveragePerPersonValue = (aRoomOrderValue + aRoom.getDeliveryFee()) / aRoom.getUsersCount();
            double bRoomAveragePerPersonValue = (bRoomOrderValue + bRoom.getDeliveryFee()) / bRoom.getUsersCount();
            return  Double.compare(aRoomAveragePerPersonValue, bRoomAveragePerPersonValue);
        });

        for (DataSnapshot dataSnapshot : dataSnapshots) {
            String id = dataSnapshot.getKey();
            String title = dataSnapshot.child("title").getValue(String.class);
            String link = dataSnapshot.child("link").getValue(String.class);
            String description = dataSnapshot.child("description").getValue(String.class);
            int deliveryLocation = dataSnapshot.child("deliveryLocation").getValue(int.class);
            int phoneNumber = dataSnapshot.child("phoneNumber").getValue(int.class);
            Category category = dataSnapshot.child("category").getValue(Category.class);
            double minPrice = dataSnapshot.child("minPrice").getValue(double.class);
            double deliveryFee = dataSnapshot.child("deliveryFee").getValue(double.class);
            boolean isOpen = dataSnapshot.child("isOpen").getValue(boolean.class);
            double ordersValue = dataSnapshot.child("ordersValue").getValue(double.class);
            long timestamp = dataSnapshot.child("timestamp").getValue(long.class);

            List<String> users = new ArrayList<>();
            for (DataSnapshot data : dataSnapshot.child("users").getChildren()) {
                users.add(data.getValue(String.class));
            }

            Room.Builder roomBuilder = Room.newBuilder()
                    .setId(id)
                    .setTitle(title)
                    .setLink(link)
                    .setDescription(description)
                    .setDeliveryLocation(deliveryLocation)
                    .setPhoneNumber(phoneNumber)
                    .setCategory(category)
                    .setMinPrice(minPrice)
                    .setDeliveryFee(deliveryFee)
                    .setIsOpen(isOpen)
                    .setOrdersValue(ordersValue)
                    .setTimestamp(timestamp);

            for (String user : users) {
                roomBuilder.addUsers(user);
            }

            sortedRooms.offer(roomBuilder.build());
        }

        return sortedRooms;
    }
}
