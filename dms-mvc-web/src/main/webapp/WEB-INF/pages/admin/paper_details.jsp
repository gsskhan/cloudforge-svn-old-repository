<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${status eq null}">
	<div class="alert alert-warning" style="text-align: center;">Search results for paper</div>
</c:if>
<c:if test="${status eq 'success'}">
	<div class="alert alert-success" style="text-align: center;"><c:out value="${statusmsg}"/></div>
</c:if>
<c:if test="${status eq 'error'}">
	<div class="alert alert-danger" style="text-align: center;"><c:out value="${statusmsg}"/></div>
</c:if>

${paper.paperTitle}