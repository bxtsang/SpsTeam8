package com.google.sps.servlets;

import com.google.protobuf.util.JsonFormat;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.authentication.AuthenticationHandlerSupplier;
import com.google.sps.util.FirebaseUtil;

import java.io.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which handles a user joining a  room.
 */
@Singleton
public class JoinRoomServlet extends HttpServlet {
    private JoinRoomService joinRoomService;

    @Inject
    public JoinRoomServlet(JoinRoomService joinRoomService) {
        this.joinRoomService = joinRoomService;
    }

    @Override
    public void init() {
        FirebaseUtil.initializeFirebase();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthenticationHandler auth = AuthenticationHandlerSupplier.getAuthenticationHandler();
        if (!auth.isUserLoggedIn()) {
            response.setStatus(400);
            return;
        }

        String roomId = request.getParameter("roomId");

        JoinRoomRequest.Builder postJoinRequest = JoinRoomRequest.newBuilder();
        postJoinRequest.setRoomId(roomId);

        JoinRoomResponse postJoinResponse = joinRoomService.execute(postJoinRequest.build());

        response.setContentType("application/json; charset=UTF-8;");
        response.getWriter().println(JsonFormat.printer().print(postJoinResponse));
        response.setStatus(200);
    }
}
