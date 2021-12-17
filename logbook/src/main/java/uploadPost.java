import java.io.IOException;
import java.io.PrintWriter;
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
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import gr.csd.uoc.cs359.winter2019.logbook.ServerUt;

//import src.main.java.gr.csd.uoc.cs359.winter2019.logbook.servlets.List;
//import src.main.java.gr.csd.uoc.cs359.winter2019.logbook.servlets.Post;

/**
 * Servlet implementation class uploadPost
 */
@WebServlet("/uploadPost")
public class uploadPost extends HttpServlet {
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
    public uploadPost() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	//System.out.println(request.getParameter("desc"));
    	
    	String name = request.getParameter("username");
    	String desc = request.getParameter("desc");
    	String lat = request.getParameter("lat");
    	String lon = request.getParameter("lon");
    	if(desc.equals("") || lat.equals("") || lon.equals("")) {
    		out.write("Empty fields");
    		return;
    	}
    	String image = request.getParameter("image_url");
    	String base64 = request.getParameter("base64");
    	
    	Post post = new Post();
        post.setUserName(ServerUt.filter(name));
        post.setDescription(ServerUt.filter(desc));
        //post.setResourceURL(ServerUt.filter(request.getParameter("external_url")));
        post.setImageURL(ServerUt.filter(image));
        post.setImageBase64(ServerUt.filter(base64));
        post.setLatitude(lat);
        post.setLongitude(lon);
        PostDB.addPost(post);
        gotoPage("/WEB-INF/readypost.html", request, response);
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
	}

}
