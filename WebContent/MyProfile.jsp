<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="entities.Books" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyProfile</title>
<style>
body
{
background-color:Wheat;
}
</style>
</head>
<body>
<div align="center" style="color:red;margin-top:30px"><h1>Return Book</h1><div style="color:blue"><h4>The Books that you have to return are :</h4></div></div>
<form onclick="newrespond()" action="http://onlinelibrary.bluemix.net/"><div style="float:right;margin-right:70px;margin-top:-2px;color:blue">Logged in as : <input name="user" id="user" type="text" style="border:none;color:red;font-weight:bold;margin-right:20px" size="7" value="<%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %>" readonly /></div>
 <div style="float:right;margin-right:-255px;margin-top:-4px"><input id="logout" style="padding-top:3px;padding-bottom:3px;-moz-border-radius: 7px;
   -webkit-border-radius: 7px" name="logout" type="submit" value="Log Out"></div>
   </form>
   <form action="/OnlineBookStoreWebApp/ReturnSearch" method="post">
<input type="hidden" name="user" value="<%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %>">
<input type="submit" value="Go back to Search Page" style="float:right;-moz-border-radius: 5px;
   -webkit-border-radius: 5px;margin-top:22px;margin-right:-160px">
</form>
<%List<Books> itemList = new ArrayList<Books>();
  if(request.getAttribute("itemList")!=null)
  {
	  itemList=(List<Books>)request.getAttribute("itemList");
  }
  
   for (int i=0;i<itemList.size();i++)
  {
	   Books item=(Books)itemList.get(i);%>
	   <form id="SubmitBook" name="SubmitBook" onclick="respond()" method="post" action="/OnlineBookStoreWebApp/SubmitBook">
	  <div id="style" style="margin-left:480px;margin-top:80px">
          <label style="margin-right:20px;font-weight:bold;color:blue;margin-left:-40px">
	   Book Name  :</label><input id="Book" name="Book"  style="-moz-border-radius: 5px;
   -webkit-border-radius: 5px" type="text" value="<%=item.getBooks() %>"  readonly>
	 <span style="margin-left:10px"><input type="submit" value="Return Book" style="-moz-border-radius: 5px;
   -webkit-border-radius: 5px" ></span>
	  <input type="hidden" name="user" value="<%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %>">
	  <input type="hidden" name="bookId" value="<%= item.getBookId() %>">
	  </div>
	  </form>
	  
	  <%
  }%>
  <form action="/OnlineBookStoreWebApp/ReturnSearch" method="post">
<input type="hidden" name="user" value="<%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %>">
<input type="submit" value="Go back to Search Page" style="float:right;-moz-border-radius: 5px;
   -webkit-border-radius: 5px;margin-top:22px;margin-right:-160px">
</form>
  <%itemList.clear(); %>
  <script type="text/Javascript">

function respond()   
{
	alert("You have Successfully Submitted the Book");
}
function newrespond(){
	alert("You have Successfully Logged Out...");
}
</script>
</body>
</html>