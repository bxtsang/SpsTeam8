package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;
import com.google.sps.firebase.Firebase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines room objects
 */
public class Room {
    private String title;
    private String link;
    private String description;
    private int deliveryLocation; //modify to use different representation of location if needed
    private int phoneNumber; //modify to use different representation of contact details if needed
    private Category category;
    private double minPrice;
    private double deliveryFee;
    private List<String> users;
    private String creator;
    private double ordersValue;
    private boolean isOpen;

    private static Gson gson = new Gson();

    private Room (Builder builder) {
        this.title = builder.title;
        this.link = builder.link;
        this.description = builder.description;
        this.deliveryLocation = builder.deliveryLocation;
        this.phoneNumber = builder.phoneNumber;
        this.category = builder.category;
        this.minPrice = builder.minPrice;
        this.deliveryFee = builder.deliveryFee;
        this.users = new ArrayList<>();
        this.isOpen = true;
    }
    
    public String getTitle() {return title;}

    public String getLink() {return link;}

    public String getDescription() {return description;}

    public int getDeliveryLocation() {return deliveryLocation;}

    public int getPhoneNumber() {return phoneNumber;}

    public Category getCategory() {return category;}

    public double getMinPrice() {return minPrice;}

    public double getDeliveryFee() {return deliveryFee;}

    public List<String> getUsers() {return users;}

    public String getCreator() {return creator;}

    public double getOrdersValue() {return ordersValue;}

    public void addUser(String userEmail) {
        this.users.add(userEmail);
    }

    public void addOrdersValue(double orderPrice) {
        this.ordersValue += orderPrice;
    }

    public void save() throws IOException {
        String roomJson = gson.toJson(this);
        Firebase.sendRequest("https://summer20-sps-47.firebaseio.com/rooms.json", "POST", roomJson);
    }

    public void save(String roomId) throws IOException {
        String roomJson = gson.toJson(this);
        Firebase.sendRequest("https://summer20-sps-47.firebaseio.com/rooms/" + roomId + ".json", "PUT", roomJson);
    }

    public static Room getRoomById(String roomId) throws IOException {
        String roomData = Firebase.sendGetRequest("https://summer20-sps-47.firebaseio.com/rooms/" + roomId + ".json");
        Room room = gson.fromJson(roomData, Room.class);

        return room;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String link;
        private String description;
        private int deliveryLocation; //modify to use different representation of location if needed
        private int phoneNumber; //modify to use different representation of contact details if needed
        private Category category;
        private double minPrice;
        private double deliveryFee;
        private String creator;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDeliveryLocation(int deliveryLocation) {
            this.deliveryLocation = deliveryLocation;
            return this;
        }

        public Builder setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setMinPrice(double minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public Builder setDeliveryFee(double deliveryFee) {
            this.deliveryFee = deliveryFee;
            return this;
        }

        public Builder setCreator(String creatorEmail) {
            this.creator = creatorEmail;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
