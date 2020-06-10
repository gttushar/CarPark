package com.mycompany.tushar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
//@author Tushar
@WebServlet(name="newgaragesession", urlPatterns={"/newgaragesession"})
public class newgaragesession extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String regno = request.getParameter("regno");
        try{
            //File file=new File(getServletContext.getRealPath("/registrations.txt"));    //creates a new file instance  
            //FileReader fr=new FileReader(file);   //reads the file  
            String path = getServletContext().getRealPath("/")+"/";
            FileInputStream fr = new FileInputStream(path+"WEB-INF/registrations.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(fr)));  //creates a buffering character input stream  
            String line;  
            int flag = 0;
            while((line=br.readLine())!=null)  
            {  
                if(regno.equals(line))
                {    flag = 1; break; }
            }  
            fr.close(); 
            if(flag == 0)
                {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Enter a valid Registration Number!!');");
                out.println("location='newgarage.jsp';");
                out.println("</script>");
            }
            else
            {
                session.setAttribute("name", request.getParameter("name"));
                session.setAttribute("regno", request.getParameter("regno"));
                session.setAttribute("address", request.getParameter("address"));
                session.setAttribute("landmark", request.getParameter("landmark"));
                request.getRequestDispatcher("addslots.jsp").forward(request, response);               
            }
        }
        catch(IOException e)
            { out.println(e.getMessage());}
        
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
