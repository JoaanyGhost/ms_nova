package com.msnova.backend.domain.entity.product;

public class ProductVariant {

    private String size;
    private String color;
    private int stock;

    // Getters and setters


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}