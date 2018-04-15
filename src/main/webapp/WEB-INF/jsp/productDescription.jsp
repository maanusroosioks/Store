<%--
  Created by IntelliJ IDEA.
  User: Maanus
  Date: 05-Apr-18
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title>Product Description</title>
</head>
<body>
<s:include value="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <label>Product type: </label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productData.productType"/> </s:label>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[0]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:property value="productSpecificationsData[0]"/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[1]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productSpecificationsData[1]"/> </s:label>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[2]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productSpecificationsData[2]"/> </s:label>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[3]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productSpecificationsData[3]"/> </s:label>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[4]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productSpecificationsData[4]"/> </s:label>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[5]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productSpecificationsData[5]"/> </s:label>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[6]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productSpecificationsData[6]"/> </s:label>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <s:label> <s:property value="productTableColumnNames[7]"/> </s:label>
        </div>
        <div class="col-md-2">
            <s:label> <s:property value="productSpecificationsData[7]"/> </s:label>
        </div>
    </div>

    <<s:url var="addToShoppingCart" action="addToShoppingCart">
        <s:param value="productData.productName" name="productData.productName"/>
        <s:param value="productData.productID" name="productData.productID"/>
        <s:param value="productData.productType" name="productData.productType"/>
    </s:url>
    <s:a class="btn btn-danger" href="%{addToShoppingCart}">Add to shopping cart</s:a>
</div>
</body>
</html>
