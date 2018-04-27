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
    <style type="text/css">
        .button {
            margin-right: 20px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<s:include value="header.jsp"/>
<div id="content" class="container">
    <h3>Enter New Values</h3>
    <s:form action="changeValues" theme="simple">
        <s:hidden name="productData.productID" value="%{productData.productID}"/>
        <s:hidden name="productData.productType" value="%{productData.productType}"/>
        <div class="row">
            <div class="col-md-2">
                PRODUCT ID: <s:property value="productData.productID"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-5">
                <s:label>PRODUCT TYPE: <s:property value="%{productData.productType}"/> </s:label>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>PRODUCT NAME: </label>

            </div>
            <div class="col-md-3">
                <s:textfield label="PRODUCT NAME" name="productData.productName" class="form-control"
                             value="%{productData.productName}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>PRODUCT AMOUNT: </label>

            </div>
            <div class="col-md-3">
                <s:textfield type="number" label="PRODUCT AMOUNT" name="productData.productAmount" class="form-control"
                             value="%{productData.productAmount}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>PRODUCT PRICE: </label>

            </div>
            <div class="col-md-3">
                <s:textfield type="number" class="form-control" name="productData.productPrice"
                             value="%{productData.productPrice}"/>
            </div>
        </div>
        <s:iterator value="productTableColumnNames" status="row">
            <div class="row">
                <div class="col-md-2">
                    <s:property/>
                </div>
                <div class="col-md-2">
                    <s:textfield name="productSpecValueList[%{#row.index}]" value="%{productSpecificationsData[#row.index]}" class="form-control"/>
                </div>
            </div>
        </s:iterator>
        <div class="row">
            <div class="col-md-12">
                <s:submit class="btn btn-info button" value="Change values"/>

                <s:url var="deleteProductUrl" action="deleteProductEntry">
                    <s:param value="%{productData.productID}" name="productData.productID"/>
                    <s:param  value="%{productData.productType}" name="productData.productType"/>
                </s:url>
                <s:a class="btn btn-danger button" href="%{deleteProductUrl}">Delete entry</s:a>

                <s:url var="productTableUrl" action="fetchProductTable"/>
                <s:a class="btn btn-info button" href="%{productTableUrl}">Back</s:a>
            </div>
        </div>
    </s:form>
</div>
</div>
</body>
</html>
