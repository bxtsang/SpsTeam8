package com.google.sps.servlets;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.sps.services.implementations.GetJoinServiceImpl;
import com.google.sps.services.implementations.LandingServiceImpl;
import com.google.sps.services.implementations.JoinRoomRoomServiceImpl;
import com.google.sps.services.interfaces.GetJoinService;
import com.google.sps.services.interfaces.LandingService;
import com.google.sps.services.interfaces.JoinRoomService;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                super.configureServlets();

                bind(JoinRoomService.class).to(JoinRoomRoomServiceImpl.class);
                bind(GetJoinService.class).to(GetJoinServiceImpl.class);

                serve("/landing").with(LandingServlet.class);
                serve("/postJoin").with(JoinRoomServlet.class);
                serve("/getJoin").with(GetJoinServlet.class);
            }
        });
    }
}
