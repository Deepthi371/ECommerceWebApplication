package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String imageUrl = request.getParameter("imageUrl");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        // Check if item already exists, update quantity
        boolean found = false;
        for(CartItem item : cart) {
            if(item.getName().equals(name) && item.getBrand().equals(brand)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if(!found) {
            cart.add(new CartItem(name, brand, price, quantity, imageUrl));
        }

        response.sendRedirect("cart.jsp");
    }
}
