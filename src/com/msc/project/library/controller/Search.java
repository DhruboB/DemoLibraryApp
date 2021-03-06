package com.msc.project.library.controller;

import java.io.IOException;
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

import com.msc.project.library.entities.Items;
import com.msc.project.library.utility.HttpConnection;


@WebServlet("/Search")
public class Search extends HttpServlet {
	private String str1;
    private String searchBy;
    private String searchValue;
    private List<Items> result=new ArrayList<Items>();
	private static final long serialVersionUID = 1L;
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}
	
	private  List<Items> find(){
		 
		
		JSONObject res;
		try {
			res = new JSONObject(str1);
			System.out.println("Here is the fetched JSON :: >> \n" + res);
		JSONArray jsonResArr = res.getJSONArray("rows");
		int iJsonResArrSize = jsonResArr.length();
		for (int i=0;i<iJsonResArrSize;i++){
            JSONObject jsonResElem = jsonResArr.getJSONObject(i);
            	String Author = jsonResElem.getJSONObject("fields").getString("Author");
    			String BookName = jsonResElem.getJSONObject("fields").getString("BookName");
    			String Subject = jsonResElem.getJSONObject("fields").getString("Subject");
    			String Rating = ""+jsonResElem.getJSONObject("fields").getInt("Rating");
    			String bookId = ""+jsonResElem.getString("id");
    			result.add(new Items(Author,BookName,Subject,Rating,bookId));
    			
            }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("result="+ result);
		return result;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String newStr=request.getParameter("user");
			searchBy=request.getParameter("searchBy");
			System.out.println("searchBy="+searchBy);
			searchValue=request.getParameter("searchValue");
			System.out.println("searchValue="+searchValue);
			process();
			request.setAttribute("itemList", find());
			request.setAttribute("user", newStr);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Search.jsp");
			requestDispatcher.forward(request, response);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void process() throws JSONException{
		
		String str = null;
		if(!searchValue.isEmpty() || searchValue.trim().length() != 0){
		str = HttpConnection.getProperties().getProperty("CLOUDANT_URL")+"_design/SearchBook/_search/by"+searchBy+"?q="+searchBy+":"+searchValue+"*";
		}
		else if(searchValue.isEmpty() || searchValue.trim().length() == 0){
			str=HttpConnection.getProperties().getProperty("CLOUDANT_URL")+"_design/SearchBook/_search/byAuthor?q=*%3A*";
		}
		HttpConnection obj=new HttpConnection();
		str1=obj.fetchDetails(str);
	
	}
}
