<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="/WEB-INF/views/common/common-libs.jsp" />
<head>
<title>Root Memory</title>
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Spring MVC Demo</a>
	</div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container">
	<h1>${demo.title}</h1>
	<p>
		<c:if test="${not empty demo.name}">
			Hello <font color='red'>${demo.name}</font>
		</c:if>

		<c:if test="${empty demo.name}">
			Welcome to Spring MVC Demo Project!
		</c:if>
    </p>
    <p>
	<a class="btn btn-primary btn-lg" href="./demo/show" role="button">Home</a>
    </p>
  </div>
</div>

<div class="container">
  <hr>
  <footer>
	<p>© Hongten ${demo.copyRightYear}</p>
  </footer>
</div>
</body>
</html>