<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // Ideally, orderId should come from request attribute or session, fallback to timestamp
    Object orderIdObj = request.getAttribute("orderId");
    String orderIdStr = (orderIdObj != null) ? "ORD" + orderIdObj.toString() : "ORD" + System.currentTimeMillis();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Thank You for Shopping!</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 50px 60px;
            border-radius: 16px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.15);
            text-align: center;
            width: 500px;
        }

        h1 {
            color: #28a745;
            font-size: 30px;
            margin-bottom: 15px;
        }

        p {
            color: #555;
            font-size: 18px;
            margin-bottom: 25px;
            line-height: 1.6;
        }

        .emoji {
            font-size: 60px;
            margin-bottom: 20px;
        }

        .btn {
            background-color: #007bff;
            color: white;
            padding: 12px 25px;
            text-decoration: none;
            border-radius: 8px;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .order-id {
            background-color: #f1f9ff;
            color: #007bff;
            padding: 10px;
            border-radius: 6px;
            display: inline-block;
            font-weight: 600;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="emoji">🎉</div>
    <h1>Thank You for Your Purchase!</h1>
    <p>Your order has been successfully placed. We’ll send you a confirmation email shortly.</p>
    <p>Your Order ID: 
        <span class="order-id"><%= orderIdStr %></span>
    </p>
    <a href="index.jsp" class="btn">Continue Shopping</a>
</div>

</body>
</html>
