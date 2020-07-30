package com.google.sps.services.interfaces;

import java.io.IOException;

import com.google.firebase.FirebaseOptions;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.proto.PostJoinProto.PostJoinResponse;
import com.google.sps.proto.PostJoinProto.PostJoinRequest;

public interface PostJoinService {
    public FirebaseOptions getFirebaseOptions() throws IOException;
    public PostJoinResponse execute(PostJoinRequest joinRequest);
}
