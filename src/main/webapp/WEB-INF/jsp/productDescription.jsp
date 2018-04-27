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
    <title>Product Description</title>
</head>
<body>
<s:include value="header.jsp"/>
<div class="container">
    <table class="table">

        <td><label>Product type: </label></td>
        <td><s:property value="productData.productType"/></td>
        <s:iterator value="productTableColumnNames" status="row">
            <tr>
                <td><s:property/></td>
                <td><s:property value="%{productSpecificationsData[#row.index]}"/></td>
            </tr>
        </s:iterator>
    </table>
    <s:url var="addToShoppingCart" action="addToShoppingCart">
        <s:param value="productData.productName" name="productData.productName"/>
        <s:param value="productData.productID" name="productData.productID"/>
        <s:param value="productData.productType" name="productData.productType"/>
    </s:url>
    <s:url var="productTableUrl" action="productShoppingTable"/>
    <s:a class="btn btn-danger" href="%{addToShoppingCart}">Add to shopping cart</s:a>
    <s:a class="btn btn-info button" href="%{productTableUrl}">Back</s:a>
</div>

</body>
</html>
