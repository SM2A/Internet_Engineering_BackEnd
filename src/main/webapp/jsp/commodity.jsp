<%@ page import="ir.ut.ece.ie.util.Path" %>
<%@ page import="ir.ut.ece.ie.util.Factory" %>
<%@ page import="ir.ut.ece.ie.domain.commodity.Commodity" %>
<%@ page import="java.util.List" %>
<%@ page import="ir.ut.ece.ie.domain.commodity.Comment" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% Commodity commodity = (Commodity) request.getAttribute("commodity"); %>
<% String username = Factory.getUserController().getLoggedInUser().getUsername(); %>
<% String pagePath = "/commodities/"+commodity.getId(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%=commodity.getName()%>
    </title>
    <style>
        li {
            padding: 5px;
        }

        table {
            width: 50%;
            text-align: center;
        }
    </style>
</head>
<body>
<a href=<%=Path.Web.HOME%>>Home</a>
<p id="username">username: <%=username%>
</p>
<br><br>
<div id="content">
    <ul>
        <li id="id">Id: <%=commodity.getId()%>
        </li>
        <li id="name">Name: <%=commodity.getName()%>
        </li>
        <li id="providerId">Provider Id: <%=commodity.getProviderId()%>
        </li>
        <li id="price">Price: <%=commodity.getPrice()%>
        </li>
        <li id="categories">Categories: <%=commodity.getCategories()%>
        </li>
        <li id="rating">Rating: <%=commodity.getRating()%>
        </li>
        <li id="inStock">In Stock: <%=commodity.getInStock()%>
        </li>
    </ul>
    <form action="<%=pagePath%>" method="POST">
        <label>Add your comment:</label>
        <br/>
        <input type="text" name="content">
        <button type="submit" name="action" value="comment">Submit</button>
    </form>
    <br/>
    <form action="<%=pagePath%>" method="POST">
        <label>Rate(between 1 and 10):</label>
        <input type="number" name="content" min="1" max="10">
        <button type="submit" name="action" value="rate">Rate</button>
    </form>
    <br>
    <form action="<%=pagePath%>" method="POST">
        <button type="submit" name="action" value="add">Add to BuyList</button>
    </form>
    <br/>
    <h3>Comments</h3>
    <table>
        <tr>
            <th>username</th>
            <th>comment</th>
            <th>date</th>
            <th></th>
            <th></th>
        </tr>
        <% for (Comment comment : (List<Comment>) request.getAttribute("comments")) { %>
        <tr>
            <td><%=comment.getUserEmail()%>
            </td>
            <td><%=comment.getText()%>
            </td>
            <td><%=comment.getDate()%>
            </td>
            <td>
                <form action="<%=pagePath%>" method="POST">
                    <label><%=comment.getLikes()%></label>
                    <input type="hidden" name="comment_id" value="<%=comment.getId()%>"/>
                    <button type="submit" name="action" value="like">like</button>
                </form>
            </td>
            <td>
                <form action="<%=pagePath%>" method="POST">
                    <label><%=comment.getDislikes()%></label>
                    <input type="hidden" name="comment_id" value="<%=comment.getId()%>"/>
                    <button type="submit" name="action" value="dislike">dislike</button>
                </form>
            </td>
        </tr>
        <%}%>
    </table>

    <h3>Suggested Commodities</h3>
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
        <% for (Commodity c : (List<Commodity>) request.getAttribute("suggestedCommodities")) { %>
        <tr>
            <td><%=c.getId()%>
            </td>
            <td><%=c.getName()%>
            </td>
            <td><%=c.getProviderId()%>
            </td>
            <td><%=c.getPrice()%>
            </td>
            <td><%=c.getCategories()%>
            </td>
            <td><%=c.getRating()%>
            </td>
            <td><%=c.getInStock()%>
            </td>
            <td><a href="/commodities/<%=c.getId()%>">Link</a></td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>