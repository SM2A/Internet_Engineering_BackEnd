<%@ page import="ir.ut.ece.ie.util.Path" %>
<%@ page import="ir.ut.ece.ie.util.Factory" %>
<%@ page import="ir.ut.ece.ie.domain.commodity.Commodity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Commodities</title>
    <style>
        table {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<a href=<%=Path.Web.HOME%>>Home</a>
<p id="username">username: <%=Factory.getUserController().getLoggedInUser().getUsername()%>
</p>
<br><br>
<form method="post" action=<%=Path.Web.COMMODITIES%>>
    <label>Search:</label>
    <input type="text" name="search" value="">
    <button type="submit" name="action" value="search_by_category">Search By Cagtegory</button>
    <button type="submit" name="action" value="search_by_name">Search By Name</button>
    <button type="submit" name="action" value="clear">Clear Search</button>
</form>
<br><br>
<form method="post" action=<%=Path.Web.COMMODITIES%>>
    <label>Sort By:</label>
    <button type="submit" name="action" value="sort_by_rate">Rate</button>
    <button type="submit" name="action" value="sort_by_price">Price</button>
</form>
<br><br>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Provider Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
        <th>Links</th>
    </tr>
    <% for (Commodity commodity : (List<Commodity>) request.getAttribute("commodities")) { %>
    <tr>
        <td><%=commodity.getId()%>
        </td>
        <td><%=commodity.getName()%>
        </td>
        <td><%=commodity.getProviderId()%>
        </td>
        <td><%=commodity.getPrice()%>
        </td>
        <td><%=commodity.getCategories()%>
        </td>
        <td><%=commodity.getRating()%>
        </td>
        <td><%=commodity.getInStock()%>
        </td>
        <td><a href="/commodities/<%=commodity.getId()%>">Link</a></td>
    </tr>
    <%}%>
</table>
</body>
</html>