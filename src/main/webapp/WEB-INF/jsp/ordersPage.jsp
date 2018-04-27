<%--
  Created by IntelliJ IDEA.
  User: Maanus
  Date: 25-Apr-18
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<s:include value="header.jsp"/>

<div class="container">
    <s:iterator value="orderListByBillID" var="orderBillID">
        <h3><s:property value="#orderBillID[0].timeStamp"/></h3>
        <s:iterator value="orderBillID">
            <div class="row">
                <div class="col-md-5">
                       PRODUCT NAME:
                    <s:property value="productName"/>
                       PRODUCT PRICE:
                    <s:property value="productPrice"/>
                </div>
            </div>
        </s:iterator>
    </s:iterator>
</div>
</body>
</html>
