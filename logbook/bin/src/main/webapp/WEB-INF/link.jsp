<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.PostDB"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.Post"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.ServerUt"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title> 
    <meta charset="UTF-8">
    <link rel="stylesheet" href="js/logbook.css">
    <script src="js/poutsa.js"></script>
</head>
<body>
    
<!-- method="post" class="/servlet" action="servlet"-->
    <div class="container">
        <nav class="w3-bar w3-black">
         <button class="button1" onclick="home()">Home</button>
         <button class="button1" onclick="editprofile()">View profile</button>
         <button class="button1" onclick="showusers()">Show Users</button>
       </nav>
       <div class="test_right">
            <button type="button" class="button" onclick="signout();">Log-out</button>
            <button type="button" class="button" onclick="delete_post();">Delete Latest Post</button>
            <button type="button" class="button" onclick="createpost();">Create Posts</button>
        </div>
        
        
        <%!
            String getTimeAgo(String time) throws ParseException{
                DateFormat format = new SimpleDateFormat("y-M-d H:m:s.S");
                Date date = format.parse(time);
                Date now = new Date();
                 
                long diff = now.getTime() - date.getTime();

		        long diffSeconds = diff / 1000 % 60;
		        long diffMinutes = diff / (60 * 1000) % 60;
		        long diffHours = diff / (60 * 60 * 1000) % 24;
		        long diffDays = diff / (24 * 60 * 60 * 1000);
                long diffMonths = diff / (30 * 24 * 60 * 60 * 1000);
                long diffYears = diff / (12 * 30 * 24 * 60 * 60 * 1000);
                
                if(diffYears>0) {
                	if(diffYears==1) return "Post created "+diffYears+" year ago";
                	else return "Post created "+diffYears+" years ago";
                }
                else if(diffMonths>0) {
                	if(diffMonths==1) return "Post created "+diffMonths+" month ago";
                	else return "Post created "+diffMonths+" months ago";
                }
                else if(diffDays>0) {
                	if(diffDays==1)  return "Post created "+diffDays+" day ago";
                    return "Post created "+diffDays+" days ago";
                }
                else if(diffHours>0) {
                	if(diffHours==1) return "Post created "+diffHours+" hour ago";
                	else return "Post created "+diffHours+" hours ago";
                }                
                else if(diffMinutes>0) {
                	if(diffMinutes==1) return "Post created "+diffMinutes+" minute ago";
                	else return "Post created "+diffMinutes+" minutes ago";
                }
                else if(diffSeconds>0) {
                	if(diffSeconds==1) return "Post created "+diffSeconds+" second ago";
                    else return "Post created "+diffSeconds+" seconds ago"; 
                }
                return "Just Now";
            }
        %>
        
        
     <%
        List<Post> op = new ArrayList<Post>(10);
        op = PostDB.getTop10RecentPosts();
      %>
      <h1>Top 10 recent posts.</h1>
      <%
      for (int i = 0; i < 10; ++i) {
        %>
        <div id="time"><%= getTimeAgo(op.get(i).getCreatedAt())%></div> 
        <h4>Description <%= i+1 %>: <%= op.get(i).getDescription() %></h4>
        <h4>Username <%= i+1 %>: <a href="#" onclick='userprofile("<%= op.get(i).getUserName()%>");return false;'><%= op.get(i).getUserName()%></a></h4>
        <label><b><a href="#" onclick='popup("<%= op.get(i).getPostID()%>");return false;'>Post Details</a></b></label>
        <p>-----------------------------</p>
        <%
      }
        %>
 
    </div>

</body>
</html>