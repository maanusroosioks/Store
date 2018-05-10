<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<s:url action="indexPage"/>"><s:property value="getText('global.mainPage')"/>
                    <span
                            class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="fetchProductTable"/>"><s:property
                        value="getText('global.productsPage')"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="productShoppingTable"/>">
                    <s:property value="getText('global.shoppingTable')"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="productTypeCreationPage"/>">
                    <s:property value="getText('global.createNewProductType')"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="shoppingCartTable"/>">
                    <s:property value="getText('global.shoppingCart')"/>
                </a>
            </li>
            <s:if test="%{#session.email ==null}">
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="clientRegistrationPage"/>">
                        <s:property value="getText('global.registerUser')"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="clientLoginPage"/>">
                        <s:property value="getText('global.login')"/>
                    </a>
                </li>
            </s:if>
            <s:if test="%{#session.email !=null}">
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="profilePage"/>">
                        <s:property value="getText('global.profilePage')"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="logOut"/>">
                        <s:property value="getText('global.logOut')"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="ordersPage"/>">
                        <s:property value="getText('global.ordersPage')"/>
                    </a>
                </li>
            </s:if>
            <li class="nav-item">
                <s:url var="languageEST" namespace="/" action="language" includeParams="all" value="">
                    <s:param name="request_locale">ee</s:param>
                </s:url>
                <s:a href="%{languageEST}">EST</s:a>
            </li>
            <li class="nav-item">
                <s:url var="languageENG" namespace="/" action="language" includeParams="all" value="">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{languageENG}">ENG</s:a>

            </li>


        </ul>
    </div>
</nav>