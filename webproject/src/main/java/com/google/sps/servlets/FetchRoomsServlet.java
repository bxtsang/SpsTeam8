package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.protobuf.util.JsonFormat;
import com.google.sps.data.CategoryProto.Category;
import com.google.sps.data.RoomProto.Room;
import com.google.sps.proto.FetchRoomsProto.FetchRoomsResponse;
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
        FetchRoomsResponse fetchRoomsResponse = fetchRoomsService.execute();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JsonFormat.printer().print(fetchRoomsResponse));
        response.setStatus(200);
    }
}
