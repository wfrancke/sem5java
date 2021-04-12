package pl.polsl.lab.musicdatabaseweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.lab.musicdatabaseweb.model.*;

/**
 * Servlet handling rating of albums
 *
 * @author Wojciech Francke
 * @version 2.0
 */
@WebServlet(name = "RateAlbum", urlPatterns = {"/RateAlbum"})
public class RateAlbumServlet extends HttpServlet {
    
    /**
     * Method used to check if the input string is an integer.
     * 
     * @param text input string
     * @return true if it is an integer, false if it's not
     */
    public boolean isInt(String text) {
        if (text == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(text);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws AlbumException if rating is incorrect
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AlbumException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String albumId = request.getParameter("album");
            String ratingString = request.getParameter("rating");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Rate album</title>");            
            out.println("</head>");
            out.println("<body>");
            if (request.getSession().getAttribute("manager") == null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                EntityManager em = emf.createEntityManager();
                
                request.setAttribute("manager", emf);
                request.getSession().setAttribute("manager", emf);
                this.getServletConfig().getServletContext().setAttribute("manager", emf);
            } 
            if(this.isInt(ratingString) == false || this.isInt(albumId) == false) {
                out.println("<h1>Incorrect data</h1>\n");
            }
            else {
                EntityManagerFactory emf = (EntityManagerFactory)request.getSession().getAttribute("manager");
                EntityManager em = emf.createEntityManager();
                if(em.find(Album.class, Long.parseLong(albumId)) == null) {
                    out.println("<h1>Incorrect data</h1>\n");
                }
                else {
                    Album album = em.find(Album.class, Long.parseLong(albumId));
                    double rating = Double.parseDouble(ratingString);
                    try {
                        album.addRating(rating);
                        out.println("<h1>Rating registered</h1>\n");
                    } catch (AlbumException ae) {
                        out.println("<h1>Incorrect data</h1>\n");
                    }
                    
                    em.getTransaction().begin();
                    try {
                        em.merge(album);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        em.getTransaction().rollback();
                    } finally {
                        em.close();
                    }
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (AlbumException ex) {
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Rate album</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Incorrect data</h1>\n");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (AlbumException ex) {
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Rate album</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Incorrect data</h1>\n");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
