<%@ page import="gr.csd.uoc.cs359.winter2019.logbook.OnlineUsersCounter"%>
<html>
<head>
    <title>Session Counter</title>
</head>
<body>
<% 
   OnlineUsersCounter counter = (OnlineUsersCounter) session.getAttribute(
		   OnlineUsersCounter.COUNTER); 
%>

Number of online user(s): <%= counter.getActiveSessionNumber() %>
</body>
</html>