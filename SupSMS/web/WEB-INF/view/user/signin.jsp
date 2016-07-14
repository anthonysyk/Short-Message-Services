<%@page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-lg-8">
    <div class="page-header">
        <h1>Insription <small># création d'un compte SupSMS</small></h1>
    </div>
    <jsp:include page="/WEB-INF/inc/form/error.jsp">
        <jsp:param name="inc_error" value="${form_error}" />
    </jsp:include>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="inputTelephone" class="col-sm-3 control-label">numéro de téléphone</label>
            <div class="col-sm-9">
                <input type="text" name="inputPhone" class="form-control" id="inputTelephone" placeholder="numéro de téléphone" value="${form_user.phone}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword1" class="col-sm-3 control-label">mot de passe</label>
            <div class="col-sm-9">
                <input type="password" name="inputPassword1" class="form-control" id="inputPassword1" placeholder="mot de passe">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword2" class="col-sm-3 control-label">mot de passe</label>
            <div class="col-sm-9">
                <input type="password" name="inputPassword2" class="form-control" id="inputPassword2" placeholder="mot de passe (confirmation)">
            </div>
        </div>
        <hr/>
        <div class="form-group">
            <label for="inputMail" class="col-sm-3 control-label">adresse mail</label>
            <div class="col-sm-9">
                <input type="email" name="inputMail" class="form-control" id="inputMail" placeholder="adresse mail" value="${form_user.mail}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputNom" class="col-sm-3 control-label">nom</label>
            <div class="col-sm-9">
                <input type="text" name="inputLname" class="form-control" id="inputNom" placeholder="nom de famille" value="${form_user.lname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPrenom" class="col-sm-3 control-label">prénom</label>
            <div class="col-sm-9">
                <input type="text" name="inputFname" class="form-control" id="inputPrenom" placeholder="prénom" value="${form_user.fname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCredit" class="col-sm-3 control-label">carte de crédit</label>
            <div class="col-sm-9">
                <input type="text" name="inputCcard" class="form-control" id="inputCredit" placeholder="numéro de carte de crédit" value="${form_user.ccard}">
            </div>
        </div>
        <hr/>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-primary">Inscription</button>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-default"> Connexion</a>
            </div>
        </div>
    </form>
</div>