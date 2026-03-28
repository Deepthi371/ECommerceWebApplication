<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // Check user login session
    String email = (String) session.getAttribute("userEmail");

    // Extract username (before '@')
    String username = null;
    if (email != null && email.contains("@")) {
        username = email.substring(0, email.indexOf("@"));
    } else {
        username = email;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome | My E-Commerce Store</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #74ebd5, #ACB6E5);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            color: #fff;
        }
        .container {
            background: rgba(0, 0, 0, 0.6);
            padding: 40px;
            border-radius: 15px;
            width: 90%;
            max-width: 480px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
        }
        h1 {
            font-size: 32px;
            margin-bottom: 15px;
            letter-spacing: 1px;
        }
        p {
            font-size: 17px;
            margin-bottom: 30px;
        }
        .btn-group {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }
        .btn-group a {
            text-decoration: none;
            background: #ffffff;
            color: #2193b0;
            font-weight: bold;
            padding: 12px 20px;
            border-radius: 8px;
            transition: 0.3s ease;
        }
        .btn-group a:hover {
            background: #e0f7fa;
            transform: translateY(-2px);
        }
        .greeting {
            margin-bottom: 20px;
            font-size: 18px;
            color: #ffeb3b;
        }
        footer {
            text-align: center;
            margin-top: 30px;
            color: #eaeaea;
            font-size: 14px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome to My E-Commerce Store</h1>
    <p>Shop the latest electronics, fashion, and home essentials!</p>

    <div class="btn-group">
        <%
            if (email == null) {
        %>
            <!-- User not logged in -->
            <a href="Register.html">Register</a>
            <a href="login.html">Login</a>
        <%
            } else {
        %>
            <!-- User logged in -->
            <p class="greeting">Hello, <strong><%= username %></strong>!</p>
            <a href="Category">Start Shopping</a>
            <a href="cart.jsp">View Cart</a>
            <a href="LogoutServlet">Logout</a>
        <%
            }
        %>
    </div>
</div>

<footer>
    &copy; 2025 My E-Commerce Store. All rights reserved.
</footer>

</body>
</html>