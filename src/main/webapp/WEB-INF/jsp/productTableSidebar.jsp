<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<style>
    #sidebar-wrapper {
        width: 200px;
        position: absolute;
        top: 0;
        height:100%;
        margin-top:50px;
    }
</style>

<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <s:form action="productShoppingTable" id="productTypeSelection">
            <s:select label="%{getText('global.selectProductType')}"
                      name="productData.productType"
                      headerValue="Select product type"
                      list="%{productTypeList}"
                      onchange="this.form.submit()"
                      emptyOption="true"

            />
            <div>
                <s:iterator value="productTypeSpecificationsData" status="row">
                    <div class="form-group">
                        <div class="checkbox">
                            <s:checkboxlist id="productShoppingTable" label="%{key}"
                                            list="%{productTypeSpecificationsData[key]}"
                                            name="checkedProductTypeSpecificationsData[%{#row.index}]"
                                            value="%{checkedProductTypeSpecificationsData[#row.index]}"
                                            onchange="this.form.submit()"/>
                        </div>
                    </div>
                </s:iterator>
            </div>
        </s:form>
    </ul>
</div>