package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.mycompany.tushar.Slot;
import java.util.List;
//@author Tushar
@WebServlet(name="addslots", urlPatterns={"/addslots"})
public class addslots extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try{PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String garage = session.getAttribute("name").toString();
        //int slotno = Integer.parseInt(request.getParameter("slotno"));
        int slotno = 0;
        int price = Integer.parseInt(request.getParameter("price"));
        int size = Integer.parseInt(request.getParameter("size"));
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Slots WHERE Garage = '"+garage+"'");
            rs = stmt.getResultSet();
            rs.last();
            slotno = rs.getRow()+1;
            stmt.execute("INSERT INTO Slots VALUES ('"+garage+"','"+slotno+"','"+size+"','yes','"+price+"')");
            /*stmt.execute("SELECT * FROM Slots WHERE Garage = '"+garage+"'");
            rs = stmt.getResultSet(); 
            while(rs.next())
                slots.add(new Slot(rs.getString("Garage"), rs.getInt("Slotno"), rs.getInt("Size"), "yes" , rs.getInt("Price")));
            if(!slots.isEmpty())
            {
                request.setAttribute("slots", slots);
                for(Slot slot:slots)
                    System.out.println(slot.garage+" "+ slot.slotno+" "+ slot.size +" "+slot.availability+" "+slot.price);
                request.getRequestDispatcher("addslots.jsp").forward(request, response);
            }*/
            /*List<Slot> slots = new getslotsbean().getslots(garage);
            for(Slot slot:slots)
                    System.out.println(slot.garage+" "+ slot.slotno+" "+ slot.size +" "+slot.availability+" "+slot.price);
            request.setAttribute("getslots", slots);  
            */request.getRequestDispatcher("addslots.jsp").forward(request, response);
            
            //while(rs.next())
            //    System.out.println(rs.getString("Garage")+" "+ rs.getInt("Slotno")+" "+ rs.getInt("Size")+" "+ rs.getString("Availability") +" "+ rs.getInt("Price"));
//            if(rs.next())
//                request.setAttribute("rs", rs);
            
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
        }catch(Exception e){e.printStackTrace();}
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
/*class Slot
{
    String garage;
    int slotno;
    int size;
    String availability;
    int price;
    public Slot(String garage, int slotno, int size, String availability, int price) {
        this.garage = garage;
        this.slotno = slotno;
        this.size = size;
        this.availability = availability;
        this.price = price;
    }
}*/