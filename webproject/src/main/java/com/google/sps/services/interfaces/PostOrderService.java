package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.PostOrderProto.PostOrderResponse;
import com.google.sps.proto.PostOrderProto.PostOrderRequest;

/**
 * An interface to handle dependency injection in JoinServlet.
 */
public interface PostOrderService {
    public PostOrderResponse execute(PostOrderRequest postOrderRequest) throws ServletException;
}
