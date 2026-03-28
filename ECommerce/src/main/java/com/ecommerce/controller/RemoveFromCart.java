package com.ecommerce.controller;

import com.ecommerce.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");

        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart != null && name != null && brand != null) {
            cart.removeIf(p -> p.getName().equals(name) && p.getBrand().equals(brand));
            session.setAttribute("cart", cart);
        }

        response.sendRedirect("cart.jsp");
    }
}
