package com.google.sps.services.implementations;

import com.google.firebase.database.FirebaseDatabase;
import com.google.sps.data.UserRoom;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.authentication.AuthenticationHandlerSupplier;
import com.google.sps.util.TimestampUtil;

public class JoinRoomRoomServiceImpl implements JoinRoomService {
    @Override
    public JoinRoomResponse execute(JoinRoomRequest postJoinRequest) {
        String userEmail = AuthenticationHandlerSupplier.getAuthenticationHandler().getCurrentUser().getEmail();

        FirebaseDatabase.getInstance()
                .getReference("UserRoom")
                .push()
                .setValueAsync(new UserRoom(userEmail, postJoinRequest.getRoomId()));

        return JoinRoomResponse.newBuilder()
                .setRoomId(postJoinRequest.getRoomId())
                .setTimestamp(TimestampUtil.getTimestamp())
                .build();
    }
}
