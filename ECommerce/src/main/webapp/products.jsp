<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Product" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (products == null || products.isEmpty()) {
%>
    <p>No products found.</p>
<%
    } else {
%>
    <div style="display: flex; flex-wrap: wrap; gap: 20px; justify-content:center;">
<%
        for (Product p : products) {
%>
        <div style="border: 1px solid #ccc; border-radius: 10px; padding: 15px; width: 220px; background: #f9f9f9; text-align: center;">
            <img src="<%= p.getImageUrl() %>" alt="<%= p.getName() %>" style="width:100%; height:150px; object-fit:cover;"><br>
            <h3><%= p.getName() %></h3>
            <p>Brand: <%= p.getBrand() %></p>
            <p>Price: ₹<%= p.getPrice() %></p>

            <!-- ✅ Add to Cart form -->
            <form action="AddToCart" method="post">
    			<input type="hidden" name="name" value="<%= p.getName() %>">
    			<input type="hidden" name="brand" value="<%= p.getBrand() %>">
    			<input type="hidden" name="price" value="<%= p.getPrice() %>">
    			<input type="hidden" name="imageUrl" value="<%= p.getImageUrl() %>"> <!-- NEW -->
    			<label>Quantity:</label>
    			<input type="number" name="quantity" value="1" min="1" required>
    			<button type="submit">Add to Cart</button>
			</form>
            
        </div>
<%
        }
%>
    </div>
<%
    }
%>