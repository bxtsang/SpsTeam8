package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.data.MessageProto.FetchMessagesRequest;
import com.google.sps.data.MessageProto.FetchMessagesResponse;


public interface FetchMessagesService {
    public FetchMessagesResponse execute(FetchMessagesRequest fetchMessagesRequest) throws ServletException;
}
