
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" href="../../css/style.css" type="text/css">


<div id="wrapper" class="asd">
    <div id="page-content-wrapper">
        <div class="page-content">
            <div class="container">
                <table class="table">
                    <th><s:property value="getText('global.productID')"/></th>
                    <th><s:property value="getText('global.productType')"/></th>
                    <th><s:property value="getText('global.productName')"/></th>
                    <th><s:property value="getText('global.productPrice')"/></th>
                    <th><s:property value="getText('global.productAmount')"/></th>
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
                                <s:param value="productPrice" name="productData.productPrice"/>
                            </s:url>
                                <s:a class="btn btn-danger" href="%{addToShoppingCart}"><s:property value="getText('global.addToShoppingCart')"/></s:a></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
