<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<div id="content" class="container">
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th><s:property value="getText('global.clientName')"/></th>
                <th><s:property value="getText('global.productID')"/></th>
                <th><s:property value="getText('global.productName')"/></th>
                <th><s:property value="getText('global.productPrice')"/></th>
                <th><s:property value="getText('global.productPrice')"/></th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="shoppingCartDataList">
                <tr>
                    <s:url var="findUserUrl" action="productOverview">
                        <s:param value="productID" name="productData.productID"/>
                        <s:param value="productName" name="productData.productName"/>
                        <s:param value="productType" name="productData.productType"/>
                    </s:url>
                    <td><s:property value="clientName"/></td>
                    <td><s:a href="%{findUserUrl}"><s:property value="productID"/></s:a></td>
                    <td><s:property value="productName"/></td>
                    <td><s:property value="productPrice"/></td>
                    <td><s:property value="productAmount"/></td>
                    <td><s:url var="removeFromShoppingCart" action="deleteFromShoppingCart">
                        <s:param value="shoppingCartID" name="cartItem.shoppingCartID"/>
                    </s:url>
                        <s:a class="btn btn-danger"
                             href="%{removeFromShoppingCart}"><s:property value="getText('global.removeFromShoppingCart')"/></s:a></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <s:url var="purchaseProducts" action="generateOrder">
        </s:url>
        <s:a class="btn btn-danger" href="%{purchaseProducts}"><s:property value="getText('global.purchaseProducts')"/></s:a>
        <s:url var="emptyShoppingCart" action="emptyShoppingCart">
        </s:url>
        <s:a class="btn btn-danger" href="%{emptyShoppingCart}"><s:property value="getText('global.removeAllItems')"/></s:a>
    </div>
</div>
