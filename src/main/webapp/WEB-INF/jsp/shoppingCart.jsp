<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
<s:include value="header.jsp"/>
<div id="content" class="container">
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Client name</th>
                <th>Product ID</th>
                <th>Product name</th>
                <th>Product price</th>
                <th>Product amount</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="shoppingCartDataList">
                <tr>
                    <s:url var="findUserUrl" action="productOverview">
                        <s:param value="productID" name="productData.productID"/>
                        <s:param value="productName" name="productData.productName"/>
                        <s:param value="productType" name="productData.productType"/>
                    </s:url>
                    <td><s:property value="clientName"/></td>
                    <td><s:a href="%{findUserUrl}"><s:property value="productID"/></s:a></td>
                    <td><s:property value="productName"/></td>
                    <td><s:property value="productPrice"/></td>
                    <td><s:property value="productAmount"/></td>
                    <td><s:url var="removeFromShoppingCart" action="deleteFromShoppingCart">
                        <s:param value="shoppingCartID" name="cartItem.shoppingCartID"/>
                    </s:url>
                        <s:a class="btn btn-danger"
                             href="%{removeFromShoppingCart}">Remove from Shopping cart</s:a></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <s:url var="purchaseProducts" action="generateOrder">
        </s:url>
        <s:a class="btn btn-danger" href="%{purchaseProducts}">Purchase products</s:a>
        <s:url var="emptyShoppingCart" action="emptyShoppingCart">
        </s:url>
        <s:a class="btn btn-danger" href="%{emptyShoppingCart}">Remove all items</s:a>
    </div>
</div>
</body>
</html>