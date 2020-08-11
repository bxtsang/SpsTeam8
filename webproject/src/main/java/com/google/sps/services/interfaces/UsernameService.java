package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.UsernameProto.UsernameResponse;
import com.google.sps.proto.UsernameProto.UsernameRequest;

/**
 * An interface to handle dependency injection in UsernameServlet.
 */
public interface UsernameService {
    public UsernameResponse execute(UsernameRequest usernameRequest) throws ServletException;
}
