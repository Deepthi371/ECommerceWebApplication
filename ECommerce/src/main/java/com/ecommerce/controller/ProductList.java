package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ensure user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userEmail") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Get category and subcategory parameters, with fallback defaults
        String category = request.getParameter("category");
        String subcategory = request.getParameter("subcategory");
        if (category == null || category.trim().isEmpty() || subcategory == null || subcategory.trim().isEmpty()) {
            category = "Electronics";
            subcategory = "Mobile";
        }

        try {
            // Fetch products from model
            List<Product> products = Model.getProductsByCategory(category, subcategory);

            if (products == null) {
                products = List.of();  // empty list if null returned
            }

            // Set products as request attribute and forward to JSP
            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Log error and show error page or message
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error loading products.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
