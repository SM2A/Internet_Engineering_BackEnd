<%@ page import="ir.ut.ece.ie.util.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>

<body>
<form method="post" action=<%=Path.Web.LOGIN %>>
    <label>Username:</label>
    <input name="username" type="text"/>
    <br>
    <label>Password:</label>
    <input name="password" type="text"/>
    <br>
    <button type="submit">Login!</button>
</form>
</body>
</html>
