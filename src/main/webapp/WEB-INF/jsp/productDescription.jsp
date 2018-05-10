
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div class="container">
    <table class="table">

        <td><s:property value="getText('global.productType')"/>:</td>
        <td><s:property value="productData.productType"/></td>
        <s:iterator value="productTableColumnNames" status="row">
            <tr>
                <td><s:property/></td>
                <td><s:property value="%{productSpecificationsData[#row.index]}"/></td>
            </tr>
        </s:iterator>
    </table>
    <s:url var="addToShoppingCart" action="addToShoppingCart">
        <s:param value="productData.productName" name="productData.productName"/>
        <s:param value="productData.productID" name="productData.productID"/>
        <s:param value="productData.productType" name="productData.productType"/>
    </s:url>
    <s:url var="productTableUrl" action="productShoppingTable"/>
    <s:a class="btn btn-danger" href="%{addToShoppingCart}"><s:property value="getText('global.addToShoppingCart')"/></s:a>
    <s:a class="btn btn-info button" href="%{productTableUrl}"><s:property value="getText('global.back')"/></s:a>
</div>

