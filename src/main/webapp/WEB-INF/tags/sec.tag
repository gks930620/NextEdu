<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="hasRole" required="true" type="String" %>

<!-- 권한 -->
<c:if test="${not empty sessionScope.USER_INFO }">
	<c:if test="${fn:contains(hasRole , sessionScope.USER_INFO.userRole)}">
		<jsp:doBody />
	</c:if>
</c:if>



