<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title>Index</title>
    <s:head/>
</head>
<body id="page-home">
<div id="page">
    <div id="header" class="clearfix">
        HEADER
    </div>

    <div id="content" class="clearfix">
        <div id="main">
            <h3>Main Content</h3>
            <s:form action="helloWorld">
                <s:textfield label="What is your name?" name="name"/>
                <s:submit/>
            </s:form>
        </div>
    </div>
    <div id="links" class="clearfix">
        <div id="linkhead">
            <h3>Links</h3>
        </div>
        <a href="<s:url action="fetchproducttable"/>">Products table</a>
    </div>
</div>
</body>
</html>
	