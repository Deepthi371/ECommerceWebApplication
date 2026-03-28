package com.ecommerce.controller;

import jakarta.servlet.*;
import com.ecommerce.model.Product;
import com.ecommerce.model.Model;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/Category")
public class Category extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Categories
        List<String> categories = Arrays.asList("Electronics", "Clothing", "Home Appliances");
        req.setAttribute("categories", categories);

        // Mapping of subcategories
        Map<String, List<String>> subcategories = new HashMap<>();
        subcategories.put("Electronics", Arrays.asList("Mobile", "Laptop", "Camera"));
        subcategories.put("Clothing", Arrays.asList("T-Shirt", "Jeans", "Jacket"));
        subcategories.put("Home Appliances", Arrays.asList("Furniture", "Kitchen", "Decor"));
        req.setAttribute("subcategories", subcategories);

        RequestDispatcher rd = req.getRequestDispatcher("categories.jsp");
        rd.forward(req, res);
    }
}
