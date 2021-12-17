

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

/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	System.out.println("skata");
    	response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out=response.getWriter();
        RequestDispatcher dispatcher
        = getServletContext().getRequestDispatcher("/WEB-INF/signup.html");
        dispatcher.include(request, response);
		/*out.println("<html>");
        out.println("<head>");
        out.println("<title>Home Page</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>You are successfully registered!</h1>");
        out.println("</body>");
        out.println("</html>");*/ 
        
        out.close();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            process(request, response);
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
            process(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checksignin.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}
