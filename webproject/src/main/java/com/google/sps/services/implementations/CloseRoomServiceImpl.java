package com.google.sps.services.implementations;


import com.google.sps.data.RoomProto.Room;
import com.google.sps.dataManagers.RoomManager;
import com.google.sps.proto.CloseRoomProto.CloseRoomResponse;
import com.google.sps.proto.CloseRoomProto.CloseRoomRequest;
import com.google.sps.services.interfaces.CloseRoomService;

import javax.inject.Inject;
import javax.servlet.ServletException;

public class CloseRoomServiceImpl implements CloseRoomService {
    private UserRoomManager userRoomManager;

    @Inject
    public CloseRoomServiceImpl(UserRoomManager userRoomManager) {
        this.userRoomManager = userRoomManager;
    }

    @Override
    public CloseRoomResponse execute(CloseRoomRequest closeRoomRequest) throws ServletException {
        Room room = roomManager.closeRoom(closeRoomRequest.getRoomId());
        return CloseRoomResponse.newBuilder()
                .setRoomId(closeRoomRequest.getRoomId())
                .build();
    }
}
