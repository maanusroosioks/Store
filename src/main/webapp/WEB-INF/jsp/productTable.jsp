<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<div id="wrapper">
    <div id="page-content-wrapper">
        <div class="page-content">
            <div id="content" class="container">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <th><s:property value="getText('global.productID')"/></th>
                        <th><s:property value="getText('global.productType')"/></th>
                        <th><s:property value="getText('global.productName')"/></th>
                        <th><s:property value="getText('global.productAmount')"/></th>
                        <th><s:property value="getText('global.productPrice')"/></th>
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
                        <s:submit class="btn btn-info" value="%{getText('global.newProduct')}"/>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
</div>
