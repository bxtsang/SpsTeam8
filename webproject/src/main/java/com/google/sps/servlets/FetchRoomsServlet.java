package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.sps.data.Room;
import com.google.sps.firebase.Firebase;
import com.google.sps.services.interfaces.FetchRoomsService;
import com.google.sps.util.FirebaseUtil;

/**
 * A servlet which fetches rooms.
 */
@Singleton
public class FetchRoomsServlet extends HttpServlet {
    private FetchRoomsService fetchRoomsService;
    private FirebaseUtil firebaseUtil;

    @Inject
    public FetchRoomsServlet(FetchRoomsService fetchRoomsService, FirebaseUtil firebaseUtil) {
        this.fetchRoomsService = fetchRoomsService;
        this.firebaseUtil = firebaseUtil;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        DatabaseReference ref = firebaseUtil.getRoomsReference();
        List<DataSnapshot> dataSnapshots = firebaseUtil.getAllSnapshotsFromReference(ref);

        List<Room> rooms = new ArrayList<>();
        for (DataSnapshot dataSnapshot : dataSnapshots) {
            System.out.println("Datasnapshot: " + dataSnapshot);
            Room room = dataSnapshot.getValue(Room.class);
            System.out.println("Room: " + room);
            rooms.add(room);
        }

        System.out.println("RoomS: " + rooms);
        response.setStatus(200);

    }
}
