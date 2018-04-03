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
    <s:form action="registerNewClient" theme="simple">
        <div class="row">
            <div class="col-md-2">
                <label>FIRST NAME: </label>

            </div>
            <div class="col-md-3">
                <s:textfield  name="firstName" class="form-control"
                              value="%{client.firstName}"/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-2">
                <label>LAST NAME: </label>

            </div>
            <div class="col-md-3">
                <s:textfield label="LAST NAME" name="lastName" class="form-control"
                             value="%{client.lastName}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>EMAIL ADDRESS: </label>

            </div>
            <div class="col-md-3">
                <s:textfield label="EMAIL ADDRESS" class="form-control" name="email"
                             value="%{client.email}"/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-2">
                <label>PASSWORD: </label>

            </div>
            <div class="col-md-3">
                <s:textfield label="PASSWORD" class="form-control" name="userpassword"
                             value="%{client.userpassword}"/>
            </div>
        </div>
        <div class="row">
            <s:submit class="btn btn-info"/>
        </div>
    </s:form>
</div>
</body>
</html>
