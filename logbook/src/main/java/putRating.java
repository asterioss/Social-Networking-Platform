

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

import gr.csd.uoc.cs359.winter2019.logbook.ServerUt;
import gr.csd.uoc.cs359.winter2019.logbook.db.PostDB;
import gr.csd.uoc.cs359.winter2019.logbook.db.RatingDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import gr.csd.uoc.cs359.winter2019.logbook.model.Rating;

/**
 * Servlet implementation class putRating
 */
@WebServlet("/putRating")
public class putRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void gotoPage(String address,
			HttpServletRequest request,
			 HttpServletResponse response)
			throws ServletException, IOException {
			 RequestDispatcher dispatcher =
			 getServletContext().getRequestDispatcher(address);
			 dispatcher.include(request, response);
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public putRating() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	int rating = Integer.parseInt(request.getParameter("rate"));
		String username = request.getParameter("username");
		int id = Integer.parseInt(request.getParameter("id"));
		Rating rate = new Rating();
		rate.setUserName(username);
		rate.setRate(rating);
		rate.setPostID(id);
		RatingDB.addRating(rate);
		gotoPage("/WEB-INF/readyrating.html", request, response);
		
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
