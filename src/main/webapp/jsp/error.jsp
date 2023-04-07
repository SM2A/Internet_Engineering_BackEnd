<%@ page import="ir.ut.ece.ie.util.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<a href=<%=Path.Web.HOME %>>Home</a>
<h1>
    Error:
</h1>
<br>
<h3><%= request.getAttribute("errorMsg") %>
</h3>
</body>
</html>