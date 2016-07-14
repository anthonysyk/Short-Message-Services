<div class="col-lg-7">
    <div class="page-header">
        <h1>Profil <small># modifier mon profil</small></h1>
    </div>
    <jsp:include page="/WEB-INF/inc/form/error.jsp">
        <jsp:param name="inc_error" value="${form_error_informations}" />
    </jsp:include>
    <form class="form-horizontal" role="form" method="post" action="?part=information">
        <div class="form-group">
            <label for="inputTelephone" class="col-sm-4 control-label">téléphone</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="inputTelephone" value="${user.phone}" disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="inputNom" class="col-sm-4 control-label">nom</label>
            <div class="col-sm-8">
                <input type="text" name="inputLname" class="form-control" id="inputNom" placeholder="nom de famille" value="${user.lname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPrenom" class="col-sm-4 control-label">prénom</label>
            <div class="col-sm-8">
                <input type="text" name="inputFname" class="form-control" id="inputPrenom" placeholder="prénom" value="${user.fname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputMail" class="col-sm-4 control-label">mail</label>
            <div class="col-sm-8">
                <input type="text" name="inputMail" class="form-control" id="inputMail" placeholder="adresse mail" value="${user.mail}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAdresse" class="col-sm-4 control-label">carte de crédit</label>
            <div class="col-sm-8">
                <input type="text" name="inputCcard" class="form-control" id="inputAdresse" placeholder="carte de crédit" value="${user.ccard}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-8">
                <button type="submit" class="btn btn-primary">Enregistrer</button>
            </div>
        </div>
    </form>


    <div class="page-header">
        <h1>Mot de passe <small># changer mon mot de passe</small></h1>
    </div>
    <jsp:include page="/WEB-INF/inc/form/error.jsp">
        <jsp:param name="inc_error" value="${form_error_password}" />
    </jsp:include>
    <form class="form-horizontal" role="form" method="post" action="?part=password">
        <div class="form-group">
            <label for="inputPassword1" class="col-sm-4 control-label">nouveau mot de passe</label>
            <div class="col-sm-8">
                <input type="password" name="inputPassword1" class="form-control" id="inputPassword1" placeholder="nouveau mot de passe">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword2" class="col-sm-4 control-label">nouveau mot de passe</label>
            <div class="col-sm-8">
                <input type="password" name="inputPassword2" class="form-control" id="inputPassword2" placeholder="nouveau mot de passe (confirmation)">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-8">
                <button type="submit" class="btn btn-primary">Modifier</button>
            </div>
        </div>
    </form>
</div>