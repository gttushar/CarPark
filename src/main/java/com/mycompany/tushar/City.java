package com.mycompany.tushar;
import java.util.*; 
import java.lang.*; 
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import com.mycompany.tushar.Garage;
class Node
{
    int n;
    String name;
    public Node(int n, String name){
        this.n = n;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" + "n=" + n + ", name=" + name + '}';
    }
}
public class City 
{
    static final int V = 20; 
     ArrayList<Node> marks;
     int graph[][];
     List<Garage> garages = new ArrayList<Garage>(); 
    public class Garage {
        public String name;
        public  String owner;
        public  String address;
        public  String landmark;
        public   String phone;
        public  int rating;

        @Override
        public String toString() {
            return "Garage{" + "name=" + name + ", owner=" + owner + ", address=" + address + ", landmark=" + landmark + ", phone=" + phone + ", rating=" + rating + '}';
        }
        public Garage(String name, String owner, String address, String landmark, String phone, int rating) {
            this.name = name;
            this.owner = owner;
            this.address = address;
            this.landmark = landmark;
            this.phone = phone;
            this.rating = rating;
        }
    }
             

    public City()
    {
        marks = new ArrayList<Node>();
        marks.add(new Node(0, "MainBuilding"));  
        marks.add(new Node(1, "Netaji"));  
        marks.add(new Node(2, "Tikka"));  
        marks.add(new Node(3, "MT"));  
        marks.add(new Node(4, "TGH"));  
        marks.add(new Node(5, "PANloop"));  
        marks.add(new Node(6, "HJB"));  
        marks.add(new Node(7, "SNIG"));  
        marks.add(new Node(8, "VS"));  
        marks.add(new Node(9, "LBS"));  
        marks.add(new Node(10, "LLR"));
        marks.add(new Node(11, "MS"));
        marks.add(new Node(12, "RP"));
        marks.add(new Node(13, "ECdept"));
        marks.add(new Node(14, "MEdept"));
        marks.add(new Node(15, "CYdept"));
        marks.add(new Node(16, "NehruMuseum"));
        marks.add(new Node(17, "Nalanda"));
        marks.add(new Node(18, "AGdept"));
        marks.add(new Node(19, "PuriGate"));
        graph = new int[][] {        {0,9,0,0,0,0,0,11,10,0,0,0,0,13,0,0,0,0,0,0},
                                     {9,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0},
                                     {0,6,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                     {0,0,8,0,7,0,0,11,0,0,0,0,0,0,0,0,0,0,0,0},
                                     {0,0,0,7,0,11,12,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                     {0,0,0,0,11,0,12,0,0,10,0,0,0,0,0,0,0,0,0,0},
                                     {0,0,0,0,12,12,0,0,10,0,0,0,0,0,0,0,0,0,0,0},
                                     {11,0,0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                     {10,0,0,0,0,0,10,0,0,0,0,8,0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,10,0,0,0,0,12,0,0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0,0,12,0,5,0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0,8,0,5,0,6,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0,0,0,0,6,0,7,0,0,0,0,0,9},
                                     {13,0,0,0,0,0,0,0,0,0,0,0,7,0,11,0,0,0,0,0},
                                     {0,0,0,0,0,0,0,0,0,0,0,0,0,11,0,5,0,0,0,0},
                                     {0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10,0,0,0},
                                     {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,0,12,0,0},
                                     {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,7,0},
                                     {0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0},
                                     {0,0,0,0,0,0,0,0,0,0,0,0,9,0,0,0,0,0,0,0}};
    }
    public int minDistance(int dist[], Boolean sptSet[]) 
    { 
        int min = Integer.MAX_VALUE, min_index = -1; 
        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                min_index = v; 
            }
        }
        return min_index; 
    } 
     List<Node> solution(int dist[]) 
    { 
        class Element implements Comparable<Element> {
            int index, value;
            Element(int index, int value){
                this.index = index;
                this.value = value;
            }
            @Override
            public int compareTo(Element e) {
                return this.value - e.value;
            }
        }
        List<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < dist.length; i++) {
            elements.add(new Element(i, dist[i]));
        }
        Collections.sort(elements);
        List<Node> list = new ArrayList<Node>();
        int i=0;
        for (Element element : elements) {
            list.add(i, marks.get(element.index));
            i++;
        } 
        return list;
    } 
    List<Node> dijkstra(int src) 
    { 
        int dist[] = new int[V];
        Boolean sptSet[] = new Boolean[V]; 
        for (int i = 0; i < V; i++) { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
        dist[src] = 0; 
        for (int count = 0; count < V - 1; count++) { 
            int u = minDistance(dist, sptSet); 
            sptSet[u] = true; 
            for (int v = 0; v < V; v++) { 
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) 
                    dist[v] = dist[u] + graph[u][v]; 
            }
        }
        return solution(dist); 
    }
    public List<Garage> searchgarages(int mark)
    {
        List<Node> list = dijkstra(mark);
        Connection conn = null; Statement stmt1 = null, stmt2 = null; ResultSet rs1 = null, rs2=null, rs3=null;
        try{
            String userName = "root"; String password = "Abcd@1234";
            String url = "jdbc:mysql://localhost:3306/testdb";
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);    
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            String landmark;
            String owner;
            String name;
            String address;
            String phone;
            int rating;
            for(Node node : list)
            {
                //System.out.println(node);
                landmark = node.name;
                stmt1.execute("SELECT * FROM Garages WHERE Landmark = '"+landmark+"'");
                rs1 = stmt1.getResultSet();
                while(rs1.next())
                {
                    name = rs1.getString("Name");
                    address = rs1.getString("Address");
                    rating = rs1.getInt("Rating");
                    stmt2.execute("SELECT Name,Phone FROM Owner WHERE Userid = '"+rs1.getString("Owner")+"'");
                    rs2 = stmt2.getResultSet();
                    rs2.next();
                    phone = rs2.getString("Phone");
                    owner = rs2.getString("Name");
                    Garage garage = new Garage(name, owner, address, landmark, phone, rating);
                    garages.add(garage);
                    rs2.close();rs2=null;
                }
                rs1.close();rs1=null;
            }
        } 
        catch (SQLException ex){
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch (Exception e){System.out.println ("Cannot connect to database server " + e.getMessage());}
        finally {
            if (rs1 != null)   {try {rs1.close();} catch (SQLException sqlEx) { } rs1 = null;}
            if (rs2 != null)   {try {rs2.close();} catch (SQLException sqlEx) { } rs2 = null;}
            if (stmt1 != null) {try {stmt1.close();} catch (SQLException sqlEx) { } stmt1 = null;}
            if (stmt2 != null) {try {stmt2.close();} catch (SQLException sqlEx) { } stmt2= null;}
            if (conn != null) {try {conn.close ();System.out.println ("Database connection terminated");}catch (Exception e) {} }
        }
        return garages;
    }
}