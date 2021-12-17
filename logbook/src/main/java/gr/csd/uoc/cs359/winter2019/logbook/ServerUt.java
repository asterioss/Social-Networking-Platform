package gr.csd.uoc.cs359.winter2019.logbook;

public class ServerUt {

	public static String filter(String input) {
	        StringBuffer filtered
	                = new StringBuffer(input.length());
	        char c;
	        int i;
	        for (i = 0; i < input.length(); i++) {
	            c = input.charAt(i);
	            switch (c) {
	                case '<':
	                    filtered.append("&lt;");
	                    break;
	                case '>':
	                    filtered.append("&gt;");
	                    break;
	                case '"':
	                    filtered.append("&quot;");
	                    break;
	                case '&':
	                    filtered.append("&amp;");
	                    break;
	                default:
	                    filtered.append(c);
	            }
	        }
	        return filtered.toString();
	    }
}
