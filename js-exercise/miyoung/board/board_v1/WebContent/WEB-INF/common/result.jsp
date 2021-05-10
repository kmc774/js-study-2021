<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<script type="text/javascript">
		
		<%-- alertMsg 있을 경우 메시지 alert --%>
		<c:if test="${alertMsg != null}">
			alert("${alertMsg}");
		</c:if>
		
		<%-- url있을 경우 해당 경로로 이동 --%>
		<c:if test="${url != null}">
			location.href = '${url}';		
		</c:if>
	</script>
	
	
	
	

</body>
</html>