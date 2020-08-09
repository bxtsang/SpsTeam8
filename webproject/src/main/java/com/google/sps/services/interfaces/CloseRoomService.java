package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.CloseRoomProto.CloseRoomResponse;
import com.google.sps.proto.CloseRoomProto.CloseRoomRequest;

/**
 * An interface to handle dependency injection in CloseRoomServlet.
 */
public interface CloseRoomService {
    public CloseRoomResponse execute(CloseRoomRequest closeRoomRequest) throws ServletException;
}
