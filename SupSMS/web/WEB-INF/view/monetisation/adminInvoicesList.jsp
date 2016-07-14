<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-lg-8">
    <div class="page-header">
        <h1>Factures SupSMS</h1>
    </div>
    <ul class="list-unstyled">
        <c:forEach var="invoice" items="${list_invoices}">
            <li>
                <a href="${pageContext.request.contextPath}/private/invoices/details?id=${invoice.id}&admin=true" class="btn btn-link">
                    <fmt:formatDate type="time" value="${invoice.date}" pattern="dd/MM/yy HH:mm" /> 
                    - ${invoice.price}$ TTC - ${invoice.user.fname} ${invoice.user.lname}
                </a>
            </li>
        </c:forEach>
    </ul>
</div>