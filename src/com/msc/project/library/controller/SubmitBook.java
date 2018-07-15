package com.msc.project.library.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.msc.project.library.entities.Books;
import com.msc.project.library.utility.HttpConnection;
import com.msc.project.library.utility.HttpPost;

@WebServlet("/SubmitBook")
public class SubmitBook extends HttpServlet {
	private String user;
	private String BookId;
	private static List<Books> result = new ArrayList<Books>();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user = request.getParameter("user");
		BookId = request.getParameter("bookId");
		request.setAttribute("user", user);
		request.setAttribute("bookId", BookId);
		try {
			process();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setAttribute("itemList", result);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("MyProfile.jsp");
		requestDispatcher.forward(request, response);
	}

	private void process() throws UnsupportedEncodingException, JSONException {
		String uname = URLEncoder.encode(user, "UTF-8");
		String rUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL")
				+ "_design/User/_search/byUserPass?q=username:@" + uname;
		HttpConnection obj2 = new HttpConnection();
		String id = null;
		String str2 = obj2.fetchDetails(rUrl);
		JSONObject res1 = new JSONObject(str2);
		JSONArray jsonResArr1 = res1.getJSONArray("rows");
		if (jsonResArr1.length() == 0) {
			System.out.println("no details available");
		} else {

			JSONObject jsonResElem1 = jsonResArr1.getJSONObject(0);
			id = jsonResElem1.getString("id");
			System.out.println("THE id is" + id);
		}
		String idDemo = URLEncoder.encode(id, "UTF-8");
		String restUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL") + idDemo;
		HttpConnection obj1 = new HttpConnection();
		String str1 = obj1.fetchDetails(restUrl);
		JSONObject res = new JSONObject(str1);
		JSONArray jsonRes = res.getJSONArray("Books");
		int iJsonResArrSize = jsonRes.length();
		for (int i = 0; i < iJsonResArrSize; i++) {
			JSONObject jsonResElements = jsonRes.getJSONObject(i);
			try {
				String BookDemo = jsonResElements.getString("BookId");
				if (BookDemo.equals(BookId)) {
					jsonResElements.remove("BookId");
					jsonResElements.remove("BookName");
				}
			} catch (JSONException e) {
				System.out.println("Error--");
			}
		}
		String requestUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL");
		HttpPost obj3 = new HttpPost();
		obj3.sendPostRequest(requestUrl, res);
		String restUrl2 = HttpConnection.getProperties().getProperty("CLOUDANT_URL") + BookId;
		HttpConnection obj5 = new HttpConnection();
		String str4 = obj5.fetchDetails(restUrl2);
		JSONObject res3 = new JSONObject(str4);
		res3.remove("Flag");
		res3.put("Flag", "0");
		String requestUrl5 = HttpConnection.getProperties().getProperty("CLOUDANT_URL");
		HttpPost obj6 = new HttpPost();
		obj6.sendPostRequest(requestUrl5, res3);

	}

}
