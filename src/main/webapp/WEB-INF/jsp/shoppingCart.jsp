<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <title>Shopping cart</title>
</head>
<body>
<s:include value="header.jsp"/>
<div id="content" class="container">
    <div class="table-responsive" >
        <table class="table">
            <thead>
            <tr>
                <div class="row">
                    <th>Client name</th>
                    <th>Product ID</th>
                    <th>Product name</th>
                    <th>Product amount</th>
                </div>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="shoppingCartDataList">
                <tr>
                    <s:url var="findUserUrl" action="productOverview">
                        <s:param value="productID" name="productID"/>
                    </s:url>
                    <td><s:property value="clientName"/></td>
                    <td><s:a href="%{findUserUrl}"><s:property value="productID"/></s:a></td>
                    <td><s:property value="productName"/></td>
                    <%--<td><s:property value="productPrice"/></td>--%>
                    <td><s:property value="productAmount"/></td>
                    <td><s:url var="removeFromShoppingCart" action="deleteFromShoppingCart">
                        <s:param value="shoppingCartID" name="shoppingCartID"/>
                    </s:url>
                        <s:a class="btn btn-danger" href="%{removeFromShoppingCart}">Remove from Shopping cart</s:a></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>