<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <title>Products table</title>
</head>
<body>
<div id="content" class="container">
    <div id="header" class="page-header">
        <h5>Links</h5>
        <a href="<s:url action="index"/>">Main page</a>
        <a href="<s:url action="fetchProductTable"/>">Products table</a>
        <a href="<s:url action="productShoppingTable"/>">Shopping table</a>
        <a href="<s:url action="shoppingCartTable"/>">Shopping cart</a>
    </div>
    <div class="table-responsive" >
        <table class="table">
            <thead>
            <tr>
                <div class="row">
                    <th>Product ID</th>
                    <th>Product name</th>
                    <th>Product amount</th>
                </div>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="productsTableDataList">
                <tr>
                    <s:url var="findUserUrl" action="productOverview">
                        <s:param value="productID" name="productID"/>
                    </s:url>
                    <td><s:a href="%{findUserUrl}"><s:property value="productID"/></s:a></td>
                    <td><s:property value="productName"/></td>
                    <td><s:property value="productPrice"/></td>
                    <td><s:url var="addToShoppingCart" action="addToShoppingCart">
                        <s:param value="productID" name="productID"/>
                    </s:url>
                        <s:a class="btn btn-danger" href="%{addToShoppingCart}">Add to shopping cart</s:a></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>