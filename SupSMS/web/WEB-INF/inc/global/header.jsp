<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head lang="fr">
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/supsms.css" />
    </head>
    <body>
        <nav class="navbar navbar-default navbar-static-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-top-navbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                        SupSMS
                        <c:if test="${sessionScope.user_prenium}">
                            <small>
                                <small><small><span class="glyphicon glyphicon-arrow-right"></span>&nbsp;</small></small>PRO
                            </small>
                        </c:if>
                    </a>
                </div>

                <div class="collapse navbar-collapse" id="main-top-navbar">
                    <ul class="nav navbar-nav">
                        <c:choose>
                            <c:when test="${!empty sessionScope.role_user}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-comment"></span>Conversations</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/private/messages/new"><span class="glyphicon glyphicon-envelope"></span>Nouveau message</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/private/contacts"><span class="glyphicon glyphicon-phone-alt"></span>Contacts</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span>Accueil</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${!empty sessionScope.role_admin}">
                            <li>
                                <a href="${pageContext.request.contextPath}/private/admin/users"><span class="glyphicon glyphicon-list"></span>Utilisateurs</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/private/admin/invoices"><span class="glyphicon glyphicon-usd"></span>Factures</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${pageContext.request.contextPath}/product"><span class="glyphicon glyphicon-tags"></span>Produits</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${!empty sessionScope.role_user}">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" >
                                        <span class="glyphicon glyphicon-user"></span>${sessionScope.user_fname} <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="${pageContext.request.contextPath}/private/profil/update">Modifier mon profil</a></li>
                                        <li><a href="${pageContext.request.contextPath}/private/invoices">Factures</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-lock"></span>déconnexion</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${pageContext.request.contextPath}/signin"><span class="glyphicon glyphicon-user"></span>inscription</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-lock"></span>connexion</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <c:if test="${!empty flash_success}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    ${flash_success}
                    <button type="button" class="close" data-dismiss="alert">
                        <span>&times;</span>
                      </button>
                </div>
            </c:if>
            <c:if test="${!empty flash_info}">
                <div class="alert alert-info alert-dismissible" role="alert">
                    ${flash_info}
                    <button type="button" class="close" data-dismiss="alert">
                        <span>&times;</span>
                      </button>
                </div>
            </c:if>
            <c:if test="${!empty flash_error}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    ${flash_error}
                    <button type="button" class="close" data-dismiss="alert">
                        <span>&times;</span>
                      </button>
                </div>
            </c:if>
            
            <c:remove var="flash_success" scope="session"/>
            <c:remove var="flash_info" scope="session"/>
            <c:remove var="flash_error" scope="session"/>
            
            <div class="row">