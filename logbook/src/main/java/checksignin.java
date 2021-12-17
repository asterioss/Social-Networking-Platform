

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static gr.csd.uoc.cs359.winter2019.logbook.db.UserDB.getUser;

import gr.csd.uoc.cs359.winter2019.logbook.db.PostDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import gr.csd.uoc.cs359.winter2019.logbook.model.User;
import java.io.*;
import javax.servlet.*; 
import javax.servlet.http.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wikenah
 */
@WebServlet(urlPatterns = {"/checksignin"})
public class checksignin extends HttpServlet {
	private void gotoPage(String address,
			HttpServletRequest request,
			 HttpServletResponse response)
			throws ServletException, IOException {
			 RequestDispatcher dispatcher =
			 getServletContext().getRequestDispatcher(address);
			 dispatcher.include(request, response);
			 }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	
        //response.setContentType("text/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");  
        //System.out.println("skata");
        PrintWriter out = response.getWriter();
        RequestDispatcher rdObj = null;
        	
           
            String uname = request.getParameter("username");
            String pwd = request.getParameter("password");
            //System.out.println(uname+" "+pwd);
            User user = getUser(uname);
            if(user==null) {
            	out.write("Wrong username/password");
            	return;
            }
            if (uname == null || pwd == null || uname.length() == 0 || pwd.length() == 0) {
            	out.write("Wrong username/password");
            	//rdObj = request.getRequestDispatcher("/index.html");
                return;
            }
            else if (user != null) {
            	Random rand = new Random();
                String usr_pwd = user.getPassword();
                //System.out.println(usr_pwd);
                if (usr_pwd.equals(pwd)) {
                	//System.out.println("logged in");
                    HttpSession mySession = request.getSession();
                    //request.login(uname, pwd);
                    //mySession.setAttribute("name", uname);
                    //mySession.setAttribute("password", pwd);
                    Cookie persistent = new Cookie("persistent_test", ""+rand.nextInt());
                    persistent.setMaxAge(3600);
                    response.addCookie(persistent);
                   // mySession.removeAttribute("persistent_test");
                   // mySession.invalidate();
                    //mySession.setMaxInactiveInterval(300);
                    //System.out.println("Creating Session " + mySession.getId());
                    System.out.println("Success!");
                    mySession.setAttribute("user", user);
                    List<Post> list = new ArrayList<>();
                    list = PostDB.getTop10RecentPosts();
                    request.setAttribute("name", "eskereee");
                    //for(Post post : list) {
                    //	out.write(i+". Title: "+post.getDescription()+"\n"+"UserID: "+post.getUserName()+"\n"+"---------");               
                        //System.out.println(post.getDescription());
                     
                	//out.println("<a href=" + response.encodeURL("servlet")
                	//+ ">" + "View servlet again</a>");

                    gotoPage("/WEB-INF/link.jsp", request, response);
                	//response.sendRedirect(request.getContextPath() + "/servlet");
                    //RequestDispatcher rd = request.getRequestDispatcher("servlet");
                    //rd.forward(request, response);
                    //return;

                    //System.out.println("logged in");
                 
                } else {
                    out.write("Wrong username/password");
                    return;
                    //rdObj = request.getRequestDispatcher("/index.html");
                    //out.close(); 
                }
            }
            
            out.close();
        
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checksignin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checksignin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
