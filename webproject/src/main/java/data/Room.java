package data;

import java.io.File;
import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

/**
 * This class defines room objects
 */
public class Room {
  private static Gson gson = new Gson();

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

  private Room (Builder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.link = builder.link;
    this.description = builder.description;
    this.deliveryLocation = builder.deliveryLocation;
    this.phoneNumber = builder.phoneNumber;
    this.category = builder.category;
    this.imagePath = builder.imagePath;
    this.roomType = builder.roomType;
    this.roomCondition = builder.roomCondition;
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
    roomEntity.setProperty("imagePath", imagePath);
    roomEntity.setProperty("roomType", roomType.toString());
    roomEntity.setProperty("roomCondition", gson.toJson(roomCondition));

    return roomEntity;
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

  public static class Builder {
    private final String id;
    private String title;
    private String link;
    private String description;
    private int deliveryLocation; //modify to use different representation of location if needed
    private int phoneNumber; //modify to use different representation of contact details if needed
    private String category;
    private String imagePath;
    private RoomType roomType;
    private RoomCondition roomCondition;

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

    public Builder setCategory(String category) {
      this.category = category;
      return this;
    }

    public Builder setImagePath(String imagePath) {
      this.imagePath = imagePath;
      return this;
    }

    public Builder setRoomType(RoomType roomType) {
      this.roomType = roomType;
      return this;
    }

    public Builder setRoomCondition(RoomCondition roomCondition) {
      this.roomCondition = roomCondition;
      return this;
    }

    public Room build() {
      return new Room(this);
    }
  }
}
