<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Register User</title>
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body
	style="background-size: 100% 118%; background-repeat: no-repeat; background-image: url(https://readrage.files.wordpress.com/2015/05/leaf_on_a_book-wallpaper-3840x2400.jpg);">
	<div align="center" style="position: relative; top: 35%;">
		<h2 align="center" style="color: #EF0D36;">
			<i>New User Sign Up</i>
		</h2>
		<br>
		<form id="Signup" name="Signup" onsubmit="return newDoc();"
			action="/OnlineBookStoreWebApp/Signup" method="post">
			<input type="text" id="username" name="username"
				placeholder=" Enter New Username"
				style="font-size: 120%; font-family: bold; color: blue; width: 250px; height: 27px; -moz-border-radius: 15px; -webkit-border-radius: 15px; text-align: center;"><br>
			<br> <input type="password" id="password" name="password"
				placeholder=" Enter New Password"
				style="font-size: 120%; font-family: bold; color: blue; width: 250px; height: 27px; -moz-border-radius: 15px; -webkit-border-radius: 15px; text-align: center;"><br>
			<br> <input type="password" id="repassword" name="repassword"
				placeholder=" Re-Enter New Password"
				style="font-family: bold; color: blue; font-size: 120%; width: 250px; height: 27px; -moz-border-radius: 15px; -webkit-border-radius: 15px; text-align: center;"><br>
			<br>
			<div id="errors" style="color: red">
				<h4 style="color: red"><%=request.getAttribute("err") == null ? "" : request.getAttribute("err")%>
				</h4>
			</div>
			<input type="submit" value="SUBMIT"
				style="color: blue; width: 81px; height: 35px; -moz-border-radius: 15px; -webkit-border-radius: 15px; font-family: verdana; font-size: 87%;">
			<h3 style="color: blue">
				<a href="http://onlinelibrary.bluemix.net/">Already Member?</a>
			</h3>
		</form>
		<h4
			style="float: left; position: relative; top: 60px; bottom: 6 px; font-family: lucida handwriting; font-size: 78%; color: #800000; margin-left: 50px;">
			For Best View Open Site Using : <i class="fa fa-chrome"
				style="font-size: 25px; color: #00008B; margin-left: 10px; margin-right: 15px"></i>
			<i class="fa fa-internet-explorer"
				style="font-size: 25px; color: #00008B; margin-right: 15px"></i> <i
				class="fa fa-safari"
				style="font-size: 25px; color: #00008B; margin-right: 15px"></i> <i
				class="fa fa-firefox"
				style="font-size: 25px; color: #00008B; margin-right: 15px"></i>
		</h4>
		<div id="contains1">
			<h3
				style="position: fixed; bottom: 15px; width: 99%; font-family: lucida handwriting; font-size: 75%;">
				<font color="blue" style="float: right; margin-right: 30px;">Developed
					by <span
					style="color: red; font-weight: bold; font-size: 120%; text-decoration: underline; float: right; margin-left: 10px;">Dhrubo</span>
				</font>
			</h3>
		</div>
	</div>
	<script type="text/Javascript">
		function newDoc(Signup) {
			valid = true
			if (document.Signup.username.value == "") {
				document.getElementById('errors').innerHTML = "*Please enter a username*";
				valid = false;
			} else {
				if (document.Signup.password.value == "") {
					document.getElementById('errors').innerHTML = "*Please enter a password*";
					valid = false;
				} else {
					if (document.Signup.repassword.value == "") {
						document.getElementById('errors').innerHTML = "*Please Re-enter the password*";
						valid = false;
					} else {
						if (document.Signup.password.value != document.Signup.repassword.value) {
							document.getElementById('errors').innerHTML = "*Password Missmatch*";
							valid = false;
						}
					}
				}
			}
			if (valid == true) {
				alert("Thank you for Signing up ....");
			}
			return valid;

		}
	</script>
</body>
</html>