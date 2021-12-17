<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.PostDB"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.Post"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.ServerUt"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.User"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.UserDB"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.Rating"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.RatingDB"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.Comment"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.CommentDB"%>
<!DOCTYPE html>
<html>
<head>
    <title>Post Details</title> 
    <meta charset="UTF-8">
    <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    <link rel="stylesheet" href="js/logbook.css">
    <script src="js/extra.js"></script>
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
      <%User me = UserDB.getUser(request.getParameter("username"));%>
      <%List<Rating> rat = new ArrayList<Rating>();
        rat = RatingDB.getRatings(post.getPostID());
        int sum=0;
        int rate=-1;
        if(rat.size()!=0) {
         for(int j=0; j<rat.size(); j++) {
        	if(rat.get(j).getUserName().equals(me.getUserName())) rate=rat.get(j).getRate();
        	//id = Integer.parseInt(rat.get(i).getID());
        	sum+=rat.get(j).getRate();
         } 
        int mo = sum/rat.size();
        %>
      
         <h4>MO: <%= mo %></h4>
         <h4>Number of ratings: <%= rat.size() %></h4>
        <%} %>
        <%if(rate!=-1) {%>
           <h4>My rating: <%= rate %></h4>
        <%} %> 
        
        
        
      <%  List<Comment> com = new ArrayList<Comment>();
        com=CommentDB.getComments(post.getPostID());
        String c="";
        int idd=0;
        if(com.size()!=0) {
        for(int k=0; k<com.size(); k++) {
              if(com.get(k).getUserName().equals(request.getParameter("username"))) {
                 c = com.get(k).getComment();
                 idd=com.get(k).getID();
              }
              else {%>
             <h4>Comment: <%= k+1 %>: <%=com.get(k).getComment()%> | Username <%= k+1 %>: <%=com.get(k).getUserName()%></h4>
             <%}%>
        <%}
        }%>
        
        <h4>Your comment: <%=c%></h4>
        
        <input type="text" id="comment" placeholder="Edit your comment..">
        <label><b><a href="#" onclick='comment("<%= post.getPostID()%>");return false;'>Edit comment</a></b></label>
        <label><b><a href="#" onclick='deletecomment("<%= idd%>");return false;'>Delete comment</a></b></label>
        
    </div>

</body>
</html>