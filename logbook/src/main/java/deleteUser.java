

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

import gr.csd.uoc.cs359.winter2019.logbook.db.CommentDB;
import gr.csd.uoc.cs359.winter2019.logbook.db.PostDB;
import gr.csd.uoc.cs359.winter2019.logbook.db.RatingDB;
import gr.csd.uoc.cs359.winter2019.logbook.db.UserDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Comment;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import gr.csd.uoc.cs359.winter2019.logbook.model.Rating;
import gr.csd.uoc.cs359.winter2019.logbook.model.User;

/**
 * Servlet implementation class deleteUser
 */
@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	response.setContentType("text/html;charset=UTF-8"); 
    	String name = request.getParameter("username");
    	User user = UserDB.getUser(request.getParameter("username"));
    	//System.out.println(user.getUserName());
    	//User user1 = UserDB.getUser("asterios");
    	//System.out.println(user1.getPassword());
    	List<Post> posts = new ArrayList<Post>();
    	posts = PostDB.getPosts();
    	for(int i=0; i<posts.size(); i++) {
    		//System.out.println(posts.get(i).getUserName()+" "+name);
    		if(posts.get(i).getUserName().equals(name)) {
    			PostDB.deletePost(posts.get(i));
    		}
    	}
    	List<Rating> rates = new ArrayList<Rating>();
    	rates = RatingDB.getRatings();
    	for(int j=0; j<rates.size(); j++) {
    		//System.out.println(posts.get(i).getUserName()+" "+name);
    		if(rates.get(j).getUserName().equals(name)) {
    			RatingDB.deleteRating(rates.get(j));
    		}
    	}
    	List<Comment> coms = new ArrayList<Comment>();
    	coms = CommentDB.getComments();
    	for(int k=0; k<coms.size(); k++) {
    		//System.out.println(posts.get(i).getUserName()+" "+name);
    		if(coms.get(k).getUserName().equals(name)) {
    			CommentDB.deleteComment(coms.get(k));
    		}
    	}
    	UserDB.deleteUser(user);
        RequestDispatcher dispatcher
        = getServletContext().getRequestDispatcher("/WEB-INF/signup.html");
        dispatcher.include(request, response);

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
