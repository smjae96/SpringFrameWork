<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
	
<!-- JavaScript -->
<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
<!-- Default theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
<!-- Semantic UI theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
<style>
.outer {
	background-color: #e7e7e7;
	width: 80%;
	margin: auto;
}

.inner-area {
	border: 1px solid #000025;
	width: 80%;
	margin: auto;
	padding: 5% 15%;
	background: #e7ecf7;
}

#top-area {
	display: flex;
	justify-content: space-around;
	padding: 1em;
}

a {
	text-decoration: none;
	color: black;
}

nav {
	margin: 0;
	padding: 0;
}

nav div {
	font-weight: bold;
	height: 50px;
	line-height: 50px;
}

nav div:hover, nav div.active {
	cursor: pointer;
}

nav div.active {
	background-color: #485ba6;
	color: white;
}
</style>
</head>
<body>
	<!-- session 영역에 저장된 alertMsg를 표시 + 제거 -->
	<c:if test="${alertMsg != null }">
	
		<script>
			alertify.alert('알림!', "${alertMsg}");
		</script>
		<c:remove var="alertMsg" scope="session"/>
	</c:if>
	
	
	<div id="top-area">
		<img src="https://khedu.co.kr/resources/images/main/logo.svg"
			alt="kh_logo" width="150" />
		<div class="d-flex flex-fill"></div>
		
		<c:choose>
		<c:when test="${ empty loginUser }">
		
		<div>
			<a href="enrollForm.me">회원가입</a> &nbsp;|&nbsp; 
			<a href="#" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
		</div>
		</c:when>
		<c:otherwise>
		
		<div>
            <label>${loginUser.userName }님 환영합니다.</label> &nbsp;&nbsp;
            <a href="myPage.me">마이페이지</a>
            <a href="logout.me">로그아웃</a>        
        </div> 
        </c:otherwise>
        </c:choose>
	</div>
	<hr>
	<nav class="container text-center">
		<div class="row">
			<div class="col">HOME</div>
			<div class="col">공지사항</div>
			<div class="col" onclick="location.href='list.bo'">자유게시판</div>
			<div class="col">사진게시판</div>
		</div>
	</nav>
	<!-- 로그인 모달 -->
	<div class="modal fade" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5">LOGIN</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="login.me" method="post">
					<div class="modal-body">
						<div class="mb-3">
							<label for="userId" class="col-form-label">ID:</label> <input
								type="text" class="form-control" placeholder="Enter ID.."
								id="userId" name="userId" />
						</div>
						<div class="mb-3">
							<label for="userPwd" class="col-form-label">PASSWORD:</label> <input
								type="password" class="form-control"
								placeholder="Enter Password.." id="userPwd" name="userPwd" />
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">로그인</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
        window.onload = () => {
            const menuList = document.querySelectorAll("nav div");
            menuList.forEach((menu)=>{              
                menu.addEventListener('click', (ev)=>{           
                    removeActiveClass(menuList);         
                    ev.target.classList.add("active");
                });
            })
            
        }
        const removeActiveClass = (list) => {
            list.forEach((menu)=>{
                menu.classList.remove("active");
            })
        }
      </script>
</body>
</html>