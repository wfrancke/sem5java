package pl.polsl.lab.musicdatabaseweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * Servlet handling printing of an artists details and discography
 *
 * @author Wojciech Francke
 * @version 2.0
 */
@WebServlet(name = "Print", urlPatterns = {"/Print"})
public class PrintServlet extends HttpServlet {

    /**
     * Method used to check if the input string is an integer.
     * 
     * @param text input string
     * @return true if it is an integer, false if it's not
     */
    public boolean isInt(String text) {
        if(text == null) {
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("id");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Print</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div  class=\"container\">");
            if (request.getSession().getAttribute("manager") == null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                EntityManager em = emf.createEntityManager();

                request.setAttribute("manager", emf);
                request.getSession().setAttribute("manager", emf);
                this.getServletConfig().getServletContext().setAttribute("manager", emf);
            }
            if (this.isInt(id) == false) {
                out.println("<h1>Incorrect data</h1>\n");
            } else {
                EntityManagerFactory emf = (EntityManagerFactory) request.getSession().getAttribute("manager");
                EntityManager em = emf.createEntityManager();
                if (em.find(Artist.class, Long.parseLong(id)) == null) {
                    out.println("<h1>Incorrect data</h1>\n");
                } else {
                    Artist artist = em.find(Artist.class, Long.parseLong(id));
                    out.println("<h1>Artist " + artist.getName() + " from " + artist.getLocation() + "</h1>\n");
                    for (Album album : artist.getAlbumCollection()) {
                        out.println("<hr>\n<h3>" + album.getTitle()
                                + ",   Genre: " + album.getGenre()
                                + ",   Release year: " + album.getReleaseyear()
                                + ",   Rating: " + album.getRating()
                                + ",   out of " + album.getNumberofratings() + " ratings" + "</h3>\n");
                                int i = 1;
                        for (Track track : album.getTrackCollection()) {
                            out.println("<h5>" + i++ + ": " + track.getTitle() + " (" + track.getLength() / 60 + ":" + track.getLength() % 60 + ")</h5>");
                        }
                    }
                }
            }
            out.println("</div>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
