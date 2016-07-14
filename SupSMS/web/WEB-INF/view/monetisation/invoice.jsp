<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-lg-7">
    <div class="page-header">
        <a href="${pageContext.request.contextPath}/private<c:if test="${not empty param.admin}">/admin</c:if>/invoices" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-chevron-left"></span>
            Retour aux factures
        </a>
        <h1>Facture <small># du <fmt:formatDate type="time" value="${invoice.date}" pattern="dd/MM/yy" /> de ${invoice.price}$</small></h1>
    </div>

    <table class="table table-bordered">
        <tr>
            <td colspan="2">
                <h2>SUPSMS</h2>
            </td>
            <td colspan="2" class="text-align-right">
                <h3>FACTURE</h3>
            </td>
        </tr>
        <tr>
            <td colspan="2"><b>Client:</b></td>
            <td class="text-align-right"><b>Date:</b></td>
            <td><fmt:formatDate type="time" value="${invoice.date}" pattern="dd/MM/yy" /></td>
        </tr>
        <tr>
            <td colspan="2">${invoice.user.fname} ${invoice.user.lname} - ${invoice.user.phone}</td>
            <td class="text-align-right"><b>Numéro:</b></td>
            <td>${invoice.id}</td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <th>Description</th>
            <th>Quantité</th>
            <th>Prix unitaire HT</th>
            <th>Total HT</th>
        </tr>
        <tr>
            <td>Abonnement 1 mois SupSMS</td>
            <td>1</td>
            <td>${invoice.price * (1 - (invoice.taxe / 100))}$</td>
            <td>${invoice.price * (1 - (invoice.taxe / 100))}$</td>
        </tr>
        <tr>
            <td colspan="2" rowspan="4"></td>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <th>Total HT</th>
            <td>${invoice.price * (1 - (invoice.taxe / 100))}$</td>
        </tr>
        <tr>
            <th>Taxe</th>
            <td>TVA ${invoice.taxe}%</td>
        </tr>
        <tr>
            <th>Total TTC</th>
            <td>${invoice.price}€</td>
        </tr>
        <tr>
            <td colspan="4"><small>SIRET: 807 436 936 00015</small></td>
        </tr>
    </table>
</div>