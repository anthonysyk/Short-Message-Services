<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-lg-7">
    <div class="page-header">
        <h1>Nouveau message</h1>
    </div>
    <jsp:include page="/WEB-INF/inc/form/error.jsp">
        <jsp:param name="inc_error" value="${form_error}" />
    </jsp:include>
    <form class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label for="inputDestinataire" class="col-sm-2 control-label">Destinataire</label>
            <div class="col-sm-6">
                <input type="text" name="inputPhone" class="form-control" id="inputDestinataire" value="${contact_phone}" placeholder="numero de téléphone du destinataire">
            </div>
            <div class="col-sm-4">
                <a href="${pageContext.request.contextPath}/private/contacts" class="btn btn-default btn-block">
                    <span class="glyphicon glyphicon-search"></span>&nbsp;
                    choisir un contact
                </a>
            </div>
        </div>
        <div class="form-group">
            <label for="inputMessage" class="col-sm-2 control-label">Message</label>
            <div class="col-sm-10">
                <textarea id="inputMessage" name="inputMessage" class="form-control" rows="5" placeholder="votre message...">${form_message.message}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="${pageContext.request.contextPath}/home" class="btn btn-default pull-left">voir toutes les conversations</a>
                <button type="submit" class="btn btn-primary pull-right">Envoyer</button>
            </div>
        </div>
    </form>
</div>