<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div id="content" class="container" >
    <s:form action="updateClientInfo" theme="simple">
        <div class="row">
            <div class="form-group">
                <label for="firstName"><s:property value="getText('global.firstName')"/>: </label>
                <s:textfield id="firstName" name="client.firstName" class="form-control"
                             value="%{client.firstName}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="lastName"><s:property value="getText('global.lastName')"/>: </label>
                <s:textfield id="lastName" name="client.lastName" class="form-control"
                             value="%{client.lastName}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="email"><s:property value="getText('global.email')"/>: </label>
                <s:textfield id="email" name="client.email" class="form-control"
                             value="%{client.email}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="password"><s:property value="getText('global.password')"/>: </label>
                <s:textfield id="password" name="client.userPassword" class="form-control"
                             value="%{client.userPassword}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <s:submit class="btn btn-info button" value="%{getText('global.changeValues')}"/>
                <s:url var="back" action="index"/>
                <s:a class="btn btn-info button" href="%{back}"><s:property value="getText('global.back')"/></s:a>
            </div>
        </div>
    </s:form>
</div>

