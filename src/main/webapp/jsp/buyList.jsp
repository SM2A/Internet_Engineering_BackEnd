<%@ page import="ir.ut.ece.ie.domain.user.User" %>
<%@ page import="ir.ut.ece.ie.util.Path" %>
<%@ page import="ir.ut.ece.ie.domain.commodity.Commodity" %>
<%@ page import="java.util.List" %>
<%@ page import="ir.ut.ece.ie.service.provider.ProviderService" %>
<%@ page import="ir.ut.ece.ie.service.provider.ProviderServiceImpl" %>
<%@ page import="ir.ut.ece.ie.util.Factory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <style>
        li {
            padding: 5px
        }

        table {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<ul>
    <% User user = (User) request.getAttribute("user"); %>
    <li id="username">Username: <%=user.getUsername()%></li>
    <li id="email">Email: <%=user.getEmail()%></li>
    <li id="birthDate">Birth Date: <%=user.getBirthDate()%></li>
    <li id="address">Address: <%=user.getAddress()%></li>
    <li id="credit">Credit: <%=user.getCredit()%></li>
    <li>Current Buy List Price: <%=request.getAttribute("buyListPrice")%></li>
    <li>
        <a href=<%=Path.Web.CREDIT%>>Add Credit</a>
    </li>
    <li>
        <form action="" method="POST">
            <label>Submit & Pay</label>
            <input id="form_payment" type="hidden" name="userId" value=<%=user.getUsername()%>>
            <button type="submit">Payment</button>
        </form>
    </li>
</ul>
<table>
    <caption>
        <h2>Buy List</h2>
    </caption>
    <tbody>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Provider Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
        <th></th>
        <th></th>
    </tr>
    <% ProviderService providerService = new ProviderServiceImpl(Factory.getProviderRepository()); %>
    <% for (Commodity commodity : (List<Commodity>) request.getAttribute("commodities")) { %>
    <tr>
        <td><%=commodity.getId()%>
        </td>
        <td><%=commodity.getName()%>
        </td>
        <td><%=providerService.getProvider(commodity.getProviderId()).get().getName()%>
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
        <td>
            <form id="removeBuyItem" action="" method="POST" onsubmit="removeFromBuyList()">
                <input id="form_commodity_id" type="hidden" name="commodityId" value=<%=commodity.getId()%>>
                <button type="submit">Remove</button>
            </form>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
<script>
    function removeFromBuyList() {
        const commodityId = document.getElementById("form_commodity_id").value;
        document.getElementById("removeBuyItem").action = "/removeFromBuyList/" + <%=user.getUsername()%> + "/" + commodityId;
    }
</script>
</body>
</html>
