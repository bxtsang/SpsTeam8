package com.google.sps.servlets;

import com.google.sps.proto.UserRoomStatusProto.UserRoomStatusRequest;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.util.FirebaseUtil;

import java.io.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which manages fetching whether a user has joined a room.
 */
@Singleton
public class UserRoomStatusServlet extends HttpServlet {
    private UserRoomStatusService userRoomStatusService;

    @Inject
    public UserRoomStatusServlet(UserRoomStatusService userRoomStatusService) {
        this.userRoomStatusService = userRoomStatusService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roomId = request.getParameter("roomId");
        UserRoomStatusRequest.Builder userRoomJoinRequest = UserRoomStatusRequest.newBuilder();
        userRoomJoinRequest.setRoomId(roomId);

        String getJoinResponse = userRoomStatusService.execute(userRoomJoinRequest.build());
        response.setContentType("text/plain");
        if (getJoinResponse.substring(0, 2).equals("{}")) {
            response.getWriter().print("Join");
        } else {
            response.getWriter().print("Chat");
        }
    }
}
