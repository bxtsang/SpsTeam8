package com.google.sps.services.interfaces;

import java.io.IOException;

import com.google.firebase.FirebaseOptions;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.proto.GetJoinProto.GetJoinRequest;

public interface GetJoinService {
    public FirebaseOptions getFirebaseOptions() throws IOException;
    public String execute(GetJoinRequest getJoinRequest) throws IOException;
}
