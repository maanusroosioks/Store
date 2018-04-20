<%--
  Created by IntelliJ IDEA.
  User: Maanus
  Date: 1.04.2018
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title>Login page</title>
</head>
<body id="page-home">
<s:include value="header.jsp"/>
<div id="content" class="container">
    <h3>Enter email and password</h3>
    <s:form action="clientLogIn" theme="simple">
        <div class="row">
            <div class="form-group">
                <label for="emailId">EMAIL: </label>
                <s:textfield id="emailId" name="client.email" class="form-control"
                             value="%{client.email}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="passwordId">PASSWORD: </label>
                <s:textfield id="passwordId" name="client.userpassword" class="form-control"
                             value="%{client.userpassword}"/>
            </div>
        </div>
        <s:submit value="login" class="btn btn-info"/>
    </s:form>
</div>
</body>
</html>
