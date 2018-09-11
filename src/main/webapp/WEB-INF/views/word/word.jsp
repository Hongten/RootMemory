<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Root Memory</title>
<jsp:include page="/WEB-INF/views/common/common-libs.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #e9e9e9;
}

.topnav a {
	float: left;
	display: block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #2196F3;
	color: white;
}

.topnav .search-container {
	float: left;
}

.topnav input[type=text] {
	padding: 6px;
	margin-top: 8px;
	font-size: 17px;
	border: none;
	margin-left: 200px;
}

.topnav .search-container button {
	float: right;
	padding: 6px 10px;
	margin-top: 8px;
	margin-right: 16px;
	background: #ddd;
	font-size: 17px;
	border: none;
	cursor: pointer;
}

.topnav .search-container button:hover {
	background: #ccc;
}

@media screen and (max-width: 600px) {
	.topnav .search-container {
		float: none;
	}
	.topnav a, .topnav input[type=text], .topnav .search-container button {
		float: none;
		display: block;
		text-align: left;
		width: 100%;
		margin: 0;
		padding: 14px;
	}
	.topnav input[type=text] {
		border: 1px solid #ccc;
	}
}

.autocompleteresult {
	background-color:white; 
	border: 1px solid red;
	width: 228px;
	position: absolute;
	top: 44px;
	margin-left: 200px;
	display:none
}
</style>
</head>


<div class="container">
	<div class="topnav">
		<a class="active" href="./word/default">Home</a> <a href="./word/aboutpage">About</a> <a
			href="./word/contactpage">Contact</a>
		<div class="search-container">
			<form action="javascript: submitForm()">
				<input type="text" placeholder="Search.." name="search" id="search"
					value="${word.wordName}" autocomplete="off">
				<div id="autocompleteDiv" class="autocompleteresult"></div>
				<button type="submit" id="submitBtn">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>
	</div>
</div>

<div class="container">
	<div class="jumbotron">
		<h2>${word.wordName}</h2>
		<p>
			<font size='2'>Eng: <b>${word.englishProunciation}</b> Ame: <b>${word.americanProunciation}</b></font>
			<br>
			<c:forEach items="${word.wordMeanings}" var="mean">
				<font size='2'><c:out value="${mean.meaning}" /></font>
				<br>
			</c:forEach>
		</p>
	</div>
</div>

<div class="container">
	<p id="relDIV"
		onclick="showOrHide('relationshipDiv', 'relDIV', 'relation')">Relationship
		[Close]</p>
	<hr>
	<div id="relationshipDiv" class="jumbotron">
		<iframe name="person" id="person" frameborder="0"
			src="./word/relationship" scrolling="no" width="100%" height="550">
		</iframe>
	</div>
	<p id="examDiv"
		onclick="showOrHide('examplesDiv', 'examDiv', 'example')">Examples
		[Close]</p>
	<hr>
	<div id="examplesDiv" class="jumbotron">
		<iframe name="person" id="person" frameborder="0"
			src="./word/examples" scrolling="no" width="100%" height="350">
		</iframe>
	</div>
	<hr>
	<footer>
		<p>© Hongten ${copyRightYear}</p>
	</footer>
</div>
<script>
	function showOrHide(divId, id, type) {
		var x = document.getElementById(divId);
		var t = document.getElementById(id);
		if (x.style.display === "none") {
			x.style.display = "block";
			if (type == 'example') {
				t.innerHTML = 'Examples [Close]';
			}
			if (type == 'relation') {
				t.innerHTML = 'Relationship [Close]';
			}
		} else {
			x.style.display = "none";
			if (type == 'example') {
				t.innerHTML = 'Examples [Open]';
			}
			if (type == 'relation') {
				t.innerHTML = 'Relationship [Open]';
			}
		}
	}

	$('#search').focus();

	function submitForm() {
		var x = $('#search').val().toLowerCase();
		if (x == null || x == '') {
			//skipping
		} else {
        	window.history.pushState({},0,'${pageContext.request.contextPath}/word/search/' + x);
        	location.reload();
		}
	}
	
	
	$("#search").keyup(function(){
        var content=$(this).val();
        if(content==""){
            $("#autocompleteDiv").css("display","none");
            return ;
        }

        var time=new Date().getTime();
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/word/autocomplete/" + $('#search').val().toLowerCase() +"/"+time,
            success:function(data){
                var res=data.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    html+="<div onclick='setSearch_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#autocompleteDiv").html(html);
                $("#autocompleteDiv").css("display","block");
            }
        });
    });
	
    function changeBackColor_over(div){
        $(div).css("background-color","#CCCCCC");
    }

    function changeBackColor_out(div){
        $(div).css("background-color","");
    }
    
    function setSearch_onclick(div){
        $("#search").val(div.innerText);
        $("#autocompleteDiv").html("");
        $("#autocompleteDiv").css("display","none");
        submitForm();
    }
</script>
</body>
</html>