<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="pagingVO" type="com.next.common.vo.PagingVO"
	required="true"%>
<%@ attribute name="linkPage" type="java.lang.String" required="true"%>

<!-- 2. 절대경우로 왔을 경우 아래 조건문을 사용 contextPath를 이용해 해결한다 -->
<c:if test='${fn:startsWith(linkPage,"/")}'>
	<c:set var="linkPage"
		value="${pageContext.request.contextPath}${linkPage }" />
</c:if>
<nav class="blog-pagination justify-content-center d-flex">
<ul class="pagination">
	<!-- 이전 페이지 -->
	<c:if test="${pagingVO.firstPage > 1 }">
		<li><a class="page-link" href="${linkPage }?curPage=${pagingVO.firstPage - 1}"
			data-page="${pagingVO.firstPage - 1}"><span aria-hidden="true">&laquo;</span></a></li>
	</c:if>
	<!-- 페이지 넘버링  -->
	<c:forEach var="i" begin="${pagingVO.firstPage }"
		end="${pagingVO.lastPage }">
		<c:if test="${pagingVO.curPage == i }">
			<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
		</c:if>
		<c:if test="${pagingVO.curPage != i }">
			<li class="page-item"><a class="page-link" href="${linkPage }?curPage=${i }" data-page="${i }">${i }</a></li>
		</c:if>
	</c:forEach>

	<!-- 다음  페이지  -->
	<c:if test="${pagingVO.lastPage < pagingVO.totalPageCount }">
		<li class="page-item"><a class="page-link" href="${linkPage }?curPage=${pagingVO.lastPage + 1}"
			data-page="${pagingVO.lastPage + 1}"><span aria-hidden="true">&raquo;</span></a></li>
	</c:if>
</ul>
</nav>
