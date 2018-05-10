<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="insertNewProduct.jsp"/>

<div class="container">
    <s:form action="insertNewProduct" theme="simple">
        <s:hidden name="productData.productType" value="%{productData.productType}"/>
        <s:hidden name="productData.productID" value="%{productData.productID}"/>
        <div class="row">
            <div class="col-md-2">
                <s:property value="getText('global.productName')"/>:
            </div>
            <div class="col-md-2">
                <s:textfield label="PRODUCT NAME" name="productData.productName" class="form-control"
                             value="%{productData.productName}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <s:property value="getText('global.productAmount')"/>:
            </div>
            <div class="col-md-2">
                <s:textfield name="productData.productAmount" class="form-control"
                             value="%{productData.productAmount}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <s:property value="getText('global.productPrice')"/>:
            </div>
            <div class="col-md-2">
                <s:textfield class="form-control" name="productData.productPrice"
                             value="%{productData.productPrice}"/>
            </div>
        </div>
        <s:iterator value="productTableColumnNames" status="row">
            <div class="row">
                <div class="col-md-2">
                    <s:property/>
                </div>
                <div class="col-md-2">
                    <s:textfield name="productSpecValueList[%{#row.index}]" class="form-control"/>
                </div>
            </div>
        </s:iterator>
        <div class="row">
            <div class="col-md-3">
                <s:submit class="btn btn-info" value="%{getText('global.submit')}"/>
            </div>
        </div>
    </s:form>
</div>