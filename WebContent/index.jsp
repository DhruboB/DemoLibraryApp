<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Library</title>
<style>
h2 {
	color: #EF0D36;
}

body {
	background-size: 100% 118%;
	background-repeat: no-repeat;
}

div#contains2 {
	position: relative;
	margin-top: 42px;
}

div#contains {
	width: 400px;
	margin-top: 165px;
	margin-left: 440px;
	padding: 30px;
}

div#errors {
	color: red;
}

div#contains1 {
	position: relative;
}

h4 {
	color: red;
	font-family: bold;
}

h1 {
	position: fixed;
	bottom: 10px;
	width: 99%;
	font-family: lucida handwriting;
	font-size: 75%;
}

marquee {
	font-size: 250%;
	font-family: calibri;
}

.submit {
	font-size: 60%;
	font-family: verdana;
	width: 81px;
	height: 35px;
	text-align: center;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	color: blue;
}

.userpass {
	font-size: 120%;
	width: 250px;
	height: 20px;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	font-family: bold;
	color: blue;
	text-align: center;
}

.redtxt {
	color: red;
	font-weight: bold;
	font-size: 120%;
	text-decoration: underline;
}

.tab {
	padding: 0 3px;
}
</style>

</head>

<body
	background="https://readrage.files.wordpress.com/2015/05/leaf_on_a_book-wallpaper-3840x2400.jpg">
	<p align="center">
		<marquee behavior="alternate" direction="right" scrollamount=8>
			<font color="#A5DF00">Welcome to the Online Library System</font>
		</marquee>
	</p>
	<div id="contains">
		<h2 align="center">
			<i>Login</i>
		</h2>
		<div align="center">
			<form id="loginForm" name="loginForm" method="post"
				onsubmit="return validateForm();"
				action="/OnlineBookStoreWebApp/login">
				<input id="username" class="userpass" type="text" name="username"
					placeholder="username"><br> <br> <input
					id="password " class="userpass" type="password" name="password"
					placeholder="password">
				<div id="errors">
					<h4><%=request.getAttribute("err") == null ? "" : request.getAttribute("err")%>
					</h4>
				</div>



				<h2>
					<input class="submit" type="submit" value="LOGIN"><span
						style="padding-left: 50px"> <input class="submit"
						type="button" value="SIGNUP" onclick="newDoc()">
					</span>
				</h2>
			</form>
			<div id="contains1">
				<h1>
					<font color="blue">Developed by <span class="tab"></span> <span
						class="redtxt">Dhrubo</span>
					</font>
				</h1>
			</div>
		</div>
	</div>
	<div id="contains2">
		<!-- <iframe src="https://www.facebook.com/plugins/follow.php?href=https%3A%2F%2Fwww.facebook.com%2Ftousif.ali.11&width=450&height=35&layout=standard&show_faces=false&appId" width="450" height="35" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowTransparency="true"></iframe> -->
	</div>
	<script type="text/Javascript">
		function validateForm(loginForm) {
			valid = true
			if (document.loginForm.username.value == "") {
				document.getElementById('errors').innerHTML = "*Please enter a username*";
				valid = false;
			} else {
				if (document.loginForm.password.value == "") {
					document.getElementById('errors').innerHTML = "*Please enter a password*";
					valid = false;
				}
			}
			return valid;
		}
		function newDoc() {
			window.location
					.assign("http://onlinelibrary.bluemix.net/Signup.jsp")
		}
	</script>
</body>
</html>