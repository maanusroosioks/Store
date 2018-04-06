<%--
  Created by IntelliJ IDEA.
  User: Maanus
  Date: 05-Apr-18
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Create new product type</title>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<s:include value="header.jsp"/>
<div class="container">
    <s:form action="createnewproducttable" theme="simple">
        <div class="row">
            <div class="col-md-2">
                <label>Product type: </label>
            </div>
            <div class="col-md-2">
                <s:textfield label="PRODUCT TYPE" name="productType" class="form-control"
                             value="%{productData.productType}"/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-2">
                <s:label> <s:property value="Column 1"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value1" value="%{value1}" class="form-control"/>
            </div>
            <div class="col-md-2">
                <s:label> <s:property value="Column 2"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value2" value="%{value2}" class="form-control"/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-2">
                <s:label> <s:property value="Column 3"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value3" value="%{value3}" class="form-control"/>
            </div>
            <div class="col-md-2">
                <s:label> <s:property value="Column 4"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value4" value="%{value4}" class="form-control"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <s:label> <s:property value="Column 5"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value5" value="%{value5}" class="form-control"/>
            </div>
            <div class="col-md-2">
                <s:label> <s:property value="Column 6"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value6" value="%{value6}" class="form-control"/>
            </div
        </div>
        <div class="row">
            <div class="col-md-3">
                <s:label> <s:property value="Column 7"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value7" value="%{value7}" class="form-control"/>
            </div>
            <div class="col-md-3">
                <s:label> <s:property value="Column 8"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value8" value="%{value8}" class="form-control"/>
            </div
        </div>
        <div class="row">
            <div class="col-md-3">
                <s:submit class="btn btn-info"/>
            </div>
        </div>
    </s:form>
</div>
</body>
</html>
