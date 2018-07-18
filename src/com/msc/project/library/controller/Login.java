package com.msc.project.library.controller;

import java.io.IOException;

//import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.msc.project.library.utility.HttpConnection;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private String user;
	private String pass;
	private String u;
	private String p;
	
	private String str1;
	
	
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		  response.setContentType("text/html");
		  response.setHeader("Content-Type", "text/plain");
			user=request.getParameter("username");
			System.out.println("username is: "+user);
			pass=request.getParameter("password");
			System.out.println("password is: "+pass);
			
			
			try {
				process();
				if(user.equals(u) && pass.equals(p)){
					request.setAttribute("user",u);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("Search.jsp");
					requestDispatcher.forward(request, response);
				
				}
				else{
					request.setAttribute("err","Username or password incorrect");
				    String strViewPage="/OnlineBookStoreWebApp/index.jsp";
				    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
				    dispatcher.forward(request, response);
				}
				
			}catch (JSONException e) {
				e.printStackTrace();
			}
	}
			
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	            doGet(request, response);
	   }
	private void process() throws JSONException, UnsupportedEncodingException{
		String usr=URLEncoder.encode(user, "UTF-8");
		String restUrl=HttpConnection.getProperties().getProperty("CLOUDANT_URL")+"_design/User/_search/byUserPass?q=username:@"+usr;
		HttpConnection obj=new HttpConnection();
		 str1=obj.fetchDetails(restUrl);
	     JSONObject res = new JSONObject(str1);
			   JSONArray jsonResArr = res.getJSONArray("rows");
			   if(jsonResArr.length()==0){
				  System.out.println("user not found");
			   }
			   else{
			   JSONObject jsonResElem = jsonResArr.getJSONObject(0);
			    u = jsonResElem.getJSONObject("fields").getString("username");
			    System.out.println(u);
				p = jsonResElem.getJSONObject("fields").getString("password");
				System.out.println(p);
			   }

}
	}