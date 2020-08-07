package com.google.sps.services.implementations;

import com.google.sps.data.Room;
import com.google.sps.dataManagers.RoomManager;
import com.google.sps.proto.CloseRoomProto.CloseRoomResponse;
import com.google.sps.proto.CloseRoomProto.CloseRoomRequest;
import com.google.sps.services.interfaces.CloseRoomService;

import javax.inject.Inject;
import javax.servlet.ServletException;

public class CloseRoomServiceImpl implements CloseRoomService {
    private RoomManager roomManager;

    @Inject
    public CloseRoomServiceImpl(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    @Override
    public CloseRoomResponse execute(CloseRoomRequest closeRoomRequest) throws ServletException {
        String roomId = closeRoomRequest.getRoomId();
        Room room = roomManager.closeRoom(roomId);
        return CloseRoomResponse.newBuilder()
                .setRoomId(roomId)
                .build();
    }
}
