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
        <div class="row">
            <div class="col-md-2">
                <s:label> <s:property value="productTableColumnNames[2]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value1" value="%{value1}" class="form-control"/>
            </div>
            <div class="col-md-2">
                <s:label> <s:property value="productTableColumnNames[3]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value2" value="%{value2}" class="form-control"/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-2">
                <s:label> <s:property value="productTableColumnNames[4]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value3" value="%{value3}" class="form-control"/>
            </div>
            <div class="col-md-2">
                <s:label> <s:property value="productTableColumnNames[5]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value4" value="%{value4}" class="form-control"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <s:label> <s:property value="productTableColumnNames[6]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value5" value="%{value5}" class="form-control"/>
            </div>
            <div class="col-md-2">
                <s:label> <s:property value="productTableColumnNames[7]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value6" value="%{value6}" class="form-control"/>
            </div
        </div>
        <div class="row">
            <div class="col-md-3">
                <s:label> <s:property value="productTableColumnNames[8]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value7" value="%{value7}" class="form-control"/>
            </div>
            <div class="col-md-3">
                <s:label> <s:property value="productTableColumnNames[9]"/> </s:label>
            </div>
            <div class="col-md-2">
                <s:textfield name="value8" value="%{value8}" class="form-control"/>
            </div
        </div>
        <div class="row">
            <div class="col-md-3">
                <s:submit class="btn btn-info"/>
            </div>
        </div>
    </s:form>
</div>