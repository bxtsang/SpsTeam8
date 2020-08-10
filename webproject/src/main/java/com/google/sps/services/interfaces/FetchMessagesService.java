package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.FetchMessagesProto.FetchMessagesResponse;
import com.google.sps.proto.FetchMessagesProto.FetchMessagesRequest;


public interface FetchMessagesService {
    public FetchMessagesResponse execute(FetchMessagesRequest fetchMessagesRequest) throws ServletException;
}
