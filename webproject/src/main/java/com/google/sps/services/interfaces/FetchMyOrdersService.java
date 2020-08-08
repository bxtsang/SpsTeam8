package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.FetchOrdersProto.FetchOrdersResponse;
import com.google.sps.proto.FetchOrdersProto.FetchOrdersRequest;


public interface FetchMyOrdersService {
    public FetchOrdersResponse execute(FetchOrdersRequest fetchOrdersRequest) throws ServletException;
}
