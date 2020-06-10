package com.mycompany.tushar;
import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class bookingsbean 
{
    List<Booking> bookings;

    public bookingsbean() 
    {
        Connection conn = null; Statement stmt1 = null, stmt2=null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    
            stmt1 = conn.createStatement();stmt2 = conn.createStatement();
            stmt1.execute("SELECT Garage, Slotno FROM Bookings WHERE End <= NOW()");
            rs = stmt1.getResultSet();
            while(rs.next())
            {
stmt2.execute("UPDATE Slots SET Availability = 'Yes' WHERE (Garage ='"+rs.getString("Garage")+"' AND Slotno = "+rs.getInt("Slotno")+")");   
            }
        } 
        catch (SQLException ex){
            ex.printStackTrace();
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            if (rs != null)   {try {rs.close();} catch (SQLException sqlEx) { } rs = null;}
            if (stmt1 != null) {try {stmt1.close();} catch (SQLException sqlEx) { } stmt1 = null;}
            if (stmt2 != null) {try {stmt2.close();} catch (SQLException sqlEx) { } stmt2 = null;}
            if (conn != null) {try {conn.close ();}catch (Exception e) {} }
        }
    }
    boolean addbooking(String owner, String customer, String phone, String garage, String model, int slotno, String startstr, String endstr, String paid) throws ParseException
    {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Timestamp start = new Timestamp(f.parse(startstr).getTime());
        Timestamp end = new Timestamp(f.parse(endstr).getTime());
        if(paid.equalsIgnoreCase("yes"))
            paid = "Yes";
        else
            paid = "No";
        int i=-1;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT COUNT(*) FROM Bookings");
            rs = stmt.getResultSet(); rs.next();
            int count = rs.getInt(1)+1;
            stmt.execute("SELECT Price,Availability FROM Slots WHERE (Garage ='"+garage+"' AND Slotno="+slotno+")");
            rs = stmt.getResultSet(); rs.next();
            if(rs.getString("Availability").equalsIgnoreCase("no"))
            {
                System.out.println("slot unavailable");
                return false;    
            }
            int cost = rs.getInt("Price");
            //System.out.println("cost = "+cost);
            cost = cost *(int)((end.getTime() - start.getTime())/(60*60*1000));
            //System.out.println(count+" "+owner+" "+customer+" "+phone+" "+garage+" "+model+" "+slotno+" "+new SimpleDateFormat("dd-mm-yyyy hh:mm:ss").format(start)+" "+new SimpleDateFormat("dd-mm-yyyy hh:mm:ss").format(end)+" "+cost+" "+paid+" ");
            PreparedStatement ps=conn.prepareStatement("INSERT INTO Bookings VALUES (?,?,?,?,?,?,?,?,?,?,?)");  
            ps.setInt(1,count);ps.setString(2,owner);ps.setString(3,customer);ps.setString(4,phone);
            ps.setString(5,garage);ps.setString(6,model);ps.setInt(7,slotno);ps.setTimestamp(8,start);
            ps.setTimestamp(9,end);ps.setInt(10,cost);ps.setString(11,paid);
            ps.execute(); 
            //ps.setTimestamp(3,new java.sql.Timestamp(Calendar.getInstance().getTime()));
            //ps.execute();
            ps=conn.prepareStatement("UPDATE Slots SET Availability = 'No' WHERE (Garage =? AND Slotno =?)");  
            ps.setString(1,garage);ps.setInt(2,slotno);
            i=ps.executeUpdate();    
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
        if(i>0)  
            return true;
        else if(i==-1){
            System.out.println("error in availability");
            return false;    
        }
        else {
            System.out.println("error in update slot");
            return false;    
        }
            
    }
    public List<Booking> getbyunpaid(String owner)
    {
        bookings = new ArrayList<Booking>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            /*stmt.execute("SELECT COUNT(*) FROM Bookings");
            rs = stmt.getResultSet(); rs.next();
            int count = rs.getInt(1);*/
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM Bookings Where (Owner = ? AND Paid = 'No' AND End<= NOW())");  
            ps.setString(1,owner);
            //ps.setTimestamp(3,new java.sql.Timestamp(Calendar.getInstance().getTime()));
            rs = ps.executeQuery();
            rs.last();
            //System.out.println("getbyunpaidRows = "+rs.getRow());
            rs.beforeFirst();
            while(rs.next())
            {
                bookings.add(new Booking
     (rs.getInt("BookingNo"),rs.getString("Owner"),rs.getString("Customer"),rs.getString("Phone"),rs.getString("Garage"),rs.getString("Model"),
                        rs.getInt("Slotno"),rs.getTimestamp("Start"),rs.getTimestamp("End"),rs.getInt("Cost"),rs.getString("Paid")));
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
        return bookings;
    }
    public List<Booking> getbycurrent(String owner)
    {
        bookings = new ArrayList<Booking>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            /*stmt.execute("SELECT COUNT(*) FROM Bookings");
            rs = stmt.getResultSet(); rs.next();
            int count = rs.getInt(1);*/
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM Bookings Where (Owner = ? AND End > NOW())");  
            ps.setString(1,owner);
            //ps.setTimestamp(3,new java.sql.Timestamp(Calendar.getInstance().getTime()));
            rs = ps.executeQuery();
            rs.last();
            //System.out.println("getbycurrentRows = "+rs.getRow());
            rs.beforeFirst();
            while(rs.next())
            {
                bookings.add
                (new Booking(rs.getInt("BookingNo"),rs.getString("Owner"),rs.getString("Customer"),rs.getString("Phone"),rs.getString("Garage"),rs.getString("Model"),
                        rs.getInt("Slotno"),rs.getTimestamp("Start"),rs.getTimestamp("End"),rs.getInt("Cost"),rs.getString("Paid")));
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
        return bookings;
    }
    public List<Booking> getbyover(String owner)
    {
        bookings = new ArrayList<Booking>();
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            /*stmt.execute("SELECT COUNT(*) FROM Bookings");
            rs = stmt.getResultSet(); rs.next();
            int count = rs.getInt(1);*/
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM Bookings Where (Owner = ? AND Paid = 'Yes' AND End< NOW())");  
            ps.setString(1,owner);
            //ps.setTimestamp(3,new java.sql.Timestamp(Calendar.getInstance().getTime()));
            rs = ps.executeQuery();
            rs.last();
            //System.out.println("getbyoverRows = "+rs.getRow());
            rs.beforeFirst();
            while(rs.next())
            {
                bookings.add
                (new Booking(rs.getInt("BookingNo"),rs.getString("Owner"),rs.getString("Customer"),rs.getString("Phone"),rs.getString("Garage"),rs.getString("Model"),
                        rs.getInt("Slotno"),rs.getTimestamp("Start"),rs.getTimestamp("End"),rs.getInt("Cost"),rs.getString("Paid")));
            }         
        } 
        catch (SQLException ex){ ex.printStackTrace(); }
        catch (Exception e){e.printStackTrace();}
        finally {
            if (rs != null)   {try {rs.close();} catch (SQLException sqlEx) { } rs = null;}
            if (stmt != null) {try {stmt.close();} catch (SQLException sqlEx) { } stmt = null;}
            if (conn != null) {try {conn.close ();}catch (Exception e) {} }
        }
        return bookings;
    }
    public List<Booking> getsummary(String start, String end, String owner)
    {
        bookings = new ArrayList<Booking>();
        //System.out.println("Start concat : "+start.concat(" 00:00:00")+" End concat : "+end.concat(" 23:59:59"));
        //System.out.println("Start  : "+start+" End  : "+end);
        start = start.concat(" 00:00:00");
        end = end.concat(" 23:59:59");
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT COUNT(*) FROM Bookings");
            rs = stmt.getResultSet(); rs.next();
            int count = rs.getInt(1);
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM Bookings Where (Owner = ? AND Start >= ? AND End<= ?)");  
            ps.setString(1,owner);ps.setString(2,start);ps.setString(3,end);
            //ps.setTimestamp(3,new java.sql.Timestamp(Calendar.getInstance().getTime()));
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Booking booking = new Booking(count,rs.getString("Owner"),rs.getString("Customer"),rs.getString("Phone"),rs.getString("Garage"),rs.getString("Model"),
                        rs.getInt("Slotno"),rs.getTimestamp("Start"),rs.getTimestamp("End"),rs.getInt("Cost"),rs.getString("Paid"));
                bookings.add(booking);
                //booking.toString();
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
        return bookings;
    }
}
