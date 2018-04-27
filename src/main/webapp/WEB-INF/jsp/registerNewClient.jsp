<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>


<html>
<head>
    <title>Enter values for the new product</title>
</head>
<body>
<s:include value="header.jsp"/>
<div id="content" class="container">
    <h3>Enter New Values</h3>
    <s:form action="registerNewClient" theme="simple">
        <div class="row">
            <div class="form-group">
                <label for="firstNameId">FIRST NAME: </label>
                <s:textfield id="firstNameId" name="client.firstName" class="form-control"
                              value="%{client.firstName}"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="lastNameId">LAST NAME: </label>
                <s:textfield id="lastNameId" name="client.lastName" class="form-control"
                             value="%{client.lastName}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="emailId">EMAIL: </label>
                <s:textfield id="emailId" class="form-control" name="client.email"
                             value="%{client.email}"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="passwordId">PASSWORD: </label>
                <s:textfield id="passwordId" class="form-control" name="client.userPassword"
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
