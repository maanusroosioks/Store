<%--
  Created by IntelliJ IDEA.
  User: Maanus
  Date: 20.03.2018
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Enter new values</title>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
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
    <h3>Enter New Values</h3>
    <s:form action="changevalues" theme="simple">
        <s:hidden type="number" name="productID" value="%{productData.productID}"/>

        <div class="row">
            <div class="form-group">
                <s:label>PRODUCT ID: <s:property value="productID"/> </s:label>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <s:textfield label="PRODUCT NAME" name="productName" class="form-control"
                             value="%{productData.productName}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <s:textfield type="number" label="PRODUCT AMOUNT" name="productAmount" class="form-control"
                             value="%{productData.productAmount}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <s:textfield type="number" label="PRODUCT PRICE" class="form-control" name="productPrice"
                             value="%{productData.productPrice}"/>
            </div>
        </div>
        <div class="row">
            <s:submit class="btn btn-info" value="Change values"/>

            <s:url var="deleteProductUrl" action="deleteproductentry">
                <s:param value="productID" name="productID"/>
            </s:url>
            <s:a class="btn btn-danger" href="%{deleteProductUrl}">Delete entry</s:a>

            <s:url var="productTableUrl" action="fetchProductTable">
            </s:url>
            <s:a class="btn btn-info" href="%{productTableUrl}">Back</s:a>
                </s:form>
        </div>
</div>
</body>
</html>
