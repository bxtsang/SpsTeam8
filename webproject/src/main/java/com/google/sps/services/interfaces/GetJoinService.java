package com.google.sps.services.interfaces;

import java.io.IOException;

import com.google.firebase.FirebaseOptions;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.proto.GetJoinProto.GetJoinRequest;

/**
 * An interface to handle dependency injection in GetJoinServlet.
 */
public interface GetJoinService {
    public String execute(GetJoinRequest getJoinRequest) throws IOException;
}
