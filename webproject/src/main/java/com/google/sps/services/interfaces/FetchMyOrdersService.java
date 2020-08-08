package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.FetchMyOrdersProto.FetchMyOrdersResponse;
import com.google.sps.proto.FetchMyOrdersProto.FetchMyOrdersRequest;


public interface FetchMyOrdersService {
    public FetchMyOrdersResponse execute(FetchMyOrdersRequest fetchMyOrdersRequest) throws ServletException;
}
