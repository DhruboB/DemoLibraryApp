package example.nosql;

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

import entities.NewItems;

@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
	public static String user;
	public static String BookName;
	public static String BookId;
	public static String Author;
	public static String Subject;
	public static String Rating;
	public static String comment;
	public static int Flag;
	public static String setFlag;
	public static String name;
	public static String id;
	static String str1;
	static String str2;
	static List<NewItems> result = new ArrayList<NewItems>();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookName = request.getParameter("BookName");
		BookId = request.getParameter("BookId");
		user = request.getParameter("user");
		System.out.println("BookName = " + BookName + " and User = " + user);
		try {
			IssueBook.main(null);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.setAttribute("Flag", setFlag);
		request.setAttribute("BookName", BookName);
		request.setAttribute("Author", Author);
		request.setAttribute("Subject", Subject);
		request.setAttribute("Rating", Rating);
		request.setAttribute("BookId", BookId);
		request.setAttribute("itemList", result);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookIssue.jsp");
		requestDispatcher.forward(request, response);

	}

	public static void main(String args[]) throws JSONException, UnsupportedEncodingException {
//		String BookID = URLEncoder.encode(BookId, "UTF-8");
//		System.out.println("Printing the Book ID  to search for .. " + BookId);
//		String rUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL")+ BookID;
//		HttpConnection obj2 = new HttpConnection();
//		str2 = obj2.fetchDetails(rUrl);
//		JSONObject res1 = new JSONObject(str2);
//		JSONArray jsonResArr1 = res1.getJSONArray("rows");
//		if (jsonResArr1.length() == 0) {
//			System.out.println("no details available");
//		} else {
//
//			JSONObject jsonResElem1 = jsonResArr1.getJSONObject(0);
//			id = jsonResElem1.getString("id");
//			System.out.println("The id is" + id);
//		}
		String idDemo = URLEncoder.encode(BookId, "UTF-8");
		String restUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL") + idDemo;
		HttpConnection obj1 = new HttpConnection();
		str1 = obj1.fetchDetails(restUrl);
		JSONObject res = new JSONObject(str1);
		Subject = res.getString("Subject");
		Author = res.getString("Author");
		Rating = "" + res.getInt("Rating");
		Flag = res.getInt("Flag");
		JSONArray jsonRes = res.getJSONArray("Comments");
		int iJsonResArrSize = jsonRes.length();
		for (int i = 0; i < iJsonResArrSize; i++) {
			JSONObject jsonResElements = jsonRes.getJSONObject(i);
			name = jsonResElements.getString("name");
			comment = jsonResElements.getString("comment");
			result.add(new NewItems(name, comment));

		}

		if (Flag == 0) {
			setFlag = "ok";
		} else {
			setFlag = "notok";
		}
	}
}
