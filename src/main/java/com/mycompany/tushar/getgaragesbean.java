package com.mycompany.tushar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class getgaragesbean 
{
    List<String> garages;

    public getgaragesbean() {
        garages = new ArrayList<String>();
    }
    public List getgarages(String user)
    {
     
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT Name FROM Garages Where Owner = '"+user+"'");
            rs = stmt.getResultSet();
            while(rs.next())
            {
                garages.add(rs.getString("Name"));
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
        return garages;
    }
    public List getallgarages()
    {
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    stmt = conn.createStatement();
            stmt.execute("SELECT Name FROM Garages");
            rs = stmt.getResultSet();
            while(rs.next())
            {
                garages.add(rs.getString("Name"));
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
        return garages;
    }
}
