package com.google.sps.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.sps.data.CategoryProto;
import com.google.sps.data.RoomProto.Room;
import com.google.sps.dataManagers.RoomManager;
import com.google.sps.proto.FetchRoomsProto.FetchRoomsResponse;
import com.google.sps.services.interfaces.FetchRoomsService;
import com.google.sps.util.FirebaseUtil;

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
