package com.ecommerce.controller;

import com.ecommerce.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Model model = new Model();

        if (model.validateUser(email, password)) {
            // ✅ User is valid, create session
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);

            // ✅ Redirect to category/subcategory selection page
            response.sendRedirect("LoginSuccess.html");
        } else {
            // ❌ Invalid login, send back to login page with error
            response.sendRedirect("login.html?error=invalid");
        }
    }
}
