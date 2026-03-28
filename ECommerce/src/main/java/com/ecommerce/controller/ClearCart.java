package com.ecommerce.controller;

import com.ecommerce.model.Model;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/ClearCart")
public class ClearCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Model m = new Model();
        boolean cleared = m.clearCart();

        if (cleared) {
            // Redirect to cart page after clearing
            response.sendRedirect("cart.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
