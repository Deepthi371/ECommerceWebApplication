<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ecommerce.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%
    String email = (String) session.getAttribute("userEmail");
    if (email == null) {
        response.sendRedirect("login.html");
        return;
    }

    List<Product> products = (List<Product>) request.getAttribute("products");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            background: linear-gradient(135deg, #f7f9fb, #e3f2fd);
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            color: #0b3c5d;
            margin-top: 25px;
        }
        .product {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            margin: 15px;
            display: inline-block;
            width: 220px;
            background: #fff;
            box-shadow: 0 5px 10px rgba(0,0,0,0.1);
            text-align: center;
            transition: transform 0.2s ease;
        }
        .product:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 18px rgba(0,0,0,0.15);
        }
        .product img {
            width: 100%;
            height: 140px;
            object-fit: contain;
            border-radius: 6px;
        }
        .product b {
            color: #0b3c5d;
            font-size: 16px;
        }
        .product p {
            margin: 6px 0;
            font-size: 14px;
            color: #333;
        }
        input[type="number"] {
            width: 60px;
            padding: 4px;
            margin-top: 6px;
        }
        input[type="submit"] {
            background: #1f618d;
            color: white;
            border: none;
            padding: 8px 14px;
            margin-top: 10px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }
        input[type="submit"]:hover {
            background: #154360;
        }
        .back-link {
            display: block;
            text-align: center;
            margin: 30px;
            font-weight: bold;
            text-decoration: none;
            color: #1f618d;
        }
    </style>
</head>
<body>
    <h2>Products for You</h2>

    <% if (products != null && !products.isEmpty()) {
           for (Product p : products) { %>
            <div class="product">
                <img src="<%=p.getImageUrl()%>" alt="<%=p.getName()%>">
                <b><%= p.getName()%></b>
                <p>Brand: <%= p.getBrand()%></p>
                <p>Price: ₹<%= p.getPrice()%></p>
                <p>Rating: ⭐ <%= p.getRating()%></p>
                <form action="AddToCart" method="post">
                    <input type="hidden" name="productId" value="<%= p.getProductId()%>">
                    <label>Qty:</label>
                    <input type="number" name="quantity" value="1" min="1" required><br>
                    <input type="submit" value="Add to Cart">
                </form>
            </div>
    <%   }
       } else { %>
         <p style="text-align:center; margin-top:30px;">No products found for the selected category/sub-category.</p>
    <% } %>

    <a class="back-link" href="subcategories.jsp">← Start Shopping</a>
</body>
</html>