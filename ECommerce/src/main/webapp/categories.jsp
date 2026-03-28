<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ecommerce.model.Product" %>
<%@ page import="com.ecommerce.model.Model" %>

<%
    String category = request.getParameter("category");
    String subcategory = request.getParameter("subcategory");
    List<Product> products = null;
    if(category != null && subcategory != null){
        products = Model.getProductsByCategory(category, subcategory);
    }
    String email = (String) session.getAttribute("userEmail"); // login check
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Select Category & Subcategory</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #f0f4f8, #d9e2ec);
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 20px;
        }
        h2, h3 {
            text-align: center;
            color: #1f618d;
        }
        form {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 12px;
            margin-bottom: 30px;
        }
        select, input[type="submit"] {
            padding: 10px 14px;
            font-size: 15px;
            border-radius: 6px;
            border: 1px solid #ccc;
            outline: none;
            transition: 0.3s;
        }
        select:focus { border-color: #1f618d; }
        input[type="submit"] {
            background: #1f618d;
            color: #fff;
            cursor: pointer;
            font-weight: bold;
            border: none;
        }
        input[type="submit"]:hover { background: #154360; }

        .product-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 25px;
        }
        .product-card {
            background: #fff;
            border-radius: 14px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.12);
            width: 260px;
            padding: 18px;
            display: flex;
            flex-direction: column;
            align-items: center;
            transition: transform 0.25s, box-shadow 0.25s;
        }
        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 25px rgba(0,0,0,0.18);
        }
        .product-card img {
            width: 100%;
            height: 180px;
            object-fit: contain;
            border-radius: 8px;
        }
        .product-info {
            margin-top: 12px;
            text-align: center;
            width: 100%;
        }
        .product-info h3 {
            margin: 6px 0;
            font-size: 18px;
            color: #0b3c5d;
        }
        .product-info p.brand {
            margin: 3px 0;
            color: #34495e;
            font-size: 14px;
        }
        .product-info p.price {
            margin: 5px 0;
            font-weight: bold;
            color: #1f618d;
            font-size: 16px;
        }
        .product-info p.desc {
            font-size: 13px;
            color: #6c7a89;
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .product-info form {
            margin-top: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 6px;
        }
        .product-info input[type="number"] {
            width: 60px;
            padding: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
            outline: none;
        }
        .product-info button {
            padding: 6px 12px;
            background-color: #1f618d;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }
        .product-info button:hover {
            background-color: #154360;
        }
        .login-msg {
            color: red;
            text-align: center;
            font-weight: bold;
            margin-top: 8px;
        }
        @media(max-width: 650px) {
            .product-card { width: 90%; }
            form { flex-direction: column; align-items: center; }
        }
    </style>
    <script>
        function updateSubcategories() {
            const subs = {
                "Electronics": ["Mobile", "Laptop", "Camera"],
                "Clothing": ["Men Wear", "Women Wear", "Kids Wear"],
                "Home Appliances": ["Furniture", "Kitchen", "Decor"]
            };
            const cat = document.getElementById("category").value;
            const subSelect = document.getElementById("subcategory");
            subSelect.innerHTML = "";
            subs[cat].forEach(sub => {
                const option = document.createElement("option");
                option.value = sub;
                option.text = sub;
                subSelect.add(option);
            });
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Select Category & Subcategory</h2>
    <form method="get">
        <select name="category" id="category" onchange="updateSubcategories()">
            <option value="Electronics" <%= "Electronics".equals(category)?"selected":"" %>>Electronics</option>
            <option value="Clothing" <%= "Clothing".equals(category)?"selected":"" %>>Clothing</option>
            <option value="Home Appliances" <%= "Home Appliances".equals(category)?"selected":"" %>>Home Appliances</option>
        </select>
        <select name="subcategory" id="subcategory"></select>
        <input type="submit" value="Load Products">
    </form>

    <%
        if(products != null && !products.isEmpty()) {
    %>
        <h3>Products in <%= category %> - <%= subcategory %></h3>
        <div class="product-container">
        <%
            for(Product p : products) {
        %>
            <div class="product-card">
                <img src="<%= p.getImageUrl() %>" alt="<%= p.getName() %>">
                <div class="product-info">
                    <h3><%= p.getName() %></h3>
                    <p class="brand"><%= p.getBrand() %></p>
                    <p class="price">&#8377;<%= p.getPrice() %></p>
                    <p class="desc"><%= p.getDescription() %></p>
                    <p><strong>Rating:</strong> ⭐ <%= p.getRating() %></p>

                    <% if(email == null) { %>
                        <p class="login-msg">Please <a href="login.html">login</a> to add to cart.</p>
                    <% } else { %>
                        <form action="AddToCart" method="post">
    						<input type="hidden" name="name" value="<%= p.getName() %>">
   							<input type="hidden" name="brand" value="<%= p.getBrand() %>">
    						<input type="hidden" name="price" value="<%= p.getPrice() %>">
    						<label>Quantity:</label>
    						<input type="number" name="quantity" value="1" min="1" required>
    						<button type="submit">Add to Cart</button>
						</form>
                    <% } %>
                </div>
            </div>
        <%
            }
        %>
        </div>
    <%
        } else if(category != null && subcategory != null) {
    %>
        <p style="text-align:center; margin-top:20px;">No products found in this category/subcategory.</p>
    <%
        }
    %>
</div>

<script>
    updateSubcategories();
    <% if(subcategory != null) { %>
        document.getElementById("subcategory").value = "<%= subcategory %>";
    <% } %>
</script>
</body>
</html>
