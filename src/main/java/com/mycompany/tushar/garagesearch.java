package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
//@author Tushar
@WebServlet(name="garagesearch", urlPatterns={"/garagesearch"})
public class garagesearch extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        int mark = Integer.parseInt(request.getParameter("mark"));
        request.setAttribute("mark", mark);
        //System.out.println("mark = "+mark);
        if(mark == 0) request.setAttribute("landmark", "MainBuilding");if(mark == 10) request.setAttribute("landmark", "LLR");
        if(mark == 1) request.setAttribute("landmark", "Netaji");if(mark == 11) request.setAttribute("landmark", "MS");
        if(mark == 2) request.setAttribute("landmark", "Tikka");if(mark == 12) request.setAttribute("landmark", "RP");
        if(mark == 3) request.setAttribute("landmark", "MT");if(mark == 13) request.setAttribute("landmark", "ECdept");
        if(mark == 4) request.setAttribute("landmark", "TGH");if(mark == 14) request.setAttribute("landmark", "MEdept");
        if(mark == 5) request.setAttribute("landmark", "PANloop");if(mark == 15) request.setAttribute("landmark", "CYdept");
        if(mark == 6) request.setAttribute("landmark", "HJB");if(mark == 16) request.setAttribute("landmark", "NehruMuseum");
        if(mark == 7) request.setAttribute("landmark", "SNIG");if(mark == 17) request.setAttribute("landmark", "Nalanda");
        if(mark == 8) request.setAttribute("landmark", "VS");if(mark == 18) request.setAttribute("landmark", "AGRI");
        if(mark == 9) request.setAttribute("landmark", "LBS");if(mark == 19) request.setAttribute("landmark", "PuriGate");
        request.getRequestDispatcher("garagesearch.jsp").forward(request, response);
    } 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}