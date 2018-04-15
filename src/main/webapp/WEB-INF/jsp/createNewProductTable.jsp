<%--
  Created by IntelliJ IDEA.
  User: Maanus
  Date: 05-Apr-18
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Create new product type</title>
    <link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script>
        var index = 1;

        function add() {
            var element = document.createElement("input");
            var label = document.createElement("Label");
            var breakLine=document.createElement("br")
            label.innerHTML = "Column name";
            element.setAttribute("type", "text");
            element.setAttribute("value", "");
            element.setAttribute("name", "productTableColumnNames[" + index + "]");
            index++;

            label.setAttribute("style", "font-weight:normal");

            var column = document.getElementById("asd");

            column.appendChild(label);
            column.appendChild(element);
            column.appendChild(breakLine)
        }
    </script>
</head>
<body>
<s:include value="header.jsp"/>
<div class="container">
    <s:form action="createNewProductTable" theme="simple">
        <div class="row">
            <div class="col-md-2">
                <label>Product type: </label>
            </div>
            <div class="col-md-2">
                <s:textfield label="PRODUCT TYPE" name="productData.productType" class="form-control"
                             value="%{productData.productType}"/>
            </div>
        </div>
        <div  id="asd">
            <br>
        </div>
        <%--<s:iterator begin="0" end="8" status="row">--%>
        <%--<div class="row">--%>
        <%--<div class="col-md-2">--%>
        <%--Column <s:property value="%{#row.index}"/>--%>
        <%--</div>--%>
        <%--<div class="col-md-2">--%>
        <%--<s:textfield name="productTableColumnNames[%{#row.index}]"--%>
        <%--class="form-control"/>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</s:iterator>--%>
        <div class="row">
            <div class="col-md-3">
                <s:submit class="btn btn-info"/>
            </div>
        </div>
    </s:form>
    <button id="button" value="Add" onclick="add();">ADD COLUMN</button>
</div>
</body>
</html>
