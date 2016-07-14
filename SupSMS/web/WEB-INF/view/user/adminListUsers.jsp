<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-lg-12">
    <div class="page-header">
        <h1>Utilisateurs SupSMS</h1>
    </div>
    <table class="table table-responsive table-striped">
        <thead>
            <tr>
                <th>numéro</th>
                <th>prénom</th>
                <th>nom</th>
                <th>mail</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${list_users}" >
                <tr>
                    <td>${user.phone}</td>
                    <td>${user.fname}</td>
                    <td>${user.lname}</td>
                    <td>${user.mail}</td>
                    <td>
                        <div class="btn-group pull-right" role="group">
                            <a href="${pageContext.request.contextPath}/private/admin/users/remove?id=${user.id}" class="btn btn-xs btn-default">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>