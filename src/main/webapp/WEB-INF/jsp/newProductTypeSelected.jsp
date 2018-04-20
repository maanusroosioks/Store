<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="insertNewProduct.jsp"></jsp:include>

<div class="container">
    <s:form action="insertNewProduct" theme="simple">
        <s:label> <s:property value="productData.productType"/> </s:label>
        <s:hidden name="productData.productType" value="%{productData.productType}"/>
        <s:hidden name="productData.productID" value="%{productData.productID}"/>
        <div class="row">
            <div class="col-md-2">
                <label>PRODUCT NAME: </label>
            </div>
            <div class="col-md-2">
                <s:textfield label="PRODUCT NAME" name="productData.productName" class="form-control"
                             value="%{productData.productName}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>PRODUCT AMOUNT: </label>
            </div>
            <div class="col-md-2">
                <s:textfield name="productData.productAmount" class="form-control"
                             value="%{productData.productAmount}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>PRODUCT PRICE: </label>
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
                    <s:textfield name="productSpecValueList[%{#row.index}]"  class="form-control"/>
                </div>
            </div>
        </s:iterator>
        <div class="row">
            <div class="col-md-3">
                <s:submit class="btn btn-info"/>
            </div>
        </div>
    </s:form>
</div>