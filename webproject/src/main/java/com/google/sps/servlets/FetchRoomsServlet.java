package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.protobuf.util.JsonFormat;
import com.google.sps.data.CategoryProto.Category;
import com.google.sps.data.RoomProto.Room;
import com.google.sps.proto.FetchRoomsProto.FetchRoomsResponse;
import com.google.sps.services.interfaces.FetchRoomsService;
import com.google.sps.util.FirebaseUtil;

/**
 * A servlet which fetches rooms.
 */
@Singleton
public class FetchRoomsServlet extends HttpServlet {
    private FetchRoomsService fetchRoomsService;
    private FirebaseUtil firebaseUtil;

    @Inject
    public FetchRoomsServlet(FetchRoomsService fetchRoomsService, FirebaseUtil firebaseUtil) {
        this.fetchRoomsService = fetchRoomsService;
        this.firebaseUtil = firebaseUtil;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        DatabaseReference ref = firebaseUtil.getRoomsReference();
        List<DataSnapshot> dataSnapshots = firebaseUtil.getAllSnapshotsFromReference(ref);
        FetchRoomsResponse.Builder fetchRoomsResponseBuilder = FetchRoomsResponse.newBuilder();

        for (DataSnapshot dataSnapshot : dataSnapshots) {
            String id = dataSnapshot.getKey();
            String title = dataSnapshot.child("title").getValue(String.class);
            String link = dataSnapshot.child("link").getValue(String.class);
            String description = dataSnapshot.child("description").getValue(String.class);
            int deliveryLocation = dataSnapshot.child("deliveryLocation").getValue(int.class);
            int phoneNumber = dataSnapshot.child("phoneNumber").getValue(int.class);
            Category category = dataSnapshot.child("category").getValue(Category.class);
            double minPrice = dataSnapshot.child("minPrice").getValue(double.class);
            double deliveryFee = dataSnapshot.child("deliveryFee").getValue(double.class);
            boolean isOpen = dataSnapshot.child("isOpen").getValue(boolean.class);
            double ordersValue = dataSnapshot.child("ordersValue").getValue(double.class);
            long timestamp = dataSnapshot.child("timestamp").getValue(long.class);

            List<String> users = new ArrayList<>();
            for (DataSnapshot data : dataSnapshot.child("users").getChildren()) {
                users.add(data.getValue(String.class));
            }

            Room.Builder roomBuilder = Room.newBuilder()
                    .setId(id)
                    .setTitle(title)
                    .setLink(link)
                    .setDescription(description)
                    .setDeliveryLocation(deliveryLocation)
                    .setPhoneNumber(phoneNumber)
                    .setCategory(category)
                    .setMinPrice(minPrice)
                    .setDeliveryFee(deliveryFee)
                    .setIsOpen(isOpen)
                    .setOrdersValue(ordersValue)
                    .setTimestamp(timestamp);

            for (String user : users) {
                roomBuilder.addUsers(user);
            }

            fetchRoomsResponseBuilder.addRooms(roomBuilder.build());
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JsonFormat.printer().print(fetchRoomsResponseBuilder.build()));
        response.setStatus(200);
    }
}
