<div class="col-lg-7">
    <div class="page-header">
        <h1><c:out value="${contact.fname} ${contact.lname}"/> <small># modifier le contact</small></h1>
    </div>

    <jsp:include page="/WEB-INF/inc/form/error.jsp">
        <jsp:param name="inc_error" value="${form_error}" />
    </jsp:include>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="inputTelephone" class="col-sm-3 control-label">téléphone</label>
            <div class="col-sm-9">
                <input type="text" name="inputPhone" class="form-control" id="inputTelephone" placeholder="numero de téléphone" value="${contact.phone}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputNom" class="col-sm-3 control-label">nom</label>
            <div class="col-sm-9">
                <input type="text" name="inputLname" class="form-control" id="inputNom" placeholder="nom de famille" value="${contact.lname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPrenom" class="col-sm-3 control-label">prénom</label>
            <div class="col-sm-9">
                <input type="text" name="inputFname" class="form-control" id="inputPrenom" placeholder="prénom" value="${contact.fname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputMail" class="col-sm-3 control-label">mail</label>
            <div class="col-sm-9">
                <input type="text" name="inputMail" class="form-control" id="inputMail" placeholder="adresse mail" value="${contact.mail}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAdresse" class="col-sm-3 control-label">adresse</label>
            <div class="col-sm-9">
                <input type="text" name="inputAddress" class="form-control" id="inputAdresse" placeholder="adresse postale" value="${contact.address}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <a href="${pageContext.request.contextPath}/private/contacts" class="btn btn-default">Annuler</a>
            </div>
        </div>
    </form>
</div>