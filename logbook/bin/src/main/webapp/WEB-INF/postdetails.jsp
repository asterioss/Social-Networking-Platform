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
    <title>Post Details</title> 
    <meta charset="UTF-8">
    <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
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
       int id = Integer.parseInt(request.getParameter("id"));
       Post post = PostDB.getPost(id);
       User user = UserDB.getUser(post.getUserName());
      %>
      <h1>Post Details.</h1>
        <h3>Description: <%= post.getDescription() %></h3>
        <h3>Username: <%= post.getUserName() %></h3>  
        <%  if (!post.getImageBase64().equals("")) {%>
           <h3>Image: </h3> 
          <img src="<%= post.getImageBase64().replace(' ', '+')%>" class="margintop" alt="Image" height="auto" width="100%">
       <%} else if (!post.getImageURL().equals("")) {%>
          <h3>Image: </h3> 
          <img src="<%= post.getImageURL()%>" class="margintop" alt="Image" height="auto" width="100%">
       <%} else if (!post.getResourceURL().equals("")) {%>
          <h3>Image: </h3> 
          <img src="<%= post.getResourceURL()%>" class="margintop" alt="Image" height="auto" width="100%">
      <%}%>
      
      <h3>Country: <%=user.getCountry()%></h3>
      <h3>City: <%=user.getTown()%></h3>
      <h3>Address: <%=user.getAddress()%></h3>

      <div id="skr"></div>
      
     <!-- <label><b><a href="#" onclick='fillFields("25.1092992","35.323904");return false;'>GET</a></b></label> -->
      <h4>Longitude: <%=post.getLongitude()%></h4>
      <h4>Latitude: <%= post.getLatitude()%></h4>
     <!-- <label><b><a href="#" onclick='showOnMap("<%=post.getLongitude()%>","<%= post.getLatitude()%>","<%=user.getCountry()%>","<%=user.getTown()%>","<%=user.getAddress()%>");return false;'>Map</a></b></label>-->
      <div class="full">
          <div id="map"></div>
      </div>

       
    </div>

</body>
</html>