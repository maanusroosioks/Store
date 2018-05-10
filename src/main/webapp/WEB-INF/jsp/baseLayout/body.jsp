<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>



<h1><s:text name="global.welcomeMessage"/></h1>
<h1><s:textfield key="global.welcomeMessage" name="username"/></h1>
<h1><s:property value="getText('global.welcomeMessage')"/></h1>
<h1><s:text name="global.welcomeMessage"/></h1>



