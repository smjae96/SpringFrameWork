<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Project</title>
</head>
<body>
	안녕~ 여기는 index.jsp입니다!
	<%-- WEB-INF/views/main.jsp 페이지로 포워딩 --%>
	<jsp:forward page="WEB-INF/views/main.jsp"/>
</body>
</html>