<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="webjars/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<s:url action="index"/>">Main page <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="fetchProductTable"/>">Products table</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="productShoppingTable"/>">
                    Shopping table
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="productTypeCreationPage"/>">
                    Create new product type
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url action="shoppingCartTable"/>">
                    Shopping cart
                </a>
            </li>
            <s:if test="%{#session.email ==null}">
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="clientRegistrationPage"/>">
                        Register user
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="loginPage"/>">
                        Log in
                    </a>
                </li>
            </s:if>
            <s:if test="%{#session.email !=null}">
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="profilePage"/>">
                        Profile
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="logOut"/>">
                        Log out
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<s:url action="ordersPage"/>">
                        Orders
                    </a>
                </li>
            </s:if>
        </ul>
    </div>
</nav>