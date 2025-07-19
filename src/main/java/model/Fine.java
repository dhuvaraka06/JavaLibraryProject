package model;

import java.sql.Date;

public class Fine {
    private int fineId;
    private int borrowId;
    private double fineAmount;
    private boolean finePaid;
    private Date finePaidDate;

    public Fine(int fineId, int borrowId, double fineAmount, boolean finePaid, Date finePaidDate) {
        this.fineId = fineId;
        this.borrowId = borrowId;
        this.fineAmount = fineAmount;
        this.finePaid = finePaid;
        this.finePaidDate = finePaidDate;
    }

    // Getters
    public int getFineId() {
        return fineId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public boolean isFinePaid() {
        return finePaid;
    }

    public Date getFinePaidDate() {
        return finePaidDate;
    }

    // Setters
    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void setFinePaid(boolean finePaid) {
        this.finePaid = finePaid;
    }

    public void setFinePaidDate(Date finePaidDate) {
        this.finePaidDate = finePaidDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Fine Details:\n" +
               "Fine ID: " + fineId + "\n" +
               "Borrow ID: " + borrowId + "\n" +
               "Fine Amount: ₹" + fineAmount + "\n" +
               "Fine Paid: " + (finePaid ? "Yes" : "No") + "\n" +
               "Fine Paid Date: " + (finePaidDate != null ? finePaidDate.toString() : "Not Paid");
    }
}