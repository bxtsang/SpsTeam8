package com.google.sps.servlets;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import com.google.sps.services.implementations.CloseRoomServiceImpl;
import com.google.sps.services.implementations.UserRoomStatusServiceImpl;
import com.google.sps.services.implementations.UsernameServiceImpl;
import com.google.sps.services.implementations.JoinRoomServiceImpl;
import com.google.sps.services.interfaces.CloseRoomService;
import com.google.sps.services.interfaces.UserRoomStatusService;
import com.google.sps.services.interfaces.UsernameService;
import com.google.sps.services.interfaces.JoinRoomService;

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
                bind(UsernameService.class).to(UsernameServiceImpl.class);

                serve("/landing").with(LandingServlet.class);
                serve("/joinRoom").with(JoinRoomServlet.class);
                serve("/userRoomStatus").with(UserRoomStatusServlet.class);
                serve("/closeRoom").with(CloseRoomServlet.class);
                serve("/username").with(UsernameServlet.class);
            }
        });
    }
}
