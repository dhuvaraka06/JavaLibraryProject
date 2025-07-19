package servlet;

import dao.BorrowDAO;
import model.Borrow;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    private BorrowDAO borrowDAO;

    @Override
    public void init() throws ServletException {
        borrowDAO = new BorrowDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addBorrow(request, response);
                    break;
                case "update":
                    updateBorrow(request, response);
                    break;
                case "delete":
                    deleteBorrow(request, response);
                    break;
                default:
                    listBorrows(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listBorrows(request, response);
    }

    private void addBorrow(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Date borrowDate = Date.valueOf(request.getParameter("borrowDate"));
        Date returnDate = Date.valueOf(request.getParameter("returnDate"));
        String status = request.getParameter("status");

        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(borrowDate);
        borrow.setReturnDate(returnDate);

        borrowDAO.addBorrow(borrow);
        response.sendRedirect("borrow?action=list");
    }

    private void updateBorrow(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int borrowId = Integer.parseInt(request.getParameter("borrowId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Date borrowDate = Date.valueOf(request.getParameter("borrowDate"));
        Date returnDate = Date.valueOf(request.getParameter("returnDate"));
        String status = request.getParameter("status");

        Borrow borrow = new Borrow();
        borrow.setBorrowId(borrowId);
        borrow.setUserId(userId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(borrowDate);
        borrow.setReturnDate(returnDate);

        borrowDAO.updateBorrow(borrow);
        response.sendRedirect("borrow?action=list");
    }

    private void deleteBorrow(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int borrowId = Integer.parseInt(request.getParameter("borrowId"));
        borrowDAO.deleteBorrow(borrowId);
        response.sendRedirect("borrow?action=list");
    }

    private void listBorrows(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Borrow> borrowList = borrowDAO.getAllBorrows();
        request.setAttribute("borrowList", borrowList);
        request.getRequestDispatcher("borrowList.jsp").forward(request, response);
    }
}
