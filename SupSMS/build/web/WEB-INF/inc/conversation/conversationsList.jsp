<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table table-responsive table-striped">
    <thead>
        <tr>
            <th>contact</th>
            <th>message</th>
            <th>heure</th>
            <th>messages</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="conversation" items="${list_conversations}" >
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${not empty contacts_list[conversation.phone]}">
                            ${contacts_list[conversation.phone].fname} 
                            ${contacts_list[conversation.phone].lname}
                        </c:when>
                        <c:otherwise>
                            ${conversation.phone}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/private/conversations/details?id=${conversation.id}">
                        ${conversation.messages.get(0).message}
                    <a>
                </td>
                <td><fmt:formatDate type="time" value="${conversation.time}" pattern="dd/MM/yy HH:mm:ss" /></td>
                <td>
                    ${conversation.messages.size()}
                </td>
                <td>
                    <div class="btn-group  pull-right" role="group">
                        <a href="${pageContext.request.contextPath}/private/conversations/remove?id=${conversation.id}" class="btn btn-xs btn-default">
                            <span class="glyphicon glyphicon-remove"></span>
                        </a>
                        <a href="${pageContext.request.contextPath}/private/conversations/details?id=${conversation.id}" class="btn btn-xs btn-default">
                            <span class="glyphicon glyphicon-eye-open"></span>
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>