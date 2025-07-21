// service/LibraryService.java
package Service;

import DAO.BookDAO;
import DAO.BorrowRecordDAO;
import DAO.UserDAO;
import Model.Book;
import Model.BorrowRecord;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LibraryService {
    private static final int MAX_BORROWED = 4;
    private static final int MAX_DAYS     = 5;
    private static final int RENEW_DAYS   = 3;

    private final BookDAO         bookDAO         = new BookDAO();
    private final UserDAO         userDAO         = new UserDAO();
    private final BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO();

    public void borrowBook(int userId,int bookId) throws SQLException {
        List<BorrowRecord> active = borrowRecordDAO.listActiveByUser(userId);
        if(active.size()>=MAX_BORROWED) throw new IllegalStateException("Limit exceeded");
        Book b = bookDAO.getBook(bookId);
        if(b==null || b.getAvailableCopies()<=0) throw new IllegalStateException("Unavailable");

        int newId = (int)(System.currentTimeMillis()&0xfffffff);
        BorrowRecord br = new BorrowRecord(
                newId,userId,bookId,
                LocalDate.now(),
                LocalDate.now().plusDays(MAX_DAYS),
                false,null);
        borrowRecordDAO.addRecord(br);
        bookDAO.updateAvailableCopies(bookId,-1);
    }

    public void returnBook(int userId,int bookId) throws SQLException {
        BorrowRecord br = borrowRecordDAO.getActiveRecord(userId,bookId);
        if(br==null) throw new IllegalStateException("No active borrow");
        br.setReturnDate(LocalDate.now());
        borrowRecordDAO.updateRecord(br);
        bookDAO.updateAvailableCopies(bookId,1);
    }

    public void renewBook(int userId,int bookId) throws SQLException {
        BorrowRecord br = borrowRecordDAO.getActiveRecord(userId,bookId);
        if(br==null) throw new IllegalStateException("No active borrow");
        if(br.isRenewed()) throw new IllegalStateException("Already renewed");
        br.setDueDate(br.getDueDate().plusDays(RENEW_DAYS));
        br.setRenewed(true);
        borrowRecordDAO.updateRecord(br);
    }

    // fetch helpers
    public List<Book> listBooks() throws SQLException { return bookDAO.listAll(); }
}
