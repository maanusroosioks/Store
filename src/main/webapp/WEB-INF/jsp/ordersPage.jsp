<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<div class="container">
    <s:iterator value="orderListByBillID" var="orderBillID">
        <h3><s:property value="#orderBillID[0].timeStamp"/></h3>
        <s:iterator value="orderBillID">
            <div class="row">
                <div class="col-md-5">
                    <s:property value="getText('global.productName')"/>:
                    <s:property value="productName"/>
                    <s:property value="getText('global.productPrice')"/>:
                    <s:property value="productPrice"/>
                </div>
            </div>
        </s:iterator>
    </s:iterator>
</div>
