<%@page import="com.onlineclinicsystem.Patient"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% Patient patient=(Patient)session.getAttribute("patient"); 
out.println("patient.getEmail()");
%>

</body>
</html>