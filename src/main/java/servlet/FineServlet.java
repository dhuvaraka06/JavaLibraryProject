package servlet;

import dao.FineDAO;
import model.Fine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/fine")
public class FineServlet extends HttpServlet {
    private FineDAO fineDAO;

    @Override
    public void init() throws ServletException {
        fineDAO = new FineDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            listFines(request, response);
        } else if (action.equals("delete")) {
            deleteFine(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("add")) {
            addFine(request, response);
        } else if (action.equals("update")) {
            updateFine(request, response);
        }
    }

    private void addFine(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String reason = request.getParameter("reason");

        Fine fine = new Fine();
        fine.setUserId(userId);
        fine.setAmount(amount);
        fine.setReason(reason);

        fineDAO.addFine(fine);
        response.sendRedirect("fine?action=list");
    }

    private void updateFine(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int fineId = Integer.parseInt(request.getParameter("fineId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String reason = request.getParameter("reason");

        Fine fine = new Fine();
        fine.setFineId(fineId);
        fine.setUserId(userId);
        fine.setAmount(amount);
        fine.setReason(reason);

        fineDAO.updateFine(fine);
        response.sendRedirect("fine?action=list");
    }

    private void deleteFine(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int fineId = Integer.parseInt(request.getParameter("fineId"));
        fineDAO.deleteFine(fineId);
        response.sendRedirect("fine?action=list");
    }

    private void listFines(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Fine> fineList = fineDAO.getAllFines();
        request.setAttribute("fineList", fineList);
        request.getRequestDispatcher("fineList.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int fineId = Integer.parseInt(request.getParameter("fineId"));
        Fine fine = fineDAO.getFineById(fineId);
        request.setAttribute("fine", fine);
        request.getRequestDispatcher("editFine.jsp").forward(request, response);
    }
}
