package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            if(action.equals("Login as Owner")){
                stmt.execute("SELECT Userid, Password FROM Owner WHERE (Userid = '"+user+"' AND Password = '"+pass+"')");
                rs = stmt.getResultSet(); 
                if(!rs.next()){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Invalid username/password!');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                } else{
                    session.setAttribute("user", user);
                    response.sendRedirect("ownerhome.jsp");
                } 
            }else if(action.equals("Login as Customer")){
                stmt.execute("SELECT Userid, Password FROM Customer WHERE (Userid = '"+user+"' AND Password = '"+pass+"')");
                rs = stmt.getResultSet(); 
                if(!rs.next()){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Invalid username/password!');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                } else{
                    session.setAttribute("user", user);
                    response.sendRedirect("custhome.jsp");
                } 
            }
        } 
        catch (SQLException ex){
                out.println("SQLException: " + ex.getMessage());
                out.println("SQLState: " + ex.getSQLState());
                out.println("VendorError: " + ex.getErrorCode());
        }
        catch (Exception e){out.println ("Cannot connect to database server " + e.getMessage());}
        finally {
            if (rs != null)   {try {rs.close();} catch (SQLException sqlEx) { } rs = null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException sqlEx) { } stmt = null;}
            if (conn != null) {try {conn.close ();out.println ("Database connection terminated");}catch (Exception e) {} }
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
