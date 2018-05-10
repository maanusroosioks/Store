<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div id="content" class="container">
    <h3><s:property value="getText('global.enterValues')"/></h3>

    <s:form action="displayNewProductSpecifications" id="productTypeSelection">
        <s:select label="%{getText('global.selectProductType')}"
                  name="productData.productType"
                  headerValue="Select product type"
                  list="%{productTypeList}"
                  onchange="this.form.submit()"
                  emptyOption="true"
        />
    </s:form>
</div>
