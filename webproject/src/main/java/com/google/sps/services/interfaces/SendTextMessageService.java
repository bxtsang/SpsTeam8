package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.SendTextMessageProto.SendTextMessageResponse;
import com.google.sps.proto.SendTextMessageProto.SendTextMessageRequest;

public interface SendTextMessageService {
    public SendTextMessageResponse execute(SendTextMessageRequest sendTextMessageRequest) throws ServletException;
}
