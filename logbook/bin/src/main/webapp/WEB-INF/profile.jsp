<%@page import="gr.csd.uoc.cs359.winter2019.logbook.model.User"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.db.UserDB"%>
<%@page import="gr.csd.uoc.cs359.winter2019.logbook.ServerUt"%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title> 
    <meta charset="UTF-8">
    <link rel="stylesheet" href="js/logbook.css">
    <script src="js/poutsa.js"></script>
</head>
<body>
<div class="container">
    <button class="button1" onclick="home()">Home</button>
    <button type="button" class="button" onclick="createpost();">Create Posts</button>

<%
      User ekei = UserDB.getUser(request.getParameter("eskereee"));
      System.out.println(ekei.getUserName());
      User toreturn = UserDB.getUser(request.getParameter("username"));
      toreturn.setUserName(ServerUt.filter(toreturn.getUserName()));
      toreturn.setAddress(ServerUt.filter(toreturn.getAddress()));
      toreturn.setBirthDate(ServerUt.filter(toreturn.getBirthDate()));
      toreturn.setCountry(ServerUt.filter(toreturn.getCountry()));
      toreturn.setEmail(ServerUt.filter(toreturn.getEmail()));
      toreturn.setFirstName(ServerUt.filter(toreturn.getFirstName()));
      toreturn.setGender(ServerUt.filter(toreturn.getGender().toString()));
      toreturn.setInfo(ServerUt.filter(toreturn.getInfo()));
      toreturn.setInterests(ServerUt.filter(toreturn.getInterests()));
      toreturn.setLastName(ServerUt.filter(toreturn.getLastName()));
      toreturn.setOccupation(ServerUt.filter(toreturn.getOccupation()));
      toreturn.setPassword(ServerUt.filter(toreturn.getPassword()));
      toreturn.setTown(ServerUt.filter(toreturn.getTown()));
%>

<h1><strong>Profile</strong></h1>
<%
     String name = toreturn.getUserName();
     String address = toreturn.getAddress();
     String birthdate = toreturn.getBirthDate();
     String country = toreturn.getCountry();
     String email = toreturn.getEmail();
     String firstname = toreturn.getFirstName();
     String gender = toreturn.getGender().toString();
     String info = toreturn.getInfo();
     String interests = toreturn.getInterests();
     String lastname = toreturn.getLastName();
     String occupation = toreturn.getOccupation();
     String password = toreturn.getPassword();
     String town = toreturn.getTown();
     
%>

<h2>Username: <%= name %></h2>
<h2>Email: <%= email %></h2>
<h2>Password: <%= password %></h2>
<h2>First Name: <%= firstname %></h2>
<h2>Last Name: <%= lastname %></h2>
<h2>Address: <%= address %></h2>
<h2>Country: <%= country %></h2>
<h2>Town: <%= town %></h2>
<h2>BirthDate: <%= birthdate %></h2>
<h2>Gender: <%= gender %></h2>
<h2>Occupation: <%= occupation %></h2>
<h2>Interests: <%= interests %></h2>
<h2>Info: <%= info %></h2>
</div>
   
</body>
</html>
