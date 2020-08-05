package com.google.sps.dataManagers;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

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

    public Optional getUserRoom(String userEmail, String roomId) throws InterruptedException {
        String userEmailRoom = userEmail + "_" + roomId;
        final BlockingQueue queue = new LinkedBlockingDeque(1);

        firebaseUtil.getUserRoomReference().orderByChild("userEmailRoom").equalTo(userEmailRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    queue.add(Optional.of(dataSnapshot));
                } else {
                    queue.add(Optional.empty());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        return (Optional) queue.poll(30, TimeUnit.SECONDS);
    }
}
