<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script>
    var index = 1;

    function add() {
        var element = document.createElement("input");
        var label = document.createElement("Label");
        var breakLine = document.createElement("br");
        label.innerHTML = "Column name";
        element.setAttribute("type", "text");
        element.setAttribute("value", "");
        element.setAttribute("name", "productTableColumnNames[" + index + "]");
        index++;

        label.setAttribute("style", "font-weight:normal");

        var column = document.getElementById("addColumnButton");

        column.appendChild(label);
        column.appendChild(breakLine);
        column.appendChild(element);
        column.appendChild(breakLine);
    }
</script>

<div class="container">
    <s:form action="createNewProductTable" theme="simple">
    <div class="form-group">
        <div class="col-md-2">
            <label><s:property value="getText('global.productType')"/> </label>
        </div>
        <div class="col-md-2">
            <s:textfield label="PRODUCT TYPE" name="productData.productType" class="form-control"
                         value="%{productData.productType}"/>
        </div>
    </div>
    <div id="addColumnButton">
    </div>
    <div class="row">
        <div class="col-md-1">
            <s:submit class="btn btn-info" value="%{getText('global.submit')}"/>
            </s:form>
        </div>
    </div>
        <button id="button" class="btn btn-info" value="Add" onclick="add();"><s:property
                value="getText('global.addColumn')"/></button>
</div>

