<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="insertNewProduct.jsp"></jsp:include>


<div class="row">
    <div class="col-md-2">
        <label>PRODUCT AMOUNT: </label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  name="productAmount" class="form-control"
                     value="%{productData.productAmount}"/>
    </div>
    <div class="col-md-2">
        <label>PRODUCT AMOUNT: </label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  name="productAmount" class="form-control"
                     value="%{productData.productAmount}"/>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <label>PRODUCT PRICE: </label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  class="form-control" name="productPrice"
                     value="%{productData.productPrice}"/>
    </div>
    <div class="col-md-2">
        <label>PRODUCT PRICE: </label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  class="form-control" name="productPrice"
                     value="%{productData.productPrice}"/>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <label>PRODUCT PRICE: </label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  class="form-control" name="productPrice"
                     value="%{productData.productPrice}"/>
    </div>
    <div class="col-md-2">
        <label>PRODUCT PRICE: </label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  class="form-control" name="productPrice"
                     value="%{productData.productPrice}"/>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <label>productData.productPrice</label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  class="form-control" name="productPrice"
                     value="%{productData.productPrice}"/>
    </div>
    <div class="col-md-2">
        <label>PRODUCT PRICE: </label>

    </div>
    <div class="col-md-3">
        <s:textfield type="number"  class="form-control" name="productPrice"
                     value="%{productData.productPrice}"/>
    </div>
</div>
<div class="row">
    <s:submit class="btn btn-info"/>
</div>