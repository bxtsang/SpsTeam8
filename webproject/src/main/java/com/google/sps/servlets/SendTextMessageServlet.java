package com.google.sps.servlets;

import com.google.protobuf.util.JsonFormat;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.proto.SendTextMessageProto.SendTextMessageRequest;
import com.google.sps.proto.SendTextMessageProto.SendTextMessageResponse;
import com.google.sps.services.interfaces.SendTextMessageService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which handles a user sending a  text message.
 */
@Singleton
public class SendTextMessageServlet extends HttpServlet {
    private SendTextMessageService sendTextMessageService;
    private AuthenticationHandler authenticationHandler;

    @Inject
    public SendTextMessageServlet(SendTextMessageService sendTextMessageService, AuthenticationHandler authenticationHandler) {
        this.sendTextMessageService = sendTextMessageService;
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authenticationHandler.isUserLoggedIn()) {
            response.setStatus(400);
            return;
        }

        String message = request.getParameter("message");
        String roomId = request.getParameter("roomId");
        SendTextMessageRequest sendTextMessageRequest =
                SendTextMessageRequest.newBuilder().setRoomId(roomId).setMessage(message).build();
        SendTextMessageResponse sendTextMessageResponse = sendTextMessageService.execute(sendTextMessageRequest);

        response.setContentType("application/json; charset=UTF-8;");
        response.getWriter().println(JsonFormat.printer().print(sendTextMessageResponse));
        response.setStatus(200);
    }
}
