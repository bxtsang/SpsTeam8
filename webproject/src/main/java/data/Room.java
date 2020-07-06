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
}
