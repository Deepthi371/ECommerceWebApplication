package com.ecommerce.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/Subcategory")
public class Subcategory extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");

        // Example subcategories for each category
        Map<String, List<String>> subcats = new HashMap<>();
        subcats.put("Electronics", Arrays.asList("Mobile Phones", "Laptops", "Cameras"));
        subcats.put("Clothing", Arrays.asList("Men", "Women", "Kids"));
        subcats.put("Home Appliances", Arrays.asList("Kitchen", "Living Room", "Cleaning"));

        List<String> subs = subcats.getOrDefault(category, new ArrayList<>());

        req.setAttribute("cat", category);
        req.setAttribute("subs", subs); 
        RequestDispatcher rd = req.getRequestDispatcher("subcategories.jsp");
        rd.forward(req, resp);
    }
}
