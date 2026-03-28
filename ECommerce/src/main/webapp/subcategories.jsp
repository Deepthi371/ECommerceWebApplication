<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Select Category</title></head>
<body>
  <h2>Select Category & Sub‑Category</h2>
  <form action="ProductList" method="get">
    <label>Category</label><br>
    <select name="category" id="category" required onchange="updateSubcat()">
      <option value="Electronics">Electronics</option>
      <option value="Clothing">Clothing</option>
      <option value="Home Appliances">Home Appliances</option>
    </select><br><br>
    <label>Sub‑Category</label><br>
    <select name="subcategory" id="subcategory" required>
      <!-- JavaScript will fill this -->
    </select><br><br>
    <input type="submit" value="View Products">
  </form>

  <script>
    function updateSubcat(){
      var cat = document.getElementById("category").value;
      var sub = document.getElementById("subcategory");
      sub.innerHTML = "";
      var opts = [];
      if(cat==="Electronics") opts = ["Mobile","Laptop","Camera"];
      else if(cat==="Clothing") opts = ["Men Wear","Women Wear","Kids Wear"];
      else if(cat==="Home Appliances") opts = ["Furniture","Kitchen","Decor"];
      opts.forEach(function(o){
        var opt = document.createElement("option");
        opt.value = o;
        opt.text = o;
        sub.appendChild(opt);
      });
    }
    window.onload = updateSubcat;
  </script>
</body>
</html>