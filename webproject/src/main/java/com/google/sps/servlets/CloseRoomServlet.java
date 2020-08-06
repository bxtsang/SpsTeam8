package com.google.sps.servlets;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.util.FirebaseUtil;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which manages room closure.
 */
public class CloseRoomServlet extends HttpServlet {
    private static Gson gson = new Gson();
    private FirebaseUtil firebaseUtil;

    public CloseRoomServlet() throws Exception {
        firebaseUtil = new FirebaseUtil();
    }

    /**
     * Called by the server close a room.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the server's request.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roomId = request.getParameter("roomId");
        firebaseUtil.getRoomsReference().child(roomId).child("isOpen")
                .setValue(Boolean.FALSE, (databaseError, databaseReference) -> {});

        // Send redirect here instead of inside the lambda expression due to possibility of IOException
        response.sendRedirect("/");
    }
}
