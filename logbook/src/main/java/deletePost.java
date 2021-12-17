import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.csd.uoc.cs359.winter2019.logbook.db.PostDB;
import gr.csd.uoc.cs359.winter2019.logbook.db.UserDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import gr.csd.uoc.cs359.winter2019.logbook.model.User;

/**
 * Servlet implementation class deletePost
 */
@WebServlet("/deletePost")
public class deletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletePost() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	String name = request.getParameter("username");
        List<Post> lol = new ArrayList<Post>(10);
        lol = PostDB.getTop10RecentPostsOfUser(name);
        //int id = lol.get
        if(lol.size()!=0) {
           int id = lol.get(0).getPostID();
           PostDB.deletePost(id);
           RequestDispatcher dispatcher
           = getServletContext().getRequestDispatcher("/WEB-INF/deletedpost.html");
           dispatcher.include(request, response);
        }
        else {
        	System.out.println("Empty list");
        }

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checksignin.class.getName()).log(Level.SEVERE, null, ex);
        }
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checksignin.class.getName()).log(Level.SEVERE, null, ex);
        }
		//doGet(request, response);
	}

}
