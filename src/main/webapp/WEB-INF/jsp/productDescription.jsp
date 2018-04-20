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

    <s:iterator value="productTableColumnNames" status="row">
        <div class="row">
            <div class="col-md-2">
                <s:property/>
            </div>
            <div class="col-md-2">
                <s:property value="%{productSpecificationsData[#row.index]}"/>
            </div>
        </div>
    </s:iterator>

    <s:url var="addToShoppingCart" action="addToShoppingCart">
        <s:param value="productData.productName" name="productData.productName"/>
        <s:param value="productData.productID" name="productData.productID"/>
        <s:param value="productData.productType" name="productData.productType"/>
    </s:url>
    <s:a class="btn btn-danger" href="%{addToShoppingCart}">Add to shopping cart</s:a>
</div>
</body>
</html>
