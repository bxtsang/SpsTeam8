package com.google.sps.dataManagers;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.sps.data.Room;
import com.google.sps.util.FirebaseUtil;

@Singleton
public class RoomManager {
    private FirebaseUtil firebaseUtil;

    @Inject
    public RoomManager(FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
    }

    public Room closeRoom(String roomId) throws ServletException {
        if (!isRoomIdValid(roomId)) {
            throw new ServletException("Invalid roomId");
        }

        CountDownLatch countDownLatch = new CountDownLatch(1);
        firebaseUtil.getRoomsReference().child(roomId).child("isOpen")
                .setValue(Boolean.FALSE, (error, reference) -> {
                    if (error == null) {
                        countDownLatch.countDown();
                    } else {
                        throw new ServletException("There was an error in updating the database when closing the room.");
                    }
                });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new ServletException("The close room process was interrupted.");
        }

        return Room.newBuilder().setId(roomId).build();
    }

    private boolean isRoomIdValid(String roomId) throws ServletException {
        Query query = firebaseUtil.getRoomsReference().orderByKey();
        Optional<DataSnapshot> room = firebaseUtil.getQuerySnapshot(query, roomId);
        return room.isPresent();
    }
}
