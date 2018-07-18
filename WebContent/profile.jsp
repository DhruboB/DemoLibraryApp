<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List"%>
<%@ page import="com.msc.project.library.entities.Book"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyProfile</title>
<style>
body {
	background-color: Wheat;
}
</style>
</head>
<body>
	<div align="center" style="color: red; margin-top: 30px">
		<h1>Return Book</h1>
		<div style="color: blue">
			<h4>You are currently possessing below Book:</h4>
		</div>
	</div>


	<%-- <form action="index.jsp"><div style="float:right;margin-right:70px;margin-top:-2px;color:blue">Logged in as : 
<label style="border:none;color:red;font-weight:bold;margin-right:20px"><%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %></label>
</div>
 <div style="float:right;margin-right:-255px;margin-top:-4px">
 <input id="logout" style="padding-top:3px;padding-bottom:3px;-moz-border-radius: 7px;-webkit-border-radius: 7px" name="logout" type="submit" value="Log Out" onclick="logoutMsg()"></div>
   </form> --%>

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
						<form action="/OnlineBookStoreWebApp/ReturnSearch" method="post">
							<input type="hidden" name="user"
								value="<%=request.getAttribute("user") == null ? "Guest" : request.getAttribute("user")%>">
							<input type="submit" value="Go back to Search Page"
								style="padding-top: 7px; padding-bottom: 5px; -moz-border-radius: 8px; -webkit-border-radius: 8px">
						</form>
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


	<%
		List<Book> itemList = new ArrayList<Book>();
		if (request.getAttribute("itemList") != null) {
			itemList = (List<Book>) request.getAttribute("itemList");
		}

		for (int i = 0; i < itemList.size(); i++) {
			Book item = (Book) itemList.get(i);
	%>
	<form id="SubmitBook" name="SubmitBook" method="post"
		action="/OnlineBookStoreWebApp/SubmitBook">
		<div id="style" style="margin-left: 480px; margin-top: 80px">
			<label
				style="margin-right: 20px; font-weight: bold; color: blue; margin-left: -40px">
				Book Name :</label> <input id="Book" name="Book" type="hidden"
				value="<%=item.getBooks()%>"> <label
				style="-moz-border-radius: 5px; -webkit-border-radius: 5px"><%=item.getBooks()%></label>

			<span style="margin-left: 10px"><input type="submit"
				value="Return Book"
				style="-moz-border-radius: 5px; -webkit-border-radius: 5px"></span>
			<input type="hidden" name="user"
				value="<%=request.getAttribute("user") == null ? "Guest" : request.getAttribute("user")%>">
			<input type="hidden" name="bookId" value="<%=item.getBookId()%>">
		</div>
	</form>

	<%
		}
	%>
	<%
		itemList.clear();
	%>
	<script type="text/Javascript">
		function respond() {
			alert("You have Successfully Submitted the Book");
		}
		function logoutMsg() {
			alert("You have Successfully Logged Out...");
		}
	</script>
</body>
</html>