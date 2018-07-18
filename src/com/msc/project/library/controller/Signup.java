package com.msc.project.library.controller;

import java.io.IOException;

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
import com.msc.project.library.utility.HttpPost;


@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private String user;
	private String u;
	private String pass;
	private JSONObject payload;
	private static final long serialVersionUID = 1L;
	private Boolean bool;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	user=request.getParameter("username");
		System.out.println("username is: "+user);
		pass=request.getParameter("password");
		System.out.println("password is: "+pass);
		String restUrl=HttpConnection.getProperties().getProperty("CLOUDANT_URL")+"_design/User/_search/byUserPass?q=*%3A*";
		HttpConnection obj=new HttpConnection();
		 String str1;
		 bool = false;
		try {
			str1 = obj.fetchDetails(restUrl);
			System.out.println(str1);
		
	     JSONObject res = new JSONObject(str1);
			   JSONArray jsonResArr = res.getJSONArray("rows");
			   if(jsonResArr.length()==0){
				  System.out.println("user not found");
			   }
			   else{
					int iJsonResArrSize = jsonResArr.length();
					for (int i=0;i<iJsonResArrSize;i++){
			        JSONObject jsonResElem = jsonResArr.getJSONObject(i);
			    u = jsonResElem.getJSONObject("fields").getString("username");
			    if(user.equalsIgnoreCase(u)){
			    	bool=true;
			    }
					}
			   }
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(bool==false){
		JSONObject json=new JSONObject();
		try {
			JSONArray list = new JSONArray();
			json.put("username", user);
			json.put("password", pass);
			json.put("Book", list);
			System.out.println("Json list"+json.toString());
			payload=json;
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			process();
			String strViewPage="/OnlineBookStoreWebApp/index.jsp";
		    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
		    dispatcher.forward(request, response);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			request.setAttribute("err","Username Already Exist");
			String strViewPage="/OnlineBookStoreWebApp/Signup.jsp";
		    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
		    dispatcher.forward(request, response);
		}
		
	}
	
	private void process() throws JSONException{
		String requestUrl=HttpConnection.getProperties().getProperty("CLOUDANT_URL");
		HttpPost obj=new HttpPost();
		obj.sendPostRequest(requestUrl, payload);
	}
	}

