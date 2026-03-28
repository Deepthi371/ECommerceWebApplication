<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ecommerce.model.CartItem"%>
<%@ page session="true" %>

<%
    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<style>
/* Clean styling */
body { font-family: 'Segoe UI', sans-serif; background:#f0f4f8; margin:0; }
.container { max-width:1000px; margin:50px auto; padding:30px; background:#fff; border-radius:12px; box-shadow:0 6px 15px rgba(0,0,0,0.1);}
h1 { text-align:center; color:#1e3a8a; margin-bottom:25px; }
table { width:100%; border-collapse: collapse; }
th, td { padding:12px; border:1px solid #ccc; text-align:center; }
th { background-color:#1e3a8a; color:#fff; }
td img { width:80px; height:80px; object-fit:contain; border-radius:6px; }
.total-row td { font-weight:bold; font-size:18px; background-color:#f1f5f9; }
.btn { display:inline-block; padding:12px 25px; margin:15px 5px; background-color:#1e3a8a; color:#fff; text-decoration:none; border-radius:8px; transition:0.3s; }
.btn:hover { background-color:#3749a3; }
.message { text-align:center; font-size:18px; color:#1e3a8a; margin-top:20px; }
</style>
</head>
<body>
<div class="container">
<h1>Your Shopping Cart</h1>

<% if(cart == null || cart.isEmpty()) { %>
    <p class="message">Your cart is empty. <a href="Category" class="btn">Start Shopping</a></p>
<% } else { %>
    <table>
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Brand</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
        </tr>
        <%
            double total = 0;
            for(CartItem item : cart) {
                double subtotal = item.getPrice() * item.getQuantity();
                total += subtotal;
        %>
        <tr>
            <td><img src="<%=item.getImageUrl()%>" alt="<%=item.getName()%>"></td>
            <td><%=item.getName()%></td>
            <td><%=item.getBrand()%></td>
            <td>&#8377;<%=String.format("%.2f", item.getPrice())%></td>
            <td><%=item.getQuantity()%></td>
            <td>&#8377;<%=String.format("%.2f", subtotal)%></td>
        </tr>
        <% } %>
        <tr class="total-row">
            <td colspan="5">Total</td>
            <td>&#8377;<%=String.format("%.2f", total)%></td>
        </tr>
    </table>

    <div style="text-align:center;">
        <a href="Category" class="btn">Continue Shopping</a>
        <a href="CheckoutServlet" class="btn">Proceed to Checkout</a>
    </div>
<% } %>
</div>
</body>
</html>
