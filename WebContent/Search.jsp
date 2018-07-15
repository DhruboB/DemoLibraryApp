<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="entities.Items" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Library</title>
 <style>
 div#style{
 
     border-style: double;
     align:center;
     padding: 20px; 
     width: 550px;
     height: 100px;
     color : blue;
     opacity: 0.8;
     filter: alpha(opacity=70);
     margin: 0 auto;
     overflow: hidden;
     -moz-border-radius: 10px;
     -webkit-border-radius: 10px;
     border-collapse: separate;
     border-spacing: 10px;
 }
 
 div#style:hover{
      opacity: 1.0;
      filter: alpha(opacity=100);
      background-color : #f1f1f1 ;
 }
 div#background{
        width: 100%; 
        height: 100%; 
        position: fixed; 
        left: 0px; 
        top: 0px; 
        z-index: -1;
        opacity: 0.32;
        filter: alpha(opacity=60);
 
 }
 
 .stretch {
    width:100%;
    height:107%;
}
      .css1{
  
      margin-right : 3px;
      margin-left : 510px;
      -moz-border-radius: 10px;
      -webkit-border-radius: 10px;
      width:80px;
      height:30px;
      }
      
      h2{
          font-family: "Times New Roman", Times, serif;
          text-align : center;
          color : red;
      }
      a{
          float : right;
          font-family: "Times New Roman", Times, serif;
          font-style: italic;
      }
      .redtxt{
     color:red;
     font-weight:bold;
     font-size:120%;
     text-decoration:underline;
     font-family:lucida handwriting;
   }
   .textpad{
       font-size:100%;
   width:250px;
   height:25px;
   -moz-border-radius: 15px;
   -webkit-border-radius: 15px;
   font-family:bold;
   text-align:center;
   }
div#contains1{
    position: fixed; 
    bottom: 16px; 
    right: 40px; 
    text-align: right;
    
   }
    
  </style>
</head>

  <body>
  
  <div id ="background">
   <img alt="" src="https://ladeetdareads.files.wordpress.com/2015/03/tree-of-knowledge-books.jpg" class="stretch">
  </div>
  <div id="contains1">
   <font color="blue" style="font-family:lucida handwriting;font-weight:bold;font-size:70%;">Developed by Team<span class="tab"></span> <span
      class="redtxt" >Dhrubo</span>   </font>
   </div>
  
    <div align="center" style="float : center;"><h2>Search your Book Here</h2></div><form action="/OnlineBookStoreWebApp/EditProfile"  method="post"><div style="float:right;color:blue;">Logged in as :<input name="user" id="user" type="text" style="border:none;color:red;font-weight:bold;margin-right:50px;" size="7" value="<%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %>" readonly /></div>
    <div style="float:right;margin-right:110px;margin-top:17px;"><input type="submit" style="padding-top:7px;padding-bottom:5px;-moz-border-radius:8px;
   -webkit-border-radius: 8px" value="Edit Profile" ></div></form>
    <form onclick="newrespond()" action="http://onlinelibrary.bluemix.net/">
    <input id="logout" name="logout" type="submit" value="Log Out" style="float:right;margin-right:-390px;margin-top:-3px;padding-top:3px;padding-bottom:5px; -moz-border-radius:7px;
   -webkit-border-radius: 7px;"></form><br>
  <form id="SearchForm" name="SearchForm" method="post" onsubmit="return validateForm();" action="/OnlineBookStoreWebApp/Search">
  <div style="align:center;">
  <select class="css1" id ="searchBy" name="searchBy">
      <option value = "Author">Author</option>
      <option value = "BookName">Book Name</option>
      <option value ="Subject">Subject</option>
      <option value="Rating">Rating</option>
  </select>
    <input class ="textpad" type ="text" id ="searchValue" name="searchValue" placeholder="search here">
     <input type="hidden" name="user" value="<%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %>">
      <input type="submit"  value="Search" id="button1" style ="-moz-border-radius: 11px;-webkit-border-radius: 11px;width:63px;height:31px;padding-top:7px;padding-bottom:5px"><br><br>
     </div>
     </form>
  <%List<Items> itemList = new ArrayList<Items>();
  if(request.getAttribute("itemList")!=null)
  {
	  itemList=(List<Items>)request.getAttribute("itemList");
  }
  
   for (int i=0;i<itemList.size();i++)
  {
	   Items item=(Items)itemList.get(i);%>
	   <form id="data" name="data" method="POST" action="/OnlineBookStoreWebApp/IssueBook">
	  <div id="style">
	  <label style="padding-right:50px">Author    :</label>
	  <input id="Author"  style="color:crimson;-moz-border-radius: 5px;
   -webkit-border-radius: 5px" name="Author" type="text" value="<%=item.getAuthor() %>"  readonly><br>
      <label style="padding-right:18px;">Book Name :</label>
      <input id="BookName" style="color:crimson;-moz-border-radius: 5px;
   -webkit-border-radius: 5px" name="BookName" type="text" value="<%=item.getBookName() %>" readonly><br>
	  <label style="padding-right:47px">Subject   :</label>
	  <input id="Subject" style="color:crimson;-moz-border-radius: 5px;
   -webkit-border-radius: 5px" name="Subject" type="text" value="<%=item.getSubject() %>" readonly><br>
	  <label style="padding-right:52px">Rating    :</label>
	  <input id="Rating" style="color:crimson;-moz-border-radius: 5px;
   -webkit-border-radius: 5px" Name="Rating" type="text" value="<%=item.getRating() %>" readonly><br>
	  <div style="margin-top:-57px;margin-right:80px"><input type="submit" style="color:blue;-moz-border-radius: 7px;
   -webkit-border-radius: 7px;float:right;padding-top:7px;padding-bottom:7px" value="Issue Book" onsubmit="return validateForm();" ></div>
	  <input type="hidden" name="user" value="<%= request.getAttribute("user")==null?"Guest":request.getAttribute("user") %>">
	  <input type="hidden" name="BookId" value="<%= item.getBookId()%>">
	  </div>
	  </form>
	  <%
  }%>
  </form>
  <%itemList.clear(); %>
  <script type="text/Javascript">
function validateForm(SearchForm)   
{
    valid=true;
	if (document.SearchForm.input1.value=="Guest")
    {   
    	alert("You are not logged in")
    	window.location.href = 'http://onlinelibrary.bluemix.net/';
    	valid=false;
    }
	return valid;
    }  
function newrespond(){
	alert("You have Successfully Logged Out...");
}
</script>
    </body>
   
  </html>