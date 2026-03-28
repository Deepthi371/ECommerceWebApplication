package com.ecommerce.controller;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Collect form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");   // added phone
        String password = request.getParameter("password");

        

        // Create user object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);       // set phone
        user.setPassword(password);

        // Save user
        UserDAO dao = new UserDAO();
        try {
            dao.saveUser(user);
            // Redirect to login after successful registration
            response.sendRedirect("RegisterSuccess.html");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error while registering. Please try again.");
            request.getRequestDispatcher("RegisterFail.html").forward(request, response);
        }
    }
}
