package com.google.sps.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.util.JsonFormat;
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
