<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <title>Products table</title>
</head>
<body>
<s:include value="header.jsp"/>
<div id="content" class="container">
    <div class="table-responsive" >
        <table class="table">
            <thead>
                <th>PROUDCT ID</th>
                <th>PRODUCT TYPE</th>
                <th>PRODUCT NAME</th>
                <th>PRODUCT AMOUNT</th>
                <th>PRODUCT PRICE</th>
            </thead>
            <tbody>
            <s:iterator value="productsTableDataList">
                <tr>
                    <s:url var="findUserUrl" action="valuesPage">
                        <s:param value="productID" name="productID"/>
                    </s:url>
                    <td><s:a href="%{findUserUrl}"><s:property value="productID"/></s:a></td>
                    <td><s:property value="productType"/></td>
                    <td><s:property value="productName"/></td>
                    <td><s:property value="productAmount"/></td>
                    <td><s:property value="productPrice"/></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
    <div>
        <s:form action="newProductEntry" theme="simple">
            <s:submit class="btn btn-info" value="New product"/>
        </s:form>
    </div>
</div>
</body>
</html>