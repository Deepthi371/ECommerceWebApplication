package com.ecommerce.dao;

import com.ecommerce.model.Model;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Model> getAllProducts() {
        List<Model> products = new ArrayList<>();

        // Dummy products for testing
        products.add(new Model(1, "Wireless Mouse", "Logitech", 799.99));
        products.add(new Model(2, "Mechanical Keyboard", "Corsair", 4999.00));
        products.add(new Model(3, "Gaming Headset", "HyperX", 2999.50));
        products.add(new Model(4, "4K Monitor", "Dell", 15999.00));
        products.add(new Model(5, "USB-C Hub", "Anker", 1499.00));

        return products;
    }
}

