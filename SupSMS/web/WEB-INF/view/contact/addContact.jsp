<div class="col-lg-7">
    <div class="page-header">
        <a href="${pageContext.request.contextPath}/private/contacts" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-chevron-left"></span>&nbsp;
            Retour aux contacts
        </a>

        <h1>Nouveau contact</h1>
    </div>

    <jsp:include page="/WEB-INF/inc/form/error.jsp">
        <jsp:param name="inc_error" value="${form_error}" />
    </jsp:include>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="inputTelephone" class="col-sm-3 control-label">t�l�phone</label>
            <div class="col-sm-9">
                <input type="text" name="inputPhone" class="form-control" id="inputTelephone" placeholder="numero de t�l�phone" value="${form_contact.phone}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputNom" class="col-sm-3 control-label">nom</label>
            <div class="col-sm-9">
                <input type="text" name="inputLname" class="form-control" id="inputNom" placeholder="nom de famille" value="${form_contact.lname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPrenom" class="col-sm-3 control-label">pr�nom</label>
            <div class="col-sm-9">
                <input type="text" name="inputFname" class="form-control" id="inputPrenom" placeholder="pr�nom" value="${form_contact.fname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputMail" class="col-sm-3 control-label">mail</label>
            <div class="col-sm-9">
                <input type="text" name="inputMail" class="form-control" id="inputMail" placeholder="adresse mail" value="${form_contact.mail}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAdresse" class="col-sm-3 control-label">adresse</label>
            <div class="col-sm-9">
                <input type="text" name="inputAddress" class="form-control" id="inputAdresse" placeholder="adresse postale" value="${form_contact.address}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </div>
        </div>
    </form>
</div>