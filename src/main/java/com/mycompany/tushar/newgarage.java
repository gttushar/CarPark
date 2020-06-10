package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//@author Tushar
@WebServlet(name="newgarage", urlPatterns={"/newgarage"})
public class newgarage extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String user = session.getAttribute("user").toString();
        String name = session.getAttribute("name").toString();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Garages WHERE Name = '"+name+"'");
            rs = stmt.getResultSet();
            if(!rs.next()){
                String regno = session.getAttribute("regno").toString();
                String address = session.getAttribute("address").toString();
                String landmark = session.getAttribute("landmark").toString();
                PreparedStatement ps=conn.prepareStatement("insert into Garages values(?,?,?,?,?,?)");  
                ps.setString(1,user); ps.setString(2,name); ps.setString(3,regno); ps.setString(4,address); ps.setString(5,landmark); ps.setInt(6,0);
                int i=ps.executeUpdate();  
                if(i>0)  
                {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Garage added!!');");
                    session.removeAttribute(name);
                    session.removeAttribute(regno);
                    session.removeAttribute(address);
                    session.removeAttribute(landmark);
                    out.println("location='ownerhome.jsp';");
                    out.println("</script>");
                }
                else{
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Error adding garage!');");
                    session.removeAttribute(name);
                    session.removeAttribute(regno);
                    session.removeAttribute(address);
                    session.removeAttribute(landmark);
                    out.println("location='ownerhome.jsp';");
                    out.println("</script>");
                }
            }
            else
                request.getRequestDispatcher("garageupdate.jsp").forward(request, response);
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
