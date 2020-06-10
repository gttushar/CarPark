<%@page import="java.util.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.mycompany.tushar.Booking"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bookingsbean" class="com.mycompany.tushar.bookingsbean" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" type="text/css" href="mystyle.css">-->
<!--        <link rel="stylesheet" type="text/css" href="table.css">-->
        <title>Manage your Bookings</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">   
        <style>
        table {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
}

table td, table th {
  border: 1px solid #ddd;
  padding: 8px;
}

table tr:nth-child(even){background-color: #f2f2f2;}

table tr:hover {background-color: #ddd;}

table th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
        </style>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
            //response.setHeader("Pragma", "no-cache");  HTTP older than 1.1
            //response.setHeader("Expires", "0");   For proxy server (cache expires in 0 sec)   
            if(session.getAttribute("user") == null)
                response.sendRedirect("login.jsp");
        %>
        <!-- Navbar (sit on top) -->
        <div class="w3-top">
          <div class="w3-bar w3-white w3-wide w3-padding w3-card">
            <a href="ownerhome.jsp" class="w3-bar-item w3-button"><b>CARPARK</b> Home</a>
            <!-- Float links to the right. Hide them on small screens -->
            <div class="w3-right w3-hide-small">
              <a href="about.jsp" class="w3-bar-item w3-button">About</a>
              <a href="logout" class="w3-bar-item w3-button">Logout</a>
            </div>
          </div>
        </div>
        <br><br><br>
        <div class="w3-left w3-cyan w3-hide-small">
            Owner : <b>${user}</b>
        </div>
        <div class="w3-right w3-hide-small">
            <%
            String owner = session.getAttribute("user").toString();
            out.println("CURRENT DATETIME : "+ new Date().toString());
            %>
        </div>
        <center>
        <br><br>
        <br>
        1. Add a Booking<br>
            <form action="addbooking.jsp">
                &emsp;Enter Garage name : <input type ="text" name ="garage"> <br>
                <input type="submit" value="Add Booking">
            </form>
        <br><br>
        2. Mark a Booking as Paid
            <form action="paidbooking">
            &emsp;Enter Booking No. : <input type ="text" name ="bookingno"> <br>
            <input type="submit" value="Mark as Paid">
            </form>
        <br><br>
        3. View a summary of your Bookings
            <form action="summary.jsp">
            &emsp;Enter Start Date (in YYYY/MM/DD format) : <input type ="text" name ="start"> <br>
            &emsp;Enter End Date (in YYYY/MM/DD format) : <input type ="text" name ="end"> <br>
            <input type="submit" value="Get Summary">
            </form>
        <br><br>
        <%
            List<Booking> unpaid = bookingsbean.getbyunpaid(owner);
            //List<Slot> getslots = (List<Slot>)request.getAttribute("slots");
            //out.println("<br>No.of slots = " + slots.size());
            //out.println("<br>No.of getslots = " + getslots.size());
            if(unpaid.size()>0)
            {
        %>
        <table style="text/css">
            <caption> <b>Unpaid Bookings</b></caption>
            <tr>
                <td>BookingNo</td><td>CustomerId</td><td>PhoneNo</td><td>Garage</td><td>Model</td><td>SlotNo</td><td>Booking Time</td><td>Booking Ends</td><td>Cost (in Rs.)</td><td>Paid</td>
            </tr>
            
            <%for(int i=0;i<unpaid.size();i++){
            %>
                    <tr>
                        <td><%=unpaid.get(i).number%></td>
                        <td><%=unpaid.get(i).customer%></td>
                        <td><%=unpaid.get(i).phone%></td>
                        <td><%=unpaid.get(i).garage%></td>
                        <td><%=unpaid.get(i).model%></td>
                        <td><%=unpaid.get(i).slotno%></td>
                        <td><%=unpaid.get(i).start%></td>
                        <td><%=unpaid.get(i).end%></td>
                        <td><%=unpaid.get(i).cost%></td>
                        <td><%=unpaid.get(i).paid%></td>
                    </tr>
            <%}%>
            <br><br>
        </table>
        <% 
            }
            
            List<Booking> current = bookingsbean.getbycurrent(owner);
            //List<Slot> getslots = (List<Slot>)request.getAttribute("slots");
            //out.println("<br>No.of slots = " + slots.size());
            //out.println("<br>No.of getslots = " + getslots.size());
            if(current.size()>0)
            {
        %>
        <br>
        <br>
        <table>
            <caption> <b>Current Bookings</b></caption>
            <tr>
            <td>BookingNo</td><td>CustomerId</td><td>PhoneNo</td><td>Garage</td><td>Model</td><td>SlotNo</td><td>Booking Time</td><td>Booking Ends</td><td>Cost (in Rs.)</td><td>Paid</td>
            </tr>
            
            <%for(int i=0;i<current.size();i++){
            %>
                    <tr>
                        <td><%=current.get(i).number%></td>
                        <td><%=current.get(i).customer%></td>
                        <td><%=current.get(i).phone%></td>
                        <td><%=current.get(i).garage%></td>
                        <td><%=current.get(i).model%></td>
                        <td><%=current.get(i).slotno%></td>
                        <td><%=current.get(i).start%></td>
                        <td><%=current.get(i).end%></td>
                        <td><%=current.get(i).cost%></td>
                        <td><%=current.get(i).paid%></td>
                    </tr>
            <%}%>
            <br><br>
        </table>
        <% } %>
        </center>


    </body>
</html>
