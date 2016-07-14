<div class="col-lg-8">
    <div class="page-header">
        <h1>Connexion <small># connectez-vous avec votre compte SupSMS</small></h1>
    </div>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="inputTelephone" class="col-sm-3 control-label">numéro de téléphone</label>
            <div class="col-sm-9">
                <input type="text" name="login" class="form-control" id="inputTelephone" placeholder="numéro de téléphone">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-3 control-label">mot de passe</label>
            <div class="col-sm-9">
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="mot de passe">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-primary">Connexion</button>
                <a href="${pageContext.request.contextPath}/signin" class="btn btn-default"> Inscription</a>
            </div>
        </div>
    </form>
</div>