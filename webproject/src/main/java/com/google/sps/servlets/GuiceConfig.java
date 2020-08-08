package com.google.sps.servlets;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.sps.services.implementations.UserRoomStatusServiceImpl;
import com.google.sps.services.implementations.JoinRoomServiceImpl;
import com.google.sps.services.implementations.FetchMyOrdersServiceImpl;
import com.google.sps.services.implementations.PostOrderServiceImpl;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.services.interfaces.JoinRoomService;
import com.google.sps.services.interfaces.FetchMyOrdersService;
import com.google.sps.services.interfaces.PostOrderService;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                super.configureServlets();

                bind(JoinRoomService.class).to(JoinRoomServiceImpl.class);
                bind(UserRoomStatusService.class).to(UserRoomStatusServiceImpl.class);
                bind(FetchMyOrdersService.class).to(FetchMyOrdersServiceImpl.class);
                bind(PostOrderService.class).to(PostOrderServiceImpl.class);

                serve("/landing").with(LandingServlet.class);
                serve("/joinRoom").with(JoinRoomServlet.class);
                serve("/userRoomStatus").with(UserRoomStatusServlet.class);
                serve("/order").with(PostOrderServlet.class);
                serve("/myOrder").with(MyOrderServlet.class);
            }
        });
    }
}
