package com.google.sps.services.implementations;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.sps.authentication.AuthenticationHandler;
import com.google.sps.data.OrderProto.Order;
import com.google.sps.dataManagers.OrderManager;
import com.google.sps.proto.FetchMyOrdersProto.FetchMyOrdersResponse;
import com.google.sps.proto.FetchMyOrdersProto.FetchMyOrdersRequest;
import com.google.sps.services.interfaces.FetchMyOrdersService;

@Singleton
public class FetchMyOrdersServiceImpl implements FetchMyOrdersService {
    private AuthenticationHandler authenticationHandler;
    private OrderManager orderManager;

    @Inject
    public FetchMyOrdersServiceImpl(AuthenticationHandler authenticationHandler,
                                    OrderManager orderManager) {
        this.authenticationHandler = authenticationHandler;
        this.orderManager = orderManager;
    }

    @Override
    public FetchMyOrdersResponse execute(FetchMyOrdersRequest fetchMyOrdersRequest) throws ServletException {
        String userEmail = authenticationHandler.getCurrentUser().getEmail();
        String roomId = fetchMyOrdersRequest.getRoomId();

        List<Order> orders = orderManager.getMyOrders(roomId, userEmail);
        FetchMyOrdersResponse.Builder fetchMyOrdersResponseBuilder = FetchMyOrdersResponse.newBuilder();

        for (Order order : orders) {
            fetchMyOrdersResponseBuilder.addOrders(order);
        }

        return fetchMyOrdersResponseBuilder.build();
    }
}
