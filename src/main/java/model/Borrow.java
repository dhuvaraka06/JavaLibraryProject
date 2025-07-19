package model;

import java.sql.Date;

public class Borrow {
    private int borrowId;
    private int userId;
    private int bookId;
    private Date borrowDate;
    private boolean renewed;
    private Date dueDate;
    private Date renewedDate;
    private Date returnDate;

    // Constructor without ID (for insert)
    public Borrow(int userId, int bookId, Date borrowDate, boolean renewed, Date dueDate, Date renewedDate, Date returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.renewed = renewed;
        this.dueDate = dueDate;
        this.renewedDate = renewedDate;
        this.returnDate = returnDate;
    }

    // Constructor with ID (for read/update)
    public Borrow(int borrowId, int userId, int bookId, Date borrowDate, boolean renewed, Date dueDate, Date renewedDate, Date returnDate) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.renewed = renewed;
        this.dueDate = dueDate;
        this.renewedDate = renewedDate;
        this.returnDate = returnDate;
    }

    // Default constructor
    public Borrow() {}

    // Getters and Setters
    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public boolean isRenewed() {
        return renewed;
    }

    public void setRenewed(boolean renewed) {
        this.renewed = renewed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getRenewedDate() {
        return renewedDate;
    }

    public void setRenewedDate(Date renewedDate) {
        this.renewedDate = renewedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
