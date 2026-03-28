package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if(cart == null || cart.isEmpty()) {
            request.setAttribute("errorMessage", "Your cart is empty. Add items before checkout.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        // You can later get customer info from login
        double total = 0;
        for(CartItem item : cart) {
            total += item.getPrice() * item.getQuantity();
        }

        // Example order ID
        int orderId = (int)(Math.random()*100000);
        session.removeAttribute("cart");

        request.setAttribute("orderId", orderId);
        request.setAttribute("totalAmount", String.format("%.2f", total));

        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
}
