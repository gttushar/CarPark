package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "signupowner", urlPatterns = {"/signupowner"})
public class signupowner extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String rpass = request.getParameter("rpass");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        if(name.length()>30){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Name is too long!');");
            out.println("location='signupowner.jsp';");
            out.println("</script>");
        }else if(user.length()>10){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username is too long!');");
            out.println("location='signupowner.jsp';");
            out.println("</script>");
        }else if(pass.length()>10){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Password is too long!');");
            out.println("location='signupowner.jsp';");
            out.println("</script>");
        }else if(!rpass.equals(pass)){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Re-enter password doesnt match!');");
            out.println("location='signupowner.jsp';");
            out.println("</script>");
        }else if(!isphone(phone)){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invalid Phone no!');");
            out.println("location='signupowner.jsp';");
            out.println("</script>");
        } 
        else{
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            //boolean result = stmt.execute("SELECT Userid FROM Owner WHERE Userid = '"+user+"'");
            PreparedStatement ps=conn.prepareStatement("insert into Owner values(?,?,?,?)");  
            ps.setString(1,user); ps.setString(2,pass); ps.setString(3,name); ps.setString(4,phone);  
            int i=ps.executeUpdate();  
            if(i>0)  
            {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Owner account created');");
                out.println("location='login.jsp';");
                out.println("</script>");
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error creating account!');");
                out.println("location='signupowner.jsp';");
                out.println("</script>");
            } 
            
        } 
        catch (SQLException ex){
            if(ex.getErrorCode() == 1062){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username already exists!');");
                out.println("location='signupowner.jsp';");
                out.println("</script>");
            } else{
                out.println("SQLException: " + ex.getMessage());
                out.println("SQLState: " + ex.getSQLState());
                out.println("VendorError: " + ex.getErrorCode());
            }
        }
        catch (Exception e){out.println ("Cannot connect to database server " + e.getMessage());}
        finally {
            if (rs != null)   {try {rs.close();} catch (SQLException sqlEx) { } rs = null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException sqlEx) { } stmt = null;}
            if (conn != null) {try {conn.close ();out.println ("Database connection terminated");}catch (Exception e) {} }
        }
        }
    }
        
    boolean isphone(String phone)
    {
        if(phone.length()!=10) return false;
        for(int i=0;i<phone.length();i++)
        {
            if(phone.charAt(i)>'9' || phone.charAt(i)<'0') return false;
        }
        return true;
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
