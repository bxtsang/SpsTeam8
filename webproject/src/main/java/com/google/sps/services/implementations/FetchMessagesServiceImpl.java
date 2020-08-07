package com.google.sps.services.implementations;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.sps.proto.FetchMessagesProto.FetchMessagesResponse;
import com.google.sps.proto.FetchMessagesProto.FetchMessagesRequest;
import com.google.sps.data.MessageProto.Message;
import com.google.sps.dataManagers.MessagesManager;
import com.google.sps.services.interfaces.FetchMessagesService;

@Singleton
public class FetchMessagesServiceImpl implements FetchMessagesService {
    private MessagesManager messagesManager;

    @Inject
    public FetchMessagesServiceImpl(MessagesManager messagesManager) {
        this.messagesManager = messagesManager;
    }

    @Override
    public FetchMessagesResponse execute(FetchMessagesRequest fetchMessagesRequest) throws ServletException {
        String roomId = fetchMessagesRequest.getRoomId();

        List<Message> messages = messagesManager.getMessages(roomId);
        FetchMessagesResponse.Builder fetchMessagesResponseBuilder =
                FetchMessagesResponse.newBuilder().setRoomId(roomId);

        for(Message message : messages) {
            fetchMessagesResponseBuilder.addMessages(message);
        }

        return fetchMessagesResponseBuilder.build();
    }
}
