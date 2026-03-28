package com.ecommerce.model;

public class User {
    private String name;
    private String email;
    private String address;
    private String password;
    private String phone; 

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }               // ← Add this
    public void setPhone(String phone) { this.phone = phone; } // ← Add this
}
