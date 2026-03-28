package com.ecommerce.model;

public class Product {
    private int productId;
    private String name;
    private String brand;
    private double price;
    private double rating;
    private String description;
    private String imageUrl;

    public Product(String name, String brand, double price, double rating, String description, String imageUrl) {
        this.productId = 0;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
}
