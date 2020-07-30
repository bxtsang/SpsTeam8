package com.google.sps.services.interfaces;

import java.io.IOException;

import com.google.firebase.FirebaseOptions;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;

public interface JoinRoomService {
    public FirebaseOptions getFirebaseOptions() throws IOException;
    public JoinRoomResponse execute(JoinRoomRequest joinRequest);
}
