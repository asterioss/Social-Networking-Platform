

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class createPost
 */
@WebServlet("/createPost")
public class createPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createPost() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	response.setContentType("text/html;charset=UTF-8"); 
        RequestDispatcher dispatcher
        = getServletContext().getRequestDispatcher("/WEB-INF/post.html");
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
