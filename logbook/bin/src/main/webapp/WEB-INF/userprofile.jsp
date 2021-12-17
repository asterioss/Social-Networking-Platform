<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.PostDB"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.Post"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.ServerUt"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.User"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.UserDB"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title> 
    <meta charset="UTF-8">
    <link rel="stylesheet" href="js/logbook.css">
    <script src="js/poutsa.js"></script>
</head>
<body>
    
<!-- method="post" class="/servlet" action="servlet"-->
    <div class="container">
        <nav class="w3-bar w3-black">
         <button class="button1" onclick="home()">Home</button>
       </nav>
    
     <%
       User toreturn = UserDB.getUser(request.getParameter("username"));
       toreturn.setUserName(ServerUt.filter(toreturn.getUserName()));
       System.out.println(toreturn.getUserName());
        List<Post> op = new ArrayList<Post>(10);
        op = PostDB.getTop10RecentPostsOfUser(ServerUt.filter(toreturn.getUserName()));
      %>
      <h1>Top 10 recent posts of user.</h1>
      <%
      for (int i = 0; i < op.size(); ++i) {
        %>
        <h4>Description <%= i+1 %>: <%= op.get(i).getDescription() %></h4>
        <h4>Username <%= i+1 %>: <%= op.get(i).getUserName() %></h4>
        <p>-----------------------------</p>
        <%
      }
        %>
       
    </div>

</body>
</html>