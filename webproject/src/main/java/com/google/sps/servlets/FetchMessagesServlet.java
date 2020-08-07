package com.google.sps.servlets;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.util.JsonFormat;
import com.google.sps.data.MessageProto.FetchMessagesResponse;
import com.google.sps.data.MessageProto.FetchMessagesRequest;
import com.google.sps.services.interfaces.FetchMessagesService;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which renders the website's listings page.
 */
@Singleton
public class FetchMessagesServlet extends HttpServlet {
    private FetchMessagesService fetchMessagesService;

    @Inject
    public FetchMessagesServlet(FetchMessagesService fetchMessagesService) {
        this.fetchMessagesService = fetchMessagesService;
    }

    /**
     * Called by the server to allow this servlet to handle a GET request from the chatBox page.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String roomId = request.getParameter("roomId");

        FetchMessagesRequest fetchMessagesRequest = FetchMessagesRequest.newBuilder().setRoomId(roomId).build();
        FetchMessagesResponse fetchMessagesResponse = fetchMessagesService.execute(fetchMessagesRequest);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JsonFormat.printer().print(fetchMessagesResponse));
        response.setStatus(200);
    }
}
