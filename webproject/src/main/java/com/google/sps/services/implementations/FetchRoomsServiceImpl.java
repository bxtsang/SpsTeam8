package com.google.sps.services.implementations;

import java.util.Queue;

import javax.inject.Inject;
import javax.servlet.ServletException;

import com.google.sps.data.RoomProto.Room;
import com.google.sps.dataManagers.RoomManager;
import com.google.sps.proto.FetchRoomsProto.FetchRoomsResponse;
import com.google.sps.services.interfaces.FetchRoomsService;

public class FetchRoomsServiceImpl implements FetchRoomsService {
    private RoomManager roomManager;

    @Inject
    public  FetchRoomsServiceImpl(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    @Override
    public FetchRoomsResponse execute() throws ServletException {
        Queue<Room> rooms = roomManager.getAllRooms();
        FetchRoomsResponse.Builder fetchRoomsResponseBuilder = FetchRoomsResponse.newBuilder();

        while(!rooms.isEmpty()) {
            fetchRoomsResponseBuilder.addRooms(rooms.poll());
        }

        return fetchRoomsResponseBuilder.build();
    }
}
