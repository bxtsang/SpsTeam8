package com.google.sps.data;

public class Order {
    private String userEmail;
    private String product;
    private int quantity;
    private double unitPrice;
    private double orderPrice;

    private Order(Builder builder) {
        this.userEmail = builder.userEmail;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.orderPrice = quantity * unitPrice;
    }

    public String getUser() {return userEmail;}

    public String getProduct() {return product;}

    public int getQuantity() {return quantity;}

    public double getUnitPrice() {return unitPrice;}

    public double getOrderPrice() {return orderPrice;}

    public static Builder newBuilder() {return new Builder();}

    public static class Builder {
        private String userEmail;
        private String product;
        private int quantity;
        private double unitPrice;

        public Builder setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder setProduct(String product) {
            this.product = product;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
