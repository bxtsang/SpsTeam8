package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.JoinRoomProto.JoinRoomResponse;
import com.google.sps.proto.JoinRoomProto.JoinRoomRequest;

/**
 * An interface to handle dependency injection in JoinServlet.
 */
public interface JoinRoomService {
    public JoinRoomResponse execute(JoinRoomRequest joinRequest) throws ServletException;
}
