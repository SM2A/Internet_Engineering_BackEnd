<%@ page import="ir.ut.ece.ie.util.Factory" %>
<%@ page import="ir.ut.ece.ie.util.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<ul>
    <li id="username">username: <%= Factory.getUserController().getLoggedInUser().getUsername() %>
    </li>
    <li>
        <a href=<%=Path.Web.COMMODITIES%>>Commodities</a>
    </li>
    <li>
        <a href=<%=Path.Web.BUYLIST%>>Buy List</a>
    </li>
    <li>
        <a href=<%=Path.Web.CREDIT%>>Add Credit</a>
    </li>
    <li>
        <a href=<%=Path.Web.LOGOUT%>>Log Out</a>
    </li>
</ul>

</body>
</html>
