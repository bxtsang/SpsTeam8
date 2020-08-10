package com.google.sps.dataManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.sps.data.OrderProto.Order;
import com.google.sps.util.FirebaseUtil;

@Singleton
public class OrderManager {
    private FirebaseUtil firebaseUtil;

    @Inject
    public OrderManager(FirebaseUtil firebaseUtil) {
        this.firebaseUtil = firebaseUtil;
    }

    public Order addOrder(String userEmail, String product, int quantity, double unitPrice,
                        String roomId) throws ServletException {
        if (!isRoomIdValid(roomId)) {
            throw new ServletException("Invalid roomId");
        }
     
        String userEmailRoomId = userEmail + "_" + roomId;
        double orderPrice = unitPrice * quantity;
        
        String quantityString = String.valueOf(quantity);
        String unitPriceString = String.valueOf(unitPrice);
        String orderPriceString = String.valueOf(orderPrice);
        Map<String, String> orderMapping = new HashMap<>();
        orderMapping.put("userEmail", userEmail);
        orderMapping.put("product", product);        
        orderMapping.put("quantity", quantityString);
        orderMapping.put("unitPrice", unitPriceString);
        orderMapping.put("orderPrice", orderPriceString);
        orderMapping.put("roomId", roomId);
        orderMapping.put("userEmailRoomId", userEmailRoomId);

        firebaseUtil.getOrdersReference().push()
                .setValue(orderMapping, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        try {
                            throw new ServletException("Invalid roomId");
                        } catch (ServletException e) {
                            e.printStackTrace();
                        }
                    }
                });
                
        return Order.newBuilder()
                    .setUserEmail(userEmail)
                    .setProduct(product)
                    .setQuantity(quantity)
                    .setUnitPrice(unitPrice)
                    .setOrderPrice(orderPrice)
                    .setRoomId(roomId)
                    .setUserEmailRoomId(userEmailRoomId)
                    .build();
    }

    public List<Order> getMyOrders(String roomId, String userEmail) throws ServletException {
        String userEmailRoomId = userEmail + "_" + roomId;
        Query query = firebaseUtil.getOrdersReference().orderByChild("userEmailRoomId");
        Optional<List<DataSnapshot>> dataSnapshots = firebaseUtil.getAllQuerySnapshots(query, userEmailRoomId);

        if (!dataSnapshots.isPresent()) {
            return null;
        }

        return dataSnapshots.get().stream().map(this::toOrder).collect(Collectors.toList());
    }

    private Order toOrder(DataSnapshot dataSnapshot) {
        String userEmail = dataSnapshot.child("userEmail").getValue(String.class);
        String product = dataSnapshot.child("product").getValue(String.class);
        int quantity = Integer.parseInt(dataSnapshot.child("quantity").getValue(String.class));
        double unitPrice = Double.parseDouble(dataSnapshot.child("unitPrice").getValue(String.class));
        double orderPrice = Double.parseDouble(dataSnapshot.child("orderPrice").getValue(String.class));
        String roomId = dataSnapshot.child("roomId").getValue(String.class);
        String userEmailRoomId = dataSnapshot.child("userEmailRoomId").getValue(String.class);

        Order orderObject = Order.newBuilder()
                                    .setUserEmail(userEmail)
                                    .setProduct(product)
                                    .setQuantity(quantity)
                                    .setUnitPrice(unitPrice)
                                    .setOrderPrice(orderPrice)
                                    .setRoomId(roomId)
                                    .setUserEmailRoomId(userEmailRoomId)
                                    .build();

        return orderObject;
    }

    private boolean isRoomIdValid(String roomId) throws ServletException {
        Query query = firebaseUtil.getRoomsReference().orderByKey();
        Optional<DataSnapshot> room = firebaseUtil.getQuerySnapshot(query, roomId);
        return room.isPresent();
    }
}
