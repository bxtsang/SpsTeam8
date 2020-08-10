package com.google.sps.services.interfaces;

import javax.servlet.ServletException;

import com.google.sps.proto.FetchRoomsProto.FetchRoomsResponse;

public interface FetchRoomsService {
    public FetchRoomsResponse execute() throws ServletException;
}
