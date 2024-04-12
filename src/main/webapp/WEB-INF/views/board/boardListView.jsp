<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    
<title>자유 게시판</title>
    
<style>
     #boardList {text-align: center;}
     #boardList>tbody>tr:hover{cursor:pointer;}

     #pagingArea {width:fit-content; margin:auto;}

     #searchForm {width:80%; margin: auto;}
     #searchForm>* {float:left; margin:5px;}
        
     .select {width:25%;}
     .text {width:48%;}
     .searchBtn{width:20%;}
</style>
</head>
<body>
    <%-- header --%>
    <jsp:include page="../common/header.jsp" />

    <div class="outer">
        <br><br>
        <div class="innerOuter" style="padding: 5% 10%">
            <h2>게시판</h2>
            <br>

            <%-- 로그인 시에만 글쓰기 버튼 표시 --%>
            <c:if test="${loginUser != null }">
            <a href="" class="btn btn-secondary" style="float:right">글쓰기</a>
            </c:if>
            <br>
            
            <br>
            <table id="boardList" class="table table-hover" align="center">
                <thead>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>첨부파일</th>
                </thead>

                <tbody>
                <c:choose>
				<c:when test="${not empty list }">
                	<c:forEach var="b" items="${list}">
                    <tr>
                        <td>${b.boardNo }</td>
                        <td>${b.boardTitle }</td>
                        <td>${b.boardWriter }</td>
                        <td>${b.count }</td>
                        <td>${b.createDate }</td>
                        <c:if test="${not empty b.originName  }">
                        <td>💾</td>
                        </c:if>
                        <c:if test="${empty b.originName  }">
                        <td></td>
                        </c:if>
                    </tr>
                    </c:forEach>   
                    </c:when>
                    <c:otherwise>
                    	<tr>
                    		<td colspan='6'></td>
                   		</tr>
                	</c:otherwise>
                	</c:choose>	                                   
                </tbody>
            </table>
            <br>

            <div id="pagingArea">
                <ul class="pagination">
                    <li class="page-item"><c:if test="${pi.currentPage > 1 }"><a href="list.bo?cpage=${pi.currentPage -1 }" class="page-link">Prev</a></c:if></li>
                    <c:forEach var="page" begin="${pi.startPage }" end="${pi.endPage }">
                    <li class="page-item"><a href="list.bo?cpage=${page }" class="page-link">${page}</a></li>
                    </c:forEach>
                    <li class="page-item"><c:if test="${pi.currentPage < pi.maxPage }"><a href="list.bo?cpage=${pi.currentPage + 1 }" class="page-link">Next</a></c:if></li>
                </ul>
            </div>

            <br clear="both">

            <form action="" id="searchForm">
                <div class="select">
                    <select name="condition" id="" class="custom-select form-select">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword">
                </div>
                <button class="searchBtn btn btn-secondary">검색</button>
            </form>
            <br><br>
        </div>
        <br><br>
    </div>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />
</body>
</html>