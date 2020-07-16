package com.google.sps.data;

public class Order {
    private String user; // change to User class once it is set up
    private String product;
    private int quantity;
    private double unitPrice;
    private double orderPrice;

    private Order(Builder builder) {
        this.user = builder.user;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.orderPrice = quantity * unitPrice;
    }

    private static class Builder {
        private String user; // change to User class once set up
        private String product;
        private int quantity;
        private double unitPrice;

        public Builder setUser(String user) {
            this.user = user;
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
