<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${fn:length(param.inc_error) gt 0}">
    <div class="alert alert-danger">
        <ul>
            <c:forEach var="error_msg" items="${param.inc_error}" >
                <li>${error_msg}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>