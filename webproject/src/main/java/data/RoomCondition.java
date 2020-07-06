package data;

/**
 * This class specifies the conditions for the room to be allowed to place an order
 */
public class RoomCondition {
  private double minPrice;
  private int minUsers;

  public RoomCondition(double minPrice, int minUsers) {
    this.minPrice = minPrice;
    this.minUsers = minUsers;
  }
}
