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
@WebServlet(name = "review", urlPatterns = {"/review"})
public class review extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String user = session.getAttribute("user").toString();
        String name = request.getParameter("garage");
        String review = request.getParameter("review");
        int rating = Integer.parseInt(request.getParameter("rating"));
        if(rating>5 || rating<0){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invalid Rating!');");
            out.println("location='review.jsp';");
            out.println("</script>");
        }
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT Rating FROM Garages WHERE Name = '"+name+"'");
            //System.out.println("Garage = "+name);
            rs = stmt.getResultSet(); rs.next();
            float grating = rs.getFloat("Rating");
            int size = 0;
            stmt.execute("SELECT * FROM Reviews WHERE Garage = '" +name+ "'");
            rs = stmt.getResultSet();
            if(rs!=null){
                while(rs.next())
                size++;
            }
            grating = ((grating * (size)) + rating)/(size+1);
            grating = (float) (Math.floor(grating*100)/100.0);
            stmt.execute("UPDATE Garages SET Rating = '" +grating+ "' WHERE Name = '" +name+ "'");
            PreparedStatement ps=conn.prepareStatement("insert into Reviews values(?,?,?,?)");  
            ps.setString(1,user); ps.setString(2,name); ps.setInt(3,rating); ps.setString(4,review);
            int i=ps.executeUpdate();  
            if(i>0)  
            {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Review added!!');");
                out.println("location='custhome.jsp';");
                out.println("</script>");
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error adding review!');");
                out.println("location='custhome.jsp';");
                out.println("</script>");
            }
        } 
        catch (SQLException ex){
                out.println("SQLException: " + ex.getMessage());
                out.println("SQLState: " + ex.getSQLState());
                out.println("VendorError: " + ex.getErrorCode()+"\n");
                ex.printStackTrace();
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
