package com.google.sps.servlets;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.sps.services.implementations.GetJoinServiceImpl;
import com.google.sps.services.implementations.LandingServiceImpl;
import com.google.sps.services.implementations.PostJoinServiceImpl;
import com.google.sps.services.interfaces.GetJoinService;
import com.google.sps.services.interfaces.LandingService;
import com.google.sps.services.interfaces.PostJoinService;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                super.configureServlets();

                bind(LandingService.class).to(LandingServiceImpl.class);
                bind(PostJoinService.class).to(PostJoinServiceImpl.class);
                bind(GetJoinService.class).to(GetJoinServiceImpl.class);

                serve("/landing").with(LandingServlet.class);
                serve("/postJoin").with(PostJoinServlet.class);
                serve("/getJoin").with(GetJoinServlet.class);
            }
        });
    }
}
