<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div id="content" class="container">
    <h3>Enter New Values</h3>
    <s:form action="registerNewClient" theme="simple">
        <div class="row">
            <div class="form-group">
                <label for="firstNameId"><s:property value="getText('global.firstName')"/>: </label>
                <s:textfield id="firstNameId" name="client.firstName" class="form-control"
                             value="%{client.firstName}"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="lastNameId"><s:property value="getText('global.lastName')"/>: </label>
                <s:textfield id="lastNameId" name="client.lastName" class="form-control"
                             value="%{client.lastName}"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="emailId"><s:property value="getText('global.email')"/>: </label>
                <s:textfield id="emailId" class="form-control" name="client.email"
                             value="%{client.email}"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="passwordId"><s:property value="getText('global.password')"/>: </label>
                <s:textfield id="passwordId" class="form-control" name="client.userPassword"
                             value="%{client.userpassword}"/>
            </div>
        </div>
        <div class="row">
            <s:submit class="btn btn-info" value="%{getText('global.submit')}"/>
        </div>
    </s:form>
</div>
