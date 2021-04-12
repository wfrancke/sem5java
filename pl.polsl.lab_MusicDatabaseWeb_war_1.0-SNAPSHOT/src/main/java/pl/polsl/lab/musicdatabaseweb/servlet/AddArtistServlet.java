package pl.polsl.lab.musicdatabaseweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.lab.musicdatabaseweb.model.Artist;

/**
 * Servlet handling addition of new artists
 *
 * @author Wojciech Francke
 * @version 2.0
 */
@WebServlet(urlPatterns = {"/AddArtist"})
public class AddArtistServlet extends HttpServlet {
    
    

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
            String name = request.getParameter("name");
            String location = request.getParameter("location");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Artist</title>");            
            out.println("</head>");
            out.println("<body>");
            if (request.getSession().getAttribute("manager") == null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                EntityManager em = emf.createEntityManager();
                
                request.setAttribute("manager", emf);
                request.getSession().setAttribute("manager", emf);
                this.getServletConfig().getServletContext().setAttribute("manager", emf);
            } 
            if ("".equals(location) || "".equals(name)) {
                out.println("<h1>Incorrect data</h1>\n");
            } else {
                EntityManagerFactory emf = (EntityManagerFactory)request.getSession().getAttribute("manager");
                EntityManager em = emf.createEntityManager();
                Artist newartist = new Artist();
                newartist.setName(name);
                newartist.setLocation(location);
                em.getTransaction().begin();
                try {
                    em.persist(newartist);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    em.getTransaction().rollback();
                } finally {
                    em.close();
                }

                out.println("<h1>Artist " + newartist.getName() + " from " + newartist.getLocation() + " registered </h1>\n");
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
