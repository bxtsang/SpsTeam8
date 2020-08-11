package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.DeleteOrderProto.DeleteOrderResponse;
import com.google.sps.proto.DeleteOrderProto.DeleteOrderRequest;

/**
 * An interface to handle dependency injection in JoinServlet.
 */
public interface DeleteOrderService {
    public DeleteOrderResponse execute(DeleteOrderRequest deleteOrderRequest) throws ServletException;
}
