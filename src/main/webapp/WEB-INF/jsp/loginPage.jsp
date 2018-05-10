<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="content" class="container">
    <h3><s:property value="getText('global.enterEmailAndPassword')"/></h3>
    <s:form action="clientLogIn" theme="simple">
        <div class="row">
            <div class="form-group">
                <label for="emailId"><s:property value="getText('global.email')"/>: </label>
                <s:textfield id="emailId" name="client.email" class="form-control"
                             value="%{client.email}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="passwordId"><s:property value="getText('global.password')"/>: </label>
                <s:textfield id="passwordId" name="client.userPassword" class="form-control"
                             value="%{client.userPassword}"/>
            </div>
        </div>
        <s:submit value="%{getText('global.login')}" class="btn btn-info"/>
    </s:form>
</div>

