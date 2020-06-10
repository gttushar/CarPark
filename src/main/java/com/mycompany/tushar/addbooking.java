package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//@author Tushar
@WebServlet(name="addbooking", urlPatterns={"/addbooking"})
public class addbooking extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String owner = session.getAttribute("user").toString();
        String customer = request.getParameter("customer");
        String phone = request.getParameter("phone");
        String garage = request.getParameter("garage");
        String model = request.getParameter("model");
        int slotno = Integer.parseInt(request.getParameter("slotno"));
        String start = request.getParameter("start");
        String end = request.getParameter("end");      
        String paid = request.getParameter("paid"); 
        PrintWriter out = response.getWriter();
        bookingsbean book = new bookingsbean();
            if(book.addbooking(owner,customer,phone,garage,model,slotno,start,end,paid)){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Booking added!');");
                out.println("location='bookings.jsp';");
                out.println("</script>");
            }    
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Slot Full!!');");
                out.println("location='bookings.jsp';");
                out.println("</script>");
            }   
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(addbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(addbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
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
