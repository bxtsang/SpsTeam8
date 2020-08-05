package com.google.sps.servlets;

import java.io.IOException;

import com.google.protobuf.util.JsonFormat;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;
import com.google.sps.services.interfaces.JoinRoomService;

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
    private AuthenticationHandler authenticationHandler;

    @Inject
    public JoinRoomServlet(JoinRoomService joinRoomService, AuthenticationHandler authenticationHandler) {
        this.joinRoomService = joinRoomService;
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!authenticationHandler.isUserLoggedIn()) {
            response.setStatus(400);
            return;
        }

        String roomId = request.getParameter("roomId");

        JoinRoomRequest.Builder joinRoomRequest = JoinRoomRequest.newBuilder();
        joinRoomRequest.setRoomId(roomId);

        JoinRoomResponse joinRoomResponse = joinRoomService.execute(joinRoomRequest.build());

        response.setContentType("application/json; charset=UTF-8;");
        response.getWriter().println(JsonFormat.printer().print(joinRoomResponse));
        response.setStatus(200);
    }
}
