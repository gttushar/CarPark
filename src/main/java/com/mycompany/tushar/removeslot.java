package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//@author Tushar
@WebServlet(name="removeslot", urlPatterns={"/removeslot"})
public class removeslot extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String garage = session.getAttribute("name").toString();
        int slotno = Integer.parseInt(request.getParameter("slotno"));
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        PrintWriter out = response.getWriter();
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Slots Where (Garage = '"+garage+"' AND Slotno ="+slotno+")");
            rs = stmt.getResultSet();
            if(!rs.next()){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid Slotno.!!');");
                out.println("location='garageupdate.jsp';");
                out.println("</script>");
            }
            else{
                stmt.execute("DELETE FROM Slots WHERE (Garage = '"+garage+"' AND Slotno ="+slotno+")");
                stmt.execute("UPDATE Slots SET Slotno =  Slotno-1 WHERE (Garage = '"+garage+"' AND Slotno >"+slotno+")");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Slot Removed!');");
                out.println("location='garageupdate.jsp';");
                out.println("</script>");
            }
            
        } 
        catch (SQLException ex){
            ex.printStackTrace();
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            if (rs != null)   {try {rs.close();} catch (SQLException sqlEx) { } rs = null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException sqlEx) { } stmt = null;}
            if (conn != null) {try {conn.close ();}catch (Exception e) {} }
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
