package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.UserRoomStatusProto.UserRoomStatusResponse;
import com.google.sps.proto.UserRoomStatusProto.UserRoomStatusRequest;

/**
 * An interface to handle dependency injection in GetJoinServlet.
 */
public interface UserRoomStatusService {
    public UserRoomStatusResponse execute(UserRoomStatusRequest getJoinRequest) throws ServletException;
}
