<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<style>
    #wrapper {
        padding-left: 250px;
        transition: all 0.4s ease 0s;
    }

    #sidebar-wrapper {
        margin-left: -250px;
        left: 250px;
        width: 250px;
        background: #CCC;
        position: fixed;
        height: 100%;
        overflow-y: auto;
        z-index: 1000;
        transition: all 0.4s ease 0s;
    }

    #page-content-wrapper {
        width: 100%;
    }

    .sidebar-nav {
        position: absolute;
        top: 0;
        width: 250px;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    @media (max-width: 767px) {

        #wrapper {
            padding-left: 0;
        }

        #sidebar-wrapper {
            left: 0;
        }

        #wrapper.active {
            position: relative;
            left: 250px;
        }

        #wrapper.active #sidebar-wrapper {
            left: 250px;
            width: 250px;
            transition: all 0.4s ease 0s;
        }

    }
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Products table</title>
</head>
<body>
<s:include value="header.jsp"/>
<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <s:form action="productShoppingTable" id="productTypeSelection">
                <s:select label="Select product type"
                          name="productData.productType"
                          headerValue="Select product type"
                          list="%{productTypeList}"
                          onchange="this.form.submit()"
                          emptyOption="true"
                />
            <div>
                    <s:iterator value="productTypeSpecificationsData" var="productTypeSpecificationData">
                        <div class="form-group">
                            <div class="checkbox">
                                <s:checkboxlist label="%{key}"
                                                list="value"
                                                name="productTypeSpecificationsData"/>
                            </div>
                        </div>
                    </s:iterator>
                <s:submit class="btn btn-info"/>
            </s:form>
            </div>
        </ul>
    </div>
    <div id="page-content-wrapper">
        <div class="page-content">
            <div class="container">
                <table class="table">
                            <th>Product ID</th>
                            <th>Product type</th>
                            <th>Product name</th>
                            <th>Product price</th>
                            <th>Product amount</th>
                    <tbody>
                    <s:iterator value="productsTableDataList">
                        <tr>
                            <s:url var="findUserUrl" action="productOverview">
                                <s:param value="productID" name="productData.productID"/>
                            </s:url>
                            <td><s:a href="%{findUserUrl}"><s:property value="productID"/></s:a></td>
                            <td><s:property value="productType"/></td>
                            <td><s:property value="productName"/></td>
                            <td><s:property value="productPrice"/></td>
                            <td><s:property value="productAmount"/></td>
                            <td><s:url var="addToShoppingCart" action="addToShoppingCart">
                                <s:param value="productName" name="productData.productName"/>
                                <s:param value="productID" name="productData.productID"/>
                                <s:param value="productType" name="productData.productType"/>
                            </s:url>
                                <s:a class="btn btn-danger" href="%{addToShoppingCart}">Add to shopping cart</s:a></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>