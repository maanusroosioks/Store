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
<s:include value="header.jsp"/>
<div id="content" class="container">
    <h3>Enter New Values</h3>
    <s:form action="displayNewProductSpecifications" id="productTypeSelection">
        <s:select label="Select product type"
                  name="productData.productType"
                  headerValue="Select product type"
                  list="%{productTypeList}"
                  onchange="this.form.submit()"
                  emptyOption="true"
        />
    </s:form>
</div>
</body>
</html>
