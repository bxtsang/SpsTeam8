package com.google.sps.servlets;

import com.google.sps.proto.CloseRoomProto.CloseRoomRequest;
import com.google.sps.proto.CloseRoomProto.CloseRoomResponse;
import com.google.sps.services.interfaces.CloseRoomService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * A servlet which manages room closure.
 */
@Singleton
public class CloseRoomServlet extends HttpServlet {
    private CloseRoomService closeRoomService;

    @Inject
    public CloseRoomServlet(CloseRoomService closeRoomService) throws Exception {
        this.closeRoomService = closeRoomService;
    }

    /**
     * Called by the server close a room.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the server's request.
     * @throws ServletException If there is an error updating the database when closing a room.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String roomId = request.getParameter("roomId");

        CloseRoomRequest closeRoomRequest = CloseRoomRequest.newBuilder().setRoomId(roomId).build();
        CloseRoomResponse closeRoomResponse = closeRoomService.execute(closeRoomRequest);
        
        response.sendRedirect("/");
    }
}
