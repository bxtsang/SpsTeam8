package com.google.sps.servlets;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import com.google.sps.services.implementations.FetchRoomsServiceImpl;
import com.google.sps.services.implementations.UserRoomStatusServiceImpl;
import com.google.sps.services.implementations.JoinRoomServiceImpl;
<<<<<<< HEAD
import com.google.sps.services.implementations.FetchMyOrdersServiceImpl;
import com.google.sps.services.implementations.PostOrderServiceImpl;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.services.interfaces.FetchMyOrdersService;
import com.google.sps.services.interfaces.PostOrderService;
=======
import com.google.sps.services.implementations.CloseRoomServiceImpl;

import com.google.sps.services.interfaces.FetchRoomsService;

import com.google.sps.services.implementations.SendTextMessageServiceImpl;
import com.google.sps.services.implementations.FetchMessagesServiceImpl;
import com.google.sps.services.interfaces.SendTextMessageService;
import com.google.sps.services.interfaces.CloseRoomService;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.services.interfaces.FetchMessagesService;
>>>>>>> 720d9c8a9f3ae0f4ddd263ab27bcdb3628167625

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                super.configureServlets();

                bind(CloseRoomService.class).to(CloseRoomServiceImpl.class);
                bind(JoinRoomService.class).to(JoinRoomServiceImpl.class);
                bind(UserRoomStatusService.class).to(UserRoomStatusServiceImpl.class);
<<<<<<< HEAD
                bind(FetchMyOrdersService.class).to(FetchMyOrdersServiceImpl.class);
                bind(PostOrderService.class).to(PostOrderServiceImpl.class);
=======
                bind(FetchRoomsService.class).to(FetchRoomsServiceImpl.class);
                bind(FetchMessagesService.class).to(FetchMessagesServiceImpl.class);
                bind(SendTextMessageService.class).to(SendTextMessageServiceImpl.class);
>>>>>>> 720d9c8a9f3ae0f4ddd263ab27bcdb3628167625

                serve("/landing").with(LandingServlet.class);
                serve("/joinRoom").with(JoinRoomServlet.class);
                serve("/userRoomStatus").with(UserRoomStatusServlet.class);
<<<<<<< HEAD
                serve("/order").with(PostOrderServlet.class);
                serve("/myOrder").with(MyOrderServlet.class);
=======
                serve("/fetchRooms").with(FetchRoomsServlet.class);
                serve("/fetchMessages").with(FetchMessagesServlet.class);
                serve("/sendTextMessage").with(SendTextMessageServlet.class);
                serve("/closeRoom").with(CloseRoomServlet.class);
>>>>>>> 720d9c8a9f3ae0f4ddd263ab27bcdb3628167625
            }
        });
    }
}
