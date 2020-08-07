package com.google.sps.dataManagers;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

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

        return Room.newBuilder().setId(roomId).build();
    }

    private boolean isRoomIdValid(String roomId) throws ServletException {
        Query query = firebaseUtil.getRoomsReference().orderByKey();
        Optional<DataSnapshot> room = firebaseUtil.getQuerySnapshot(query, roomId);
        return room.isPresent();
    }
}
