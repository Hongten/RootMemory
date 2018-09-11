<!DOCTYPE html>
<html lang="en">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">


<!-- All common css in here. -->
<link href="./resources/css/bootstrap/3.3.4/bootstrap.min.css" rel="stylesheet" />

<!-- All common js in here. -->
<script src="./resources/js/jquery/1.11.2/jquery.min.js"></script>
<script src="./resources/js/jquery/1.11.2/jquery-ui.js"></script>
<script src="./resources/js/bootstrap/3.3.4/bootstrap.min.js"></script>

</html>