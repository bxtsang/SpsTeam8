package com.google.sps.services.implementations;

import javax.inject.Inject;
import javax.servlet.ServletException;

import com.google.sps.data.OrderProto.Order;
import com.google.sps.dataManagers.OrderManager;
import com.google.sps.proto.DeleteOrderProto.DeleteOrderResponse;
import com.google.sps.proto.DeleteOrderProto.DeleteOrderRequest;
import com.google.sps.services.interfaces.DeleteOrderService;

public class DeleteOrderServiceImpl implements DeleteOrderService {
    private OrderManager orderManager;

    @Inject
    public DeleteOrderServiceImpl(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Override
    public DeleteOrderResponse execute(DeleteOrderRequest deleteOrderRequest) throws ServletException {
        Order order = orderManager.deleteOrder(deleteOrderRequest.getOrderId());
        return DeleteOrderResponse.newBuilder().setOrder(order).build();
    }
}
