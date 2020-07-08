package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;

/**
 * This class defines room objects
 */
public class Room {
  private String id;
  private String title;
  private String link;
  private String description;
  private int deliveryLocation; //modify to use different representation of location if needed
  private int phoneNumber; //modify to use different representation of contact details if needed
  private Category category;
  private double minPrice;

  private Room (Builder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.link = builder.link;
    this.description = builder.description;
    this.deliveryLocation = builder.deliveryLocation;
    this.phoneNumber = builder.phoneNumber;
    this.category = builder.category;
    this.minPrice = builder.minPrice;
  }

  public Entity toEntity() {
    Entity roomEntity = new Entity("Room");

    roomEntity.setProperty("id", id);
    roomEntity.setProperty("title", title);
    roomEntity.setProperty("link", link);
    roomEntity.setProperty("description", description);
    roomEntity.setProperty("deliveryLocation", deliveryLocation);
    roomEntity.setProperty("phoneNumber", phoneNumber);
    roomEntity.setProperty("category", category);
    roomEntity.setProperty("minPrice", minPrice);

    return roomEntity;
  }

  public String getId() {return id;}

  public String getTitle() {return title;}

  public String getLink() {return link;}

  public String getDescription() {return description;}

  public int getDeliveryLocation() {return deliveryLocation;}

  public int getPhoneNumber() {return phoneNumber;}

  public Category getCategory() {return category;}

  public double getMinPrice() {return minPrice;}

  public static class Builder {
    private final String id;
    private String title;
    private String link;
    private String description;
    private int deliveryLocation; //modify to use different representation of location if needed
    private int phoneNumber; //modify to use different representation of contact details if needed
    private Category category;
    private double minPrice;

    public Builder(String id) {
      this.id = id;
    }

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

    public Room build() {
      return new Room(this);
    }
  }
}
