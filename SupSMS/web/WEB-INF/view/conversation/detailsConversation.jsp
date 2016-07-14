<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-lg-12">
    <div class="page-header">
        <h1>
            ${contact.fname} ${contact.lname}
            <small>&nbsp;<span class="glyphicon glyphicon-comment"></span>&nbsp; ${conversation.phone}</small>
        </h1>
    </div>
</div>
<div class="col-lg-8">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/private/messages/new">
        <input type="hidden" name="inputPhone" value="${conversation.phone}" />
        <div class="form-group">
            <div class="col-sm-12">
                <input type="text" name="inputMessage" id="inputMessage" class="form-control pull-left" style="width: 89%" placeholder="votre message..." required/>
                <button type="submit" class="btn btn-primary pull-right" style="width: 10%">
                    <span class="glyphicon glyphicon-send"></span>
                </button>
            </div>
        </div>
        <br/>
    </form>

    <div id="conversation-message-list">
        <c:forEach var="message" items="${conversation.messages}" >
            <div class="
                 <c:choose>
                     <c:when test="${message.incoming}">text-align-right pull-right rmsg</c:when>
                     <c:otherwise>pull-left lmsg</c:otherwise>
                 </c:choose> msg">
                <div class="well well-sm 
                     <c:choose>
                        <c:when test="${message.incoming}">pull-right</c:when>
                        <c:otherwise>pull-left</c:otherwise>
                     </c:choose>">
                    <span class="
                          <c:choose>
                             <c:when test="${message.incoming}">pull-left</c:when>
                             <c:otherwise>pull-right</c:otherwise>
                          </c:choose> text-muted">
                        &nbsp;&nbsp;&nbsp;
                        <fmt:formatDate type="time" value="${message.time}" pattern="dd/MM/yy HH:mm:ss" />
                        &nbsp;&nbsp;&nbsp;
                    </span>
                    ${message.message}
                </div>
                <hr/>
            </div>
        </c:forEach>
    </div>
</div>
<div class="col-lg-4">
    <a href="${pageContext.request.contextPath}/home" class="btn btn-default">
        <span class="glyphicon glyphicon-chevron-left"></span>
        &nbsp;Conversations
    </a>
    <a href="${pageContext.request.contextPath}/private/conversations/remove?id=${conversation.id}" class="btn btn-default">
        <span class="glyphicon glyphicon-remove"></span>
        &nbsp;Supprimer
    </a>
</div>