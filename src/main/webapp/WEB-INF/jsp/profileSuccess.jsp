<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<s:include value="header.jsp"/>
<div id="content" class="container" >
    <s:form action="updateClientInfo" theme="simple">
        <div class="row">
            <div class="form-group">
                <label for="firstName">FIRST NAME: </label>
                <s:textfield id="firstName" name="client.firstName" class="form-control"
                             value="%{client.firstName}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="lastName">LAST NAME: </label>
                <s:textfield id="lastName" name="client.lastName" class="form-control"
                             value="%{client.lastName}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="email">EMAIL: </label>
                <s:textfield id="email" name="client.email" class="form-control"
                             value="%{client.email}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="password">PASSWORD: </label>
                <s:textfield id="password" name="client.userPassword" class="form-control"
                             value="%{client.userPassword}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <s:submit class="btn btn-info button" value="Change values"/>
                <s:url var="back" action="index"/>
                <s:a class="btn btn-info button" href="%{back}">Back</s:a>
            </div>
        </div>
    </s:form>
</div>
</body>
</html>
