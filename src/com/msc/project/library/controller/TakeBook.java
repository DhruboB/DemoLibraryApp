package com.msc.project.library.controller;

import java.io.IOException;
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
import com.msc.project.library.utility.HttpPost;

@WebServlet("/TakeBook")
public class TakeBook extends HttpServlet {
	private String user;
	private String BookName;
	private String BookId;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user = request.getParameter("user");
		BookId = request.getParameter("BookId");
		BookName = request.getParameter("BookName");
		request.setAttribute("user", user);
		try {
			process();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Search.jsp");
		requestDispatcher.forward(request, response);
	}

	private void process() throws UnsupportedEncodingException, JSONException {
		String idDemo = URLEncoder.encode(BookId, "UTF-8");
		String restUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL") + idDemo;
		HttpConnection obj1 = new HttpConnection();
		String str1 = obj1.fetchDetails(restUrl);
		JSONObject res = new JSONObject(str1);
		res.remove("Flag");
		res.put("Flag", "1");
		String requestUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL");
		HttpPost obj3 = new HttpPost();
		obj3.sendPostRequest(requestUrl, res);

		String idDemou = URLEncoder.encode(user, "UTF-8");
		String restUrlid = HttpConnection.getProperties().getProperty("CLOUDANT_URL") + idDemou;
		HttpConnection obj5 = new HttpConnection();
		String str3 = obj5.fetchDetails(restUrlid);
		JSONObject res2 = new JSONObject(str3);
		JSONArray books = res2.getJSONArray("Books");
		JSONObject obbj = new JSONObject();
		obbj.put("BookId", BookId);
		obbj.put("BookName", BookName);
		books.put(obbj);
		HttpPost objpost = new HttpPost();
		objpost.sendPostRequest(requestUrl, res2);

	}
}