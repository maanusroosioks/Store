<!DOCTYPE html PUBLIC
        "-//W3C//DTD XHTML 1.1 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>


<html>
<head>
    <title>Enter values for the new product</title>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="content" class="container">
    <h3>Enter New Values</h3>
    <s:form action="insertnewproduct" theme="simple">
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
            <s:submit class="btn btn-info"/>
        </div>
    </s:form>
</div>
</body>
</html>
