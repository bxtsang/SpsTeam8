package com.google.sps.services.implementations;

import javax.inject.Inject;
import javax.servlet.ServletException;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.MessageProto.Message;
import com.google.sps.dataManagers.MessagesManager;
import com.google.sps.proto.SendTextMessageProto.SendTextMessageRequest;
import com.google.sps.proto.SendTextMessageProto.SendTextMessageResponse;
import com.google.sps.services.interfaces.SendTextMessageService;

public class SendTextMessageServiceImpl implements SendTextMessageService {
    private AuthenticationHandler authenticationHandler;
    private MessagesManager messagesManager;

    @Inject
    public SendTextMessageServiceImpl(AuthenticationHandler authenticationHandler,
                               MessagesManager messagesManager) {
        this.authenticationHandler = authenticationHandler;
        this.messagesManager = messagesManager;
    }


    @Override
    public SendTextMessageResponse execute(SendTextMessageRequest sendTextMessageRequest) throws ServletException {
        String roomId = sendTextMessageRequest.getRoomId();
        String user = authenticationHandler.getCurrentUser().getEmail();
        String message = sendTextMessageRequest.getMessage();

        Message messageObject = messagesManager.addMessage(user, message, roomId);

        return SendTextMessageResponse.newBuilder().setMessages(messageObject).build();
    }
}
