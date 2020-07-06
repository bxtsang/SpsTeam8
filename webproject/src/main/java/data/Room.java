package data;

import java.io.File;

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
  private String category;
  private String imagePath;
  private RoomType roomType;
  private RoomCondition roomCondition;

  public Room (String id, String title, String link, String description, int deliveryLocation, int phoneNumber, String category, String imagePath, RoomType roomType, RoomCondition roomCondition) {
    this.id = id;
    this.title = title;
    this.link = link;
    this.description = description;
    this.deliveryLocation = deliveryLocation;
    this.phoneNumber = phoneNumber;
    this.category = category;
    this.imagePath = imagePath;
    this.roomType = roomType;
    this.roomCondition = roomCondition;
  }

  public String getId() {return id;}

  public String getTitle() {return title;}

  public String getLink() {return link;}

  public String getDescription() {return description;}

  public int getDeliveryLocation() {return deliveryLocation;}

  public int getPhoneNumber() {return phoneNumber;}

  public String getCategory() {return category;}

  public String getImagePath() {return imagePath;}

  public RoomType getRoomType() {return roomType;}

  public RoomCondition getRoomCondition() {return roomCondition;}
}
