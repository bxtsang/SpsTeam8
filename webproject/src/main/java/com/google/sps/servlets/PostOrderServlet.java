package com.google.sps.servlets;

import com.google.protobuf.util.JsonFormat;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.OrderProto.Order;
import com.google.sps.dataManagers.OrderManager;
import com.google.sps.proto.PostOrderProto.PostOrderResponse;
import com.google.sps.proto.PostOrderProto.PostOrderRequest;
import com.google.sps.services.interfaces.PostOrderService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet which retrieves all Orders made in the Room.
 */
@Singleton
public class PostOrderServlet extends HttpServlet {
    private PostOrderService postOrderService;
    private AuthenticationHandler authenticationHandler;

    @Inject
    public PostOrderServlet(PostOrderService postOrderService, AuthenticationHandler authenticationHandler) {
        this.postOrderService = postOrderService;
        this.authenticationHandler = authenticationHandler;
    }

    /**
     * Called by the server to allow this servlet to handle a GET request from the chatBox page.
     * @param request An HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response An HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!authenticationHandler.isUserLoggedIn()) {
            response.setStatus(400);
            return;
        }
        
        String roomId = request.getParameter("roomId");
        String product = request.getParameter("product");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));

        PostOrderRequest postOrderRequest = PostOrderRequest.newBuilder()
                                                            .setRoomId(roomId)
                                                            .setProduct(product)
                                                            .setQuantity(quantity)
                                                            .setUnitPrice(unitPrice)
                                                            .build();
        PostOrderResponse postOrderResponse = postOrderService.execute(postOrderRequest);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JsonFormat.printer().print(postOrderResponse));
        response.setStatus(200);
    }
}
