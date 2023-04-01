<%@ page import="ir.ut.ece.ie.util.Path" %>
<%@ page import="ir.ut.ece.ie.util.Factory" %>
<%@ page import="ir.ut.ece.ie.domain.commodity.Commodity" %>
<%@ page import="java.util.List" %>
<%@ page import="ir.ut.ece.ie.domain.commodity.Comment" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% Commodity commodity = (Commodity) request.getAttribute("commodity"); %>
<% String username = Factory.getUserController().getLoggedInUser().getUsername(); %>

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
    <form id="commentForm" action="" method="POST" onsubmit="commentCommodity()">
        <label>Add your comment:</label>
        <br/>
        <input type="text" id="commentMsg" name="content">
        <button type="submit">Submit</button>
    </form>
    <br/>
    <form id="rateCommodityForm" action="" method="POST" onsubmit="rateCommodity()">
        <label>Rate(between 1 and 10):</label>
        <input type="number" id="quantity" name="quantity" min="1" max="10">
        <button type="submit">Rate</button>
    </form>
    <br>
    <form id="buyListForm" action="" method="POST" onsubmit="addToBuyList()">
        <button type="submit">Add to BuyList</button>
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
                <form id="likeForm" action="" method="POST" onsubmit="like()">
                    <label for=""><%=comment.getLikes()%>
                    </label>
                    <input
                            id="form_comment_id"
                            type="hidden"
                            name="comment_id"
                            value="<%=comment.getId()%>"
                    />
                    <button type="submit">like</button>
                </form>
            </td>
            <td>
                <form id="dislikeForm" action="" method="POST" onsubmit="dislike()">
                    <label for=""><%=comment.getDislikes()%>
                    </label>
                    <input
                            id="form_comment_id"
                            type="hidden"
                            name="comment_id"
                            value="<%=comment.getId()%>"
                    />
                    <button type="submit">dislike</button>
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

<script>
    function addToBuyList() {
        document.getElementById("buyListForm").action = "/addToBuyList/" + <%=username%> + "/" + <%=commodity.getId()%>;
    }

    function rateCommodity() {
        document.getElementById("rateCommodityForm").action = "/rateCommodity/" + <%=username%> + "/" + <%=commodity.getId()%> +"/" + document.getElementById("quantity").value;
    }

    function like() {
        document.getElementById("likeForm").action = "/voteComment/" + <%=username%> + "/" + document.getElementById('form_comment_id').value + "/1";
    }

    function dislike() {
        document.getElementById("dislikeForm").action = "/voteComment/" + <%=username%> + "/" + document.getElementById('form_comment_id').value + "/-1";
    }

    function commentCommodity() {
        document.getElementById("commentForm").action = "/addComment/" + <%=username%> + "/" + document.getElementById('commentMsg').value;
    }
</script>

</body>
</html>