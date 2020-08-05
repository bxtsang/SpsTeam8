package com.google.sps.services.interfaces;

import java.io.IOException;

import com.google.sps.proto.UserRoomStatusProto.UserRoomStatusRequest;

/**
 * An interface to handle dependency injection in GetJoinServlet.
 */
public interface UserRoomStatusService {
    public boolean execute(UserRoomStatusRequest getJoinRequest) throws InterruptedException;
}
