package com.mycompany.tushar;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "newvehicle", urlPatterns = {"/newvehicle"})
public class newvehicle extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String user = session.getAttribute("user").toString();
        String model = request.getParameter("model");
        String license = request.getParameter("license");
        String size = request.getParameter("size");
        PrintWriter out = response.getWriter();
        try{
            String path = getServletContext().getRealPath("/")+"/";
            FileInputStream fr = new FileInputStream(path+"WEB-INF/licenses.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(fr)));  //creates a buffering character input stream  
            String line;  
            int flag = 0;
            while((line=br.readLine())!=null)  
            {  
                if(license.equals(line))
                {    flag = 1; break; }
            }  
            fr.close(); 
            if(flag == 0)
                {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Enter a valid License Number!!');");
                out.println("location='newvehicle.jsp';");
                out.println("</script>");
            }
        }
        catch(IOException e)
            { out.println(e.getMessage());}
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            PreparedStatement ps=conn.prepareStatement("insert into Vehicles values(?,?,?,?,?)");  
            ps.setString(1,user); ps.setString(2,model); ps.setString(3,license); ps.setString(4,"yes"); ps.setString(5,size); 
            int i=ps.executeUpdate();  
            if(i>0)  
            {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Vehicle added!!');");
                out.println("location='custhome.jsp';");
                out.println("</script>");
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error adding vehicle!');");
                out.println("location='custhome.jsp';");
                out.println("</script>");
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
