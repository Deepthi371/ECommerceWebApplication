<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.ecommerce.model.Model" %>
<%@ page session="true" %>
<%
    String email = (String) session.getAttribute("userEmail");
    if(email == null){
        response.sendRedirect("login.html");
        return;
    }

    Model m = new Model();
    List<Map<String,Object>> orders = m.getOrdersByEmail(email);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>My Orders</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f2f2f2; }
    .container { max-width: 900px; margin: 30px auto; background: #fff; padding: 20px; border-radius: 8px; }
    h1 { text-align: center; margin-bottom: 20px; }
    table { width: 100%; border-collapse: collapse; margin-bottom: 40px; }
    th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }
    th { background-color: #2193b0; color: white; }
    .order-title { background: #6dd5ed; font-weight: bold; }
</style>
</head>
<body>
<div class="container">
    <h1>My Orders</h1>
    <%
        if(orders.isEmpty()){
    %>
        <p>No orders placed yet.</p>
    <%
        } else {
            for(Map<String,Object> order : orders){
                int orderId = (Integer) order.get("id");
                double total = (Double) order.get("total");
                java.sql.Timestamp date = (java.sql.Timestamp) order.get("date");
    %>
    <table>
        <tr class="order-title">
            <td colspan="4">Order ID: <%=orderId%> | Date: <%=date%> | Total: ₹<%=total%></td>
        </tr>
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Total Price</th>
        </tr>
        <%
            // Fetch order items for this order
            List<Model> items = m.getOrderItems(orderId);
            for(Model item : items){
        %>
        <tr>
            <td><%=item.getName()%></td>
            <td><%=item.getQuantity()%></td>
            <td>₹<%=item.getPrice()%></td>
            <td>₹<%=item.getPrice() * item.getQuantity()%></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
            }
        }
    %>
</div>
</body>
</html>
    