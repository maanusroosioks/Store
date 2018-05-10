<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<div id="content" class="container">
    <h3><s:property value="getText('global.enterNewValues')"/></h3>

    <s:form action="changeValues" theme="simple">
        <s:hidden name="productData.productID" value="%{productData.productID}"/>
        <s:hidden name="productData.productType" value="%{productData.productType}"/>
        <div class="row">
            <div class="col-md-2">
                <s:property value="getText('global.productID')"/>: <s:property value="productData.productID"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-5">
                <s:label> <s:property value="getText('global.productType')"/>: <s:property
                        value="%{productData.productType}"/> </s:label>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <s:property value="getText('global.productName')"/>
            </div>
            <div class="col-md-3">
                <s:textfield name="productData.productName" class="form-control"
                             value="%{productData.productName}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <s:property value="getText('global.productAmount')"/>
            </div>
            <div class="col-md-3">
                <s:textfield type="number" name="productData.productAmount" class="form-control"
                             value="%{productData.productAmount}"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <s:property value="getText('global.productPrice')"/>

            </div>
            <div class="col-md-3">
                <s:textfield type="number" class="form-control" name="productData.productPrice"
                             value="%{productData.productPrice}"/>
            </div>
        </div>
        <s:iterator value="productTableColumnNames" status="row">
            <div class="row">
                <div class="col-md-2">
                    <s:property/>
                </div>
                <div class="col-md-2">
                    <s:textfield name="productSpecValueList[%{#row.index}]"
                                 value="%{productSpecificationsData[#row.index]}" class="form-control"/>
                </div>
            </div>
        </s:iterator>
        <div class="row">
            <div class="col-md-12">
                <s:submit class="btn btn-info button" value="%{getText('global.changeValues')}"/>

                <s:url var="deleteProductUrl" action="deleteProductEntry">
                    <s:param value="%{productData.productID}" name="productData.productID"/>
                    <s:param value="%{productData.productType}" name="productData.productType"/>
                </s:url>
                <s:a class="btn btn-danger button" href="%{deleteProductUrl}"><s:property
                        value="getText('global.deleteEntry')"/></s:a>

                <s:url var="productTableUrl" action="fetchProductTable"/>
                <s:a class="btn btn-info button" href="%{productTableUrl}"><s:property value="getText('global.back')"/></s:a>
            </div>
        </div>
    </s:form>

</div>
