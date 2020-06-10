package com.mycompany.tushar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class getslotsbean 
{
    public List<com.mycompany.tushar.Slot> slots = new ArrayList<com.mycompany.tushar.Slot>();
    public List getslots(String garage)
    {
        
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Slots WHERE Garage = '"+garage+"'");
            rs = stmt.getResultSet(); 
            while(rs.next())
                slots.add(new com.mycompany.tushar.Slot(rs.getString("Garage"), rs.getInt("Slotno"), rs.getInt("Size"), "yes" , rs.getInt("Price")));
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            if (rs != null)   {try {rs.close();} catch (SQLException sqlEx) { } rs = null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException sqlEx) { } stmt = null;}
            if (conn != null) {try {conn.close ();System.out.println ("Database connection terminated");}catch (Exception e) {} }
        }
        return slots;
    }
    
    public class Slot
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
    }
}
