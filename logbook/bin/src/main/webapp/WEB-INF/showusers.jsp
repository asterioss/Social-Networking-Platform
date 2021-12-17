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
    <title>Show Users</title> 
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
       List<User> users = new ArrayList<User>();
       users = UserDB.getUsers();  
      %>
      <h1>All Users.</h1>
      <%
      for (int i = 0; i < users.size(); ++i) {
        %>
        <label><b><a href="#" onclick='userprofile("<%= users.get(i).getUserName()%>");return false;'><%= users.get(i).getUserName()%></a></b></label>
        <%
      }
        %>
       
    </div>

</body>
</html>