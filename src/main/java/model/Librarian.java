package model;

public class Librarian {
    private int librarianId;
    private String name;
    private String email;
    private String password;

    // Parameterized constructor
    public Librarian(int librarianId, String name, String email, String password) {
        this.librarianId = librarianId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Default constructor
    public Librarian() {
        this.librarianId = 0;
        this.name = "";
        this.email = "";
        this.password = "";
    }

    // Getters
    public int getLibrarianId() {
        return librarianId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setLibrarianId(int librarianId) {
        if (librarianId > 0) {
            this.librarianId = librarianId;
        } else {
            System.out.println("Invalid Librarian ID");
        }
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Name cannot be empty");
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email address");
        }
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 4) {
            this.password = password;
        } else {
            System.out.println("Password must be at least 4 characters long");
        }
    }

    // toString
    @Override
    public String toString() {
        return "Librarian ID: " + librarianId +
               "\nName: " + name +
               "\nEmail: " + email;
    }
}
