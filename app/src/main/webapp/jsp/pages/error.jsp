<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
	<div class="error-container">
		<div class="error-message">
			<h1>${errorTitle}</h1>
			<p>${errorMessage}</p>
		</div>
		<img src="${pageContext.request.contextPath}/assets/img/error.png" class="error-image" alt="une erreur" />
	</div>
</section>