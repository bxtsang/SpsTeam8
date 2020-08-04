package com.google.sps.dataManagers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.sps.data.UserRoom;
import com.google.sps.util.FirebaseUtil;

@Singleton
public class UserRoomManager {
    private FirebaseUtil firebaseUtil;

    @Inject
    public UserRoomManager(FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
    }

    public void addUserRoom(String userEmail, String roomId) {
        UserRoom userRoom = UserRoom.newBuilder().setUserEmail(userEmail).setRoomId(roomId).setUserEmailRoom().build();

        firebaseUtil.getUserRoomReference().push()
            .setValue(userRoom, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            });
    }

    public boolean hasUserJoinedRoom(String userEmail, String roomId) {
        final BlockingQueue queue = new LinkedBlockingDeque(1);
        CountDownLatch done = new CountDownLatch(1);

        firebaseUtil.getUserRoomReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("userEmailRoom").getValue().equals(userEmail + "_" + roomId)) {
                        queue.add(true);
                    }
                }
                done.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("error");
            }
        });

        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        if (queue.remainingCapacity() == 1) {
            return false;
        }

        return true;
    }
}
