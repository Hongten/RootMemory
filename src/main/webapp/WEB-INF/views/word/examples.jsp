<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Root Memory</title>
</head>

<body>
	<c:forEach items="${word.examples}" var="example" varStatus="loop">
		<div>
			<p>
				${loop.index+1}. <font size='4'><c:out
						value="${example.englishSentence}" /><br>
				<c:out value="${example.chineseSentence}" /></font>
			</p>
		</div>
	</c:forEach>
</body>
</html>