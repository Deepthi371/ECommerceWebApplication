<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    Integer orderId = (Integer) request.getAttribute("orderId");
    String totalAmount = (String) request.getAttribute("totalAmount");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<style>
body { font-family:'Segoe UI',sans-serif; background:#f0f4f8; margin:0; }
.container { max-width:600px; margin:50px auto; padding:30px; background:#fff; border-radius:12px; box-shadow:0 6px 15px rgba(0,0,0,0.1);}
h1 { text-align:center; color:#1e3a8a; margin-bottom:25px; }
.message { text-align:center; font-size:18px; color:#1e3a8a; }
.btn { display:inline-block; padding:12px 25px; margin:15px 5px; background-color:#1e3a8a; color:#fff; text-decoration:none; border-radius:8px; transition:0.3s; }
.btn:hover { background-color:#3749a3; }
</style>
</head>
<body>
<div class="container">
<h1>Checkout Complete</h1>

<% if(orderId != null && totalAmount != null) { %>
    <p class="message">Thank you for your purchase!<br>
    Your Order ID is <strong><%=orderId%></strong><br>
    Total Paid: &#8377;<%=totalAmount%></p>
<% } else { %>
    <p class="message">No order found.</p>
<% } %>

<p style="text-align:center;"><a href="Category" class="btn">Continue Shopping</a></p>
</div>
</body>
</html>
