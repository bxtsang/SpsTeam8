package com.google.sps.services.implementations;

import javax.inject.Inject;
import javax.servlet.ServletException;

import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.OrderProto.Order;
import com.google.sps.dataManagers.OrderManager;
import com.google.sps.proto.PostOrderProto.PostOrderResponse;
import com.google.sps.proto.PostOrderProto.PostOrderRequest;
import com.google.sps.services.interfaces.PostOrderService;

public class PostOrderServiceImpl implements PostOrderService {
    private AuthenticationHandler authenticationHandler;
    private OrderManager orderManager;

    @Inject
    public PostOrderServiceImpl(AuthenticationHandler authenticationHandler,
                               OrderManager orderManager) {
        this.authenticationHandler = authenticationHandler;
        this.orderManager = orderManager;
    }

    @Override
    public PostOrderResponse execute(PostOrderRequest postOrderRequest) throws ServletException {
        String userEmail = authenticationHandler.getCurrentUser().getEmail();
        Order order = orderManager.addOrder(userEmail, postOrderRequest.getProduct(),
                                            postOrderRequest.getQuantity(),
                                            postOrderRequest.getUnitPrice(),
                                            postOrderRequest.getRoomId());
        return PostOrderResponse.newBuilder().setOrder(order).build();
    }
}
