package com.google.sps.servlets;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.util.FirebaseUtil;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * A servlet which manages room closure.
 */
public class CloseRoomServlet extends HttpServlet {
    private static Gson gson = new Gson();
    private CountDownLatch countDownLatch;
    private FirebaseUtil firebaseUtil;

    public CloseRoomServlet() throws Exception {
        countDownLatch = new CountDownLatch(1);
        firebaseUtil = new FirebaseUtil();
    }

    /**
     * Called by the server close a room.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the server's request.
     * @throws ServletException If there is an error updating the database when closing a room.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String roomId = request.getParameter("roomId");
        firebaseUtil.getRoomsReference().child(roomId).child("isOpen")
                .setValue(Boolean.FALSE, (error, reference) -> countDownLatch.countDown());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new ServletException("There was an error updating the database when closing the room.");
        }
        
        response.sendRedirect("/");
    }
}
