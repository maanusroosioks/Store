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
<div id="content" class="container">
    <div id="header" class="page-header">
        <h5>Links</h5>
        <a href="<s:url action="index"/>">Main page</a>
        <a href="<s:url action="fetchProductTable"/>">Products table</a>
        <a href="<s:url action="productShoppingTable"/>">Shopping table</a>
        <a href="<s:url action="shoppingCartTable"/>">Shopping cart</a>
    </div>

    <div class="clearfix">
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

    </div>
</div>
</body>
</html>
	