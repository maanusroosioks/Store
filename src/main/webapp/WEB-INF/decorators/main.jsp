<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>--%>
<%--<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>--%>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
</head>
<body id="page-home">
<div id="page">
    <div id="header" class="clearfix">
        HEADER
    </div>

    <div id="content" class="clearfix">
        <div id="main">
            <h3>Main Content</h3>
            <decorator:body/>
        </div>
    </div>
    <div id="links" class="clearfix">
        <div id="linkhead">
            <h3>Links</h3>
        </div>
        <a href="">Products table</a>
    </div>


</div>

</body>
</html>
