package model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    private String password;

    // Constructor
    public User(int userId, String name, String email, String phone, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Default constructor
    public User() {
        // Fields initialized with default values
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getPassword() {
        return password;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method for debugging/logging
    @Override
    public String toString() {
        return "User ID: " + userId +
               "\nName: " + name +
               "\nEmail: " + email +
               "\nPhone: " + phone;
    }
}
