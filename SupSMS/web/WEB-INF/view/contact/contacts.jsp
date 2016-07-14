<div class="col-lg-12">
    <div class="page-header">
        <a href="${pageContext.request.contextPath}/private/contacts/new" class="btn btn-success pull-right">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;
            Nouveau contact
        </a>
        <h1>Contacts</h1>
    </div>
    <table class="table table-responsive table-striped">
        <thead>
            <tr>
                <th>numéro</th>
                <th>prénom</th>
                <th>nom</th>
                <th>mail</th>
                <th>adresse</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="contact" items="${list_contacts}">
                <tr>
                    <td>${contact.phone}</td>
                    <td>${contact.fname}</td>
                    <td>${contact.lname}</td>
                    <td>${contact.mail}</td>
                    <td>${contact.address}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/private/messages/new?contact=${contact.id}" class="btn btn-xs btn-default">
                            &nbsp;&nbsp;<span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;
                        </a>
                        <div class="btn-group pull-right" role="group">
                            <a href="${pageContext.request.contextPath}/private/contacts/edit?id=${contact.id}" class="btn btn-xs btn-default">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>
                            <a href="${pageContext.request.contextPath}/private/contacts/remove?id=${contact.id}" class="btn btn-xs btn-default">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>