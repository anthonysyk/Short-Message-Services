<div class="col-lg-12">
    <div class="page-header">
        <a href="${pageContext.request.contextPath}/private/messages/new" class="btn btn-success pull-right">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;
            Nouveau message
        </a>
        <h1>Conversations</h1>
    </div>
    <jsp:include page="/WEB-INF/inc/conversation/conversationsList.jsp" />
</div>