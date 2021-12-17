

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.csd.uoc.cs359.winter2019.logbook.db.CommentDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Comment;

/**
 * Servlet implementation class deleteComment
 */
@WebServlet("/deleteComment")
public class deleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteComment() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	String comment = request.getParameter("com");
    	int id = Integer.parseInt(request.getParameter("id"));
    	CommentDB.deleteComment(id);
		//gotoPage("/WEB-INF/readycomment.html", request, response);
    	RequestDispatcher dispatcher
        = getServletContext().getRequestDispatcher("/WEB-INF/deletecomment.html");
        dispatcher.include(request, response);
        out.close();
        //List<Post> posts = getTop10RecentPostsOfUser(filter(request.getParameter("username")));

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
