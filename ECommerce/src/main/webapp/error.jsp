<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f8d7da;
            color: #721c24;
            text-align: center;
            padding-top: 100px;
        }
        a {
            color: #004085;
            text-decoration: none;
            font-weight: bold;
        }
        .box {
            display: inline-block;
            background: #f5c6cb;
            padding: 20px;
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <div class="box">
        <h2>Error</h2>
        <p><%= request.getAttribute("errorMessage") != null 
                ? request.getAttribute("errorMessage") 
                : "An unexpected error occurred." %></p>
        <p><a href="index.jsp">Go to Home</a></p>
    </div>
</body>
</html>
