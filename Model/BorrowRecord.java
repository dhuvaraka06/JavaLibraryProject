// model/BorrowRecord.java
package Model;

import java.time.LocalDate;

public class BorrowRecord {
    private int        recordId;
    private int        userId;
    private int        bookId;
    private LocalDate  borrowDate;
    private LocalDate  dueDate;
    private boolean    renewed;
    private LocalDate  returnDate;

    public BorrowRecord(int recordId, int userId, int bookId,
                        LocalDate borrowDate, LocalDate dueDate,
                        boolean renewed, LocalDate returnDate) {
        this.recordId   = recordId;
        this.userId     = userId;
        this.bookId     = bookId;
        this.borrowDate = borrowDate;
        this.dueDate    = dueDate;
        this.renewed    = renewed;
        this.returnDate = returnDate;
    }

    public BorrowRecord(){}

    public int       getRecordId()              { return recordId; }
    public void      setRecordId(int id)        { this.recordId = id; }
    public int       getUserId()                { return userId; }
    public void      setUserId(int id)          { this.userId = id; }
    public int       getBookId()                { return bookId; }
    public void      setBookId(int id)          { this.bookId = id; }
    public LocalDate getBorrowDate()            { return borrowDate; }
    public void      setBorrowDate(LocalDate d) { this.borrowDate = d; }
    public LocalDate getDueDate()               { return dueDate; }
    public void      setDueDate(LocalDate d)    { this.dueDate = d; }
    public boolean   isRenewed()                { return renewed; }
    public void      setRenewed(boolean r)      { this.renewed = r; }
    public LocalDate getReturnDate()            { return returnDate; }
    public void      setReturnDate(LocalDate d) { this.returnDate = d; }
}
