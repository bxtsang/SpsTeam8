package com.google.sps.services.implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.sps.data.MessageProto.FetchMessagesResponse;
import com.google.sps.data.MessageProto.FetchMessagesRequest;
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

        int index = 0;
        for(Message message : messages) {
            fetchMessagesResponseBuilder.setMessages(index, message);
            index++;
        }

        return fetchMessagesResponseBuilder.build();
    }
}
