package servlet;

import dao.LibrarianDAO;
import model.Librarian;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/librarian")
public class LibrarianServlet extends HttpServlet {
    private LibrarianDAO librarianDAO;

    @Override
    public void init() throws ServletException {
        librarianDAO = new LibrarianDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addLibrarian(request, response);
                    break;
                case "update":
                    updateLibrarian(request, response);
                    break;
                case "delete":
                    deleteLibrarian(request, response);
                    break;
                default:
                    listLibrarians(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listLibrarians(request, response);
    }

    private void addLibrarian(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Librarian librarian = new Librarian();
        librarian.setName(name);
        librarian.setEmail(email);
        librarian.setPassword(password);

        librarianDAO.addLibrarian(librarian);
        response.sendRedirect("librarian?action=list");
    }

    private void updateLibrarian(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int librarianId = Integer.parseInt(request.getParameter("librarianId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Librarian librarian = new Librarian();
        librarian.setLibrarianId(librarianId);
        librarian.setName(name);
        librarian.setEmail(email);
        librarian.setPassword(password);

        librarianDAO.updateLibrarian(librarian);
        response.sendRedirect("librarian?action=list");
    }

    private void deleteLibrarian(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int librarianId = Integer.parseInt(request.getParameter("librarianId"));
        librarianDAO.deleteLibrarian(librarianId);
        response.sendRedirect("librarian?action=list");
    }

    private void listLibrarians(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Librarian> librarianList = librarianDAO.getAllLibrarians();
        request.setAttribute("librarianList", librarianList);
        request.getRequestDispatcher("librarianList.jsp").forward(request, response);
    }
}
