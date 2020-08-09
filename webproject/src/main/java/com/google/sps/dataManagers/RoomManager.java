package com.google.sps.dataManagers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.sps.Comparators.RoomsListingComparator;
import com.google.sps.data.CategoryProto.Category;
import com.google.sps.data.RoomProto.Room;
import com.google.firebase.database.Query;
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
        Queue<Room> sortedRooms = dataSnapshots.stream().map(this::toRoom).collect(Collectors.toCollection(() ->
                                new PriorityQueue<>(dataSnapshots.size(), new RoomsListingComparator())));
        return sortedRooms;
    }

    public Room closeRoom(String roomId) throws ServletException {
        if (!isRoomIdValid(roomId)) {
            throw new ServletException("Invalid roomId");
        }

        final BlockingQueue<Optional<ServletException>> queue = new LinkedBlockingDeque(1);
        firebaseUtil.getRoomsReference().child(roomId).child("isOpen")
                .setValue(Boolean.FALSE, (error, reference) -> {
                    if (error != null) {
                        queue.add(Optional.of(new ServletException("There was an error closing the room.")));
                    } else {
                        queue.add(Optional.empty());
                    }
                });
        
        try {
            Optional<ServletException> servletException = queue.poll(30, TimeUnit.SECONDS);
            if (servletException.isPresent()) {
                throw servletException.get();
            }
        } catch (InterruptedException e) {
            throw new ServletException("The close room process did not return a response from the database.");
        }

        Query query = firebaseUtil.getRoomsReference().orderByChild(roomId);
        Optional<DataSnapshot> roomSnapshot = firebaseUtil.getQuerySnapshot(query, roomId);
        if (!roomSnapshot.isPresent()) {
            return null;
        }

        return toRoom(roomSnapshot.get());
    }

    private Room toRoom(DataSnapshot dataSnapshot) {
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

        return roomBuilder.build();
    }

    private boolean isRoomIdValid(String roomId) throws ServletException {
        Query query = firebaseUtil.getRoomsReference().orderByKey();
        Optional<DataSnapshot> room = firebaseUtil.getQuerySnapshot(query, roomId);
        return room.isPresent();
    }
}
