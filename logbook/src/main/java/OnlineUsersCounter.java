

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineUsersCounter
 *
 */
@WebListener
public class OnlineUsersCounter implements HttpSessionListener {
	private List<String> sessions = new ArrayList<>();
    public static final String COUNTER = "session-counter";
 
    public int getActiveSessionNumber() {
        return sessions.size();
    }
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent event)  {
    	System.out.println("SessionCounter.sessionCreated");
        HttpSession session = event.getSession();
        sessions.add(session.getId());
        session.setAttribute(OnlineUsersCounter.COUNTER, this);
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event)  { 
    	System.out.println("SessionCounter.sessionDestroyed");
        HttpSession session = event.getSession();
        sessions.remove(session.getId());
        session.setAttribute(OnlineUsersCounter.COUNTER, this);
         // TODO Auto-generated method stub
    }
	
}
