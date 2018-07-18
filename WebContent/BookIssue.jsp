<%@page import="com.msc.project.library.entities.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Issue</title>
<style>
body {
	background-color: Wheat;
}

div#calledvalue {
	width: 400px;
	border: 10px solid green;
	padding-left: 90px;
	margin-left: 430px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	margin-top: 90px;
}
</style>
</head>
<body>
	<h1 align="center">
		<b style="color: blue;">Issue Your Book Here</b>
	</h1>

	<div align="right" style="float: right;">
		<table>
			<tr>
				<td>
					<div>
						<label style="color: blue;">Logged in as :</label> <label
							style="border: none; color: red; font-weight: bold; margin-right: 5px"><%=request.getAttribute("user") == null ? "Guest" : request.getAttribute("user")%>
						</label>
					</div>
				</td>
				<td>
					<div style="float: right; margin-right: 20px; margin-top: 10px;">
						<form action="index.jsp">
							<input id="logout" name="logout" type="submit" value="Log Out"
								onclick="logoutMsg()"
								style="padding-top: 7px; padding-bottom: 5px; -moz-border-radius: 8px; -webkit-border-radius: 8px">
						</form>
					</div>
				</td>
			</tr>
		</table>
	</div>


	<div id="calledvalue">
		<p align="left" style="padding-left: 5 px"><B>
			Book Name :</B>
			<%=request.getAttribute("BookName")%></p>
		<p align="left" style="padding-left: 5 px"><B>
			Author :</B>
			<%=request.getAttribute("Author")%></p>
		<p align="left" style="padding-left: 5 px"><B>
			Subject :</B>
			<%=request.getAttribute("Subject")%></p>
		<p align="left" style="padding-left: 5 px"><B>
			Rating :</B>
			<%=request.getAttribute("Rating")%></p>
	</div>
	<%
		if (request.getAttribute("Flag").equals("ok") == true) {
	%>
	<form id="TakeBook" name="TakeBook" onclick="respond()"
		action="/OnlineBookStoreWebApp/TakeBook" method="post">
		<input type="hidden" name="user"
			value="<%=request.getAttribute("user") == null ? "Guest" : request.getAttribute("user")%>">
		<input type="hidden" name="BookName"
			value="<%=request.getAttribute("BookName") == null ? "" : request.getAttribute("BookName")%>">
		<input type="hidden" name="BookId"
			value="<%=request.getAttribute("BookId") == null ? "" : request.getAttribute("BookId")%>">
		<br>
		<br>
		<br>
		<br> <a style="margin-top: 70px; margin-left: 450px;"><input
			type="submit" value="Issue Book"
			style="width: 150px; height: 30px; -moz-border-radius: 15px; -webkit-border-radius: 15px;"></a>
	</form>
	<%
		} else {
	%>
	<br>
	<br>
	<br>
	<br>
	<a style="margin-top: 70px; margin-left: 450px;"><input
		style="width: 200px; height: 30px; -moz-border-radius: 15px; -webkit-border-radius: 15px;"
		type="submit" value="Book Not Available" disabled> </a>
	<%
		}
	%>
	<div style="margin-left: 690px; margin-top: -24px">
		<form action="/OnlineBookStoreWebApp/ReturnSearch" method="post">
			<input type="hidden" name="user"
				value="<%=request.getAttribute("user") == null ? "Guest" : request.getAttribute("user")%>">
			<input type="submit" value="Go back to Search Page"
				style="width: 200px; height: 30px; margin-left: 25px; margin-top: -85px; -moz-border-radius: 15px; -webkit-border-radius: 15px;">
		</form>
	</div>
	<h2 align="center" style="color: blue; margin-top: 80px">Comments
		:</h2>
	<%
		List<Comment> itemList = new ArrayList<Comment>();
		if (request.getAttribute("itemList") != null) {
			itemList = (List<Comment>) request.getAttribute("itemList");
		}

		for (int i = 0; i < itemList.size(); i++) {
			Comment item = (Comment) itemList.get(i);
	%>
	<form id="comments" name="comments">
		<div id="style" align="center">
			<b style="color: red"><%=item.getName()%></b>:<%=item.getComments()%>
		</div>
	</form>
	<%
		}
	%>

	</form>
	<%
		itemList.clear();
	%>
	<script type="text/Javascript">
		function validateForm(SearchForm) {
			valid = true;
			if (document.SearchForm.input1.value == "Guest") {
				alert("You are not logged in")
				window.location.href = 'http://onlinelibrary.bluemix.net/';
				valid = false;
			}
			return valid;
		}
		function respond() {
			alert("You have Successfully taken this Book");
		}
		function newrespond() {
			alert("You have Successfully Logged Out...");
		}
	</script>
</body>
</html>