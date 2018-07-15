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

import entities.Books;

@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private String user;
	private String bookId;
	private String id;
	private String BookName;
	private List<Books> result = new ArrayList<Books>();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user = request.getParameter("user");
		bookId = request.getParameter("bookId");
		request.setAttribute("user", user);
		request.setAttribute("bookId", bookId);
		try {
			process();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setAttribute("itemList", result);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("MyProfile.jsp");
		requestDispatcher.forward(request, response);
	}

	public void process() throws JSONException, UnsupportedEncodingException {

		String uname = URLEncoder.encode(user, "UTF-8");
		String rUrl = HttpConnection.getProperties().getProperty("CLOUDANT_URL")+"_design/User/_search/byUserPass?q=username:@"
				+ uname;
		HttpConnection obj2 = new HttpConnection();
		String str2 = obj2.fetchDetails(rUrl);
		JSONObject res1 = new JSONObject(str2);
		JSONArray jsonResArr1 = res1.getJSONArray("rows");
		if (jsonResArr1.length() == 0) {
			System.out.println("no details available");
		} else {

			JSONObject jsonResElem1 = jsonResArr1.getJSONObject(0);
			id = jsonResElem1.getString("id");
			System.out.println("The id is" + id);
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
			if (jsonResElements.has("BookId")) {
				BookName = jsonResElements.getString("BookName");
				bookId = jsonResElements.getString("BookId");
				result.add(new Books(BookName,bookId));
			}

		}

	}
}