package com.ecommerce.model;

import java.sql.*;
import java.util.*;

import com.ecommerce.controller.Product;
import com.ecommerce.utils.Credentials;

public class Model {

    private static Connection con;
	private static String productMap;
	private static String jdbcUsername;
	private static String jdbcPassword;
	private static String jdbcURL;
    private PreparedStatement pstmt;
    private ResultSet res;

    // =================== User Fields ===================
    private String uname;
    private String email;
    private Long phone;
    private String address;
    private String password;

    // =================== Product Fields ===================
    private int productId;
    private String name;
    private String brand;
    private String rating;
    private String description;
    private double price;
    private String category;
    private String subcategory;

    // =================== Cart Fields ===================
    private int quantity;
    private int cartId;

    // =================== Order Summary ===================
    private double total;
    
    // =================== Image Url =====================
    private String imageUrl;

    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	// =================== Constructor ===================
    public Model() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Model(int i, String string, String string2, double d) {
		// TODO Auto-generated constructor stub
	}
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ecommerce2?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Deepu";

    // =================== User Getters & Setters ===================
    public String getUname() { return uname; }
    public void setUname(String uname) { this.uname = uname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getPhone() { return phone; }
    public void setPhone(Long phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // =================== Product Getters & Setters ===================
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSubcategory() { return subcategory; }
    public void setSubcategory(String subcategory) { this.subcategory = subcategory; }

    // =================== Cart Getters & Setters ===================
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    // =================== Functional Methods ===================

    // 1. Register new customer
    public boolean registerUser() {
        try {
            String sql = "INSERT INTO users (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, uname);
            pstmt.setString(2, email);
            pstmt.setLong(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, password);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Validate user login
    
    public boolean validateUser(String email, String password) {
        System.out.println("DEBUG: Email received: '" + email + "'");
        System.out.println("DEBUG: Password received: '" + password + "'");

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("✅ User found in DB.");
                return true;
            } else {
                System.out.println("❌ No matching user found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Get all products by category and subcategory
    public List<Model> getProductsByCategory() {
        List<Model> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products WHERE category = ? AND subcategory = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, category);
            pstmt.setString(2, subcategory);
            res = pstmt.executeQuery();
            while (res.next()) {
                Model m = new Model();
                m.setProductId(res.getInt("id"));
                m.setName(res.getString("name"));
                m.setBrand(res.getString("brand"));
                m.setRating(res.getString("rating"));
                m.setDescription(res.getString("description"));
                m.setPrice(res.getDouble("price"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 4. Add product to cart
    public boolean addToCart() {
        try {
            String sql = "INSERT INTO cart(product_id, quantity) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productId);
            pstmt.setInt(2, quantity);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Get all items in the cart
    public List<Model> getCartItems() {
        List<Model> list = new ArrayList<>();
        try {
            String sql = "SELECT c.id, p.name, p.price, c.quantity FROM cart c JOIN products p ON c.product_id = p.id";
            pstmt = con.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Model m = new Model();
                m.setCartId(res.getInt("id"));
                m.setName(res.getString("name"));
                m.setPrice(res.getDouble("price"));
                m.setQuantity(res.getInt("quantity"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6. Remove item from cart
    public boolean removeFromCart() {
        try {
            String sql = "DELETE FROM cart WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cartId);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 7. Clear cart (on checkout)
    public boolean clearCart() {
        try {
            String sql = "DELETE FROM cart";
            pstmt = con.prepareStatement(sql);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 8. Calculate total price in cart
    public double calculateTotal() {
        try {
            String sql = "SELECT p.price, c.quantity FROM cart c JOIN products p ON c.product_id = p.id";
            pstmt = con.prepareStatement(sql);
            res = pstmt.executeQuery();
            double sum = 0;
            while (res.next()) {
                sum += res.getDouble("price") * res.getInt("quantity");
            }
            total = sum;
        } catch (Exception e) {
            e.printStackTrace();
            total = 0;
        }
        return total;
    }
    
    public static List<Product> getProductsByCategory(String category, String subcategory) {
        List<Product> products = new ArrayList<>();

        if ("Electronics".equalsIgnoreCase(category)) {
            if ("Mobile".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Galaxy S23", "Samsung", 10799.99, 4.3, "High-end Samsung smartphone", "https://images.pexels.com/photos/12708808/pexels-photo-12708808.jpeg"));
                products.add(new Product("iPhone 15", "Apple", 99999.99, 4.8, "Latest Apple iPhone", "https://images.pexels.com/photos/404280/pexels-photo-404280.jpeg"));
                products.add(new Product("Pixel 9", "Google", 30000.00, 4.6, "Google Pixel phone", "https://images.pexels.com/photos/14434414/pexels-photo-14434414.jpeg"));
                products.add(new Product("OnePlus 12", "OnePlus", 100000.00, 4.2, "Fast OnePlus phone", "https://images.pexels.com/photos/17761866/pexels-photo-17761866.jpeg"));
                products.add(new Product("Moto Edge", "Motorola", 25999.99, 3.8, "Motorola smartphone with edge display", "https://images.pexels.com/photos/8490075/pexels-photo-8490075.jpeg"));
            } else if ("Laptop".equalsIgnoreCase(subcategory)) {
                products.add(new Product("MacBook Pro", "Apple", 80000.00, 4.6, "Apple's powerful laptop", "https://cdn.pixabay.com/photo/2015/01/21/14/14/macbook-606761_1280.jpg"));
                products.add(new Product("Dell XPS", "Dell", 59999.99, 4.0, "Sleek Dell laptop", "https://images.pexels.com/photos/811587/pexels-photo-811587.jpeg"));
                products.add(new Product("Lenovo ThinkPad", "Lenovo", 49999.99, 3.6, "Reliable Lenovo laptop", "https://images.pexels.com/photos/1777022/pexels-photo-1777022.jpeg"));
                products.add(new Product("Surface Laptop", "Microsoft", 90000.00, 4.5, "Microsoft Surface Laptop", "https://images.pexels.com/photos/4348404/pexels-photo-4348404.jpeg"));
                products.add(new Product("HP Spectre", "HP", 75999.99, 4.8, "HP Spectre sleek design", "https://images.pexels.com/photos/374074/pexels-photo-374074.jpeg"));
            } else if ("Camera".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Canon EOS R", "Canon", 25999.99, 3.6, "Full-frame mirrorless camera", "https://images.pexels.com/photos/2823924/pexels-photo-2823924.jpeg"));
                products.add(new Product("Nikon Z6", "Nikon", 39999.99, 4.2, "High quality Nikon camera", "https://images.pexels.com/photos/5050670/pexels-photo-5050670.jpeg"));
                products.add(new Product("Sony Alpha", "Sony", 15999.99, 4.6, "Sony Alpha mirrorless camera", "https://images.pexels.com/photos/31313591/pexels-photo-31313591.jpeg"));
                products.add(new Product("Fujifilm X-T4", "Fujifilm", 19999.99, 3.2, "Fujifilm X-T4 camera", "https://images.pexels.com/photos/709486/pexels-photo-709486.jpeg"));
                products.add(new Product("Panasonic Lumix", "Panasonic", 29999.99, 4.8, "Panasonic mirrorless camera", "https://images.pexels.com/photos/1125329/pexels-photo-1125329.jpeg"));
            }
        } else if ("Clothing".equalsIgnoreCase(category)) {
            if ("Men Wear".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Casual Shirt", "H & M", 500.00, 4.3, "Comfortable casual shirt", "https://images.pexels.com/photos/15870246/pexels-photo-15870246.jpeg"));
                products.add(new Product("Jeans", "Levi's", 1599.99, 3.8, "Stylish jeans for men", "https://images.pexels.com/photos/14048722/pexels-photo-14048722.jpeg"));
                products.add(new Product("Jacket", "North Face", 1500.00, 4.0, "Warm winter jacket", "https://images.pexels.com/photos/20469818/pexels-photo-20469818.jpeg"));
                products.add(new Product("T-Shirt", "Uniqlo", 400.00, 4.6, "Casual T-shirt", "https://images.pexels.com/photos/30407752/pexels-photo-30407752.jpeg"));
                products.add(new Product("Hoodie", "Nike", 999.99, 3.4, "Comfortable hoodie", "https://images.pexels.com/photos/26891575/pexels-photo-26891575.jpeg"));
            } else if ("Women Wear".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Luxury Saree", "Sabyasachi", 7999.99, 4.8, "Luxury saree for special occasions", "https://images.pexels.com/photos/29049358/pexels-photo-29049358.jpeg"));
                products.add(new Product("Skirt", "ONLY", 599.99, 4.2, "Casual skirt for women", "https://images.pexels.com/photos/19320340/pexels-photo-19320340.jpeg"));
                products.add(new Product("Designer Saree", "Manish Malhotra", 9999.99, 4.5, "Exclusive designer saree", "https://images.pexels.com/photos/13708226/pexels-photo-13708226.jpeg"));
                products.add(new Product("Jacket", "Zara", 1499.99, 3.8, "Trendy jacket for women", "https://images.pexels.com/photos/12168800/pexels-photo-12168800.jpeg"));
                products.add(new Product("Dress", "H & M", 499.99, 3.2, "Elegant women's dress", "https://images.pexels.com/photos/32322502/pexels-photo-32322502.jpeg"));
            } else if ("Kids Wear".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Kids T-Shirt", "Mothercare", 299.99, 4.3, "Fun kids t-shirt", "https://images.pexels.com/photos/19428919/pexels-photo-19428919.jpeg"));
                products.add(new Product("Kids Jeans", "Gini & Jony", 599.99, 3.6, "Durable kids jeans", "https://images.pexels.com/photos/30014162/pexels-photo-30014162.jpeg"));
                products.add(new Product("Kids Hoodie", "Hopscotch", 799.99, 4.5, "Warm kids hoodie", "https://images.pexels.com/photos/10538932/pexels-photo-10538932.jpeg"));
                products.add(new Product("Kids Jacket", "Carter’s", 499.99, 3.8, "Stylish kids jacket", "https://images.pexels.com/photos/19870104/pexels-photo-19870104.jpeg"));
                products.add(new Product("Kids Shorts", "Babyhug", 399.99, 4.0, "Comfortable shorts", "https://images.pexels.com/photos/8775085/pexels-photo-8775085.jpeg"));
            }
        } else if ("Home Appliances".equalsIgnoreCase(category)) {
            if ("Furniture".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Sofa", "Urban Ladder", 25999.99, 4.8, "Comfortable living room sofa", "https://images.pexels.com/photos/276528/pexels-photo-276528.jpeg"));
                products.add(new Product("Dining Table", "Godrej Interio", 14999.99, 3.9, "Elegant dining table", "https://images.pexels.com/photos/279748/pexels-photo-279748.jpeg"));
                products.add(new Product("Chair", "IKEA", 299.99, 3.8, "Modern chair", "https://images.pexels.com/photos/8082309/pexels-photo-8082309.jpeg"));
                products.add(new Product("Bed", "Durian", 24999.99, 4.0, "Comfortable bed", "https://images.pexels.com/photos/5998049/pexels-photo-5998049.jpeg"));
                products.add(new Product("Shelf", "Pepperfry", 9999.99, 3.6, "Stylish shelf", "https://images.pexels.com/photos/2062431/pexels-photo-2062431.jpeg"));
            } else if ("Kitchen".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Mixer", "Bajaj", 3999.99, 3.6, "High-speed kitchen mixer", "https://images.pexels.com/photos/6996330/pexels-photo-6996330.jpeg"));
                products.add(new Product("Microwave", "IFB", 14999.99, 4.0, "Compact microwave oven", "https://images.pexels.com/photos/8135120/pexels-photo-8135120.jpeg"));
                products.add(new Product("Blender", "NutriBullet", 4999.99, 4.8, "Smoothie blender", "https://images.pexels.com/photos/8845077/pexels-photo-8845077.jpeg"));
                products.add(new Product("Toaster", "Morphy Richards", 7999.99, 3.4, "2-slice toaster", "https://images.pexels.com/photos/5825406/pexels-photo-5825406.jpeg"));
                products.add(new Product("Coffee Maker", "Philips", 4999.99, 4.5, "Automatic coffee maker", "https://images.pexels.com/photos/31897998/pexels-photo-31897998.jpeg"));
            } else if ("Decor".equalsIgnoreCase(subcategory)) {
                products.add(new Product("Wall Art", "The June Shop", 1999.99, 3.8, "Beautiful wall decor", "https://images.pexels.com/photos/31888809/pexels-photo-31888809.jpeg"));
                products.add(new Product("Vase", "Home Centre", 1499.99, 4.0, "Elegant decorative vase", "https://images.pexels.com/photos/7620592/pexels-photo-7620592.jpeg"));
                products.add(new Product("Lamp", "IKEA", 1999.99, 4.6, "Modern table lamp", "https://images.pexels.com/photos/34237532/pexels-photo-34237532.jpeg"));
                products.add(new Product("Candle", "Bath & Body Works", 299.99, 3.5, "Scented candle", "https://images.pexels.com/photos/6311808/pexels-photo-6311808.jpeg"));
                products.add(new Product("Clock", "Chumbak", 999.99, 4.5, "Decorative wall clock", "https://images.pexels.com/photos/5627660/pexels-photo-5627660.jpeg"));
            }
        }

        return products;
    }
}
