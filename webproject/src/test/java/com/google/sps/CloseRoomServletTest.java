package com.google.sps.servlets;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.sps.proto.CloseRoomProto.CloseRoomResponse;
import com.google.sps.proto.CloseRoomProto.CloseRoomRequest;
import com.google.sps.services.interfaces.CloseRoomService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration test for CloseRoomServlet.
 */
public class CloseRoomServletTest {
    private CloseRoomServlet closeRoomServlet;
    private CloseRoomService closeRoomService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    @Before
    public void setUp() throws Exception {
        closeRoomServlet = new CloseRoomServlet(new CloseRoomServiceStub());
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        when (request.getParameter("roomId")).thenReturn("test room id");
    }

    @Test
    public void doPost_closesRoomAndRedirectsToEntryPage() throws IOException, ServletException {

        // Arrange
        LocalServiceTestHelper helper = new LocalServiceTestHelper();
        helper.setUp();

        // Act
        closeRoomServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("/");
        helper.tearDown();
    }

    private static class CloseRoomServiceStub implements CloseRoomService {
        @Override
        public CloseRoomResponse execute(CloseRoomRequest closeRoomRequest) throws ServletException {
            return CloseRoomResponse.newBuilder()
                .setRoomId(closeRoomRequest.getRoomId())
                .build();
        }
    }
}
