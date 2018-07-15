package example.nosql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;




@Path("/authorize")
@Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
public class HttpConnection {
	//private String restUrl ="";
	private String s;
	private static Properties properties;
	@Path("/details")
	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	
	public String fetchDetails(String restUrl) throws JSONException{
		// String str1 ="";
    JSONObject ret = new JSONObject();
		//JSONArray details_JSONArr = new JSONArray();
		try{
			StringBuffer urlData=new StringBuffer();
			HttpURLConnection urlConnection = getConnection(restUrl,"GET");
			BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String resStr;
			while((resStr=reader.readLine()) !=null){
				urlData.append(resStr);
			}
			s=urlData.toString();
			System.out.println(s);
			urlConnection.disconnect();
	}catch(MalformedURLException urlEx){
			urlEx.printStackTrace();
			ret.put("code", "100");
			ret.put("error", urlEx.getMessage());
		}catch(IOException ioEx){
			ioEx.printStackTrace();
			ret.put("code", "100");
			ret.put("error", ioEx.getMessage());
		}catch(Exception ex){

			ex.printStackTrace();
			ret.put("code", "100");
			ret.put("error", ex.getMessage());
		}
		return s;
	}
	
	
	private HttpURLConnection getConnection(String restUrl, String httpMethod) throws IOException{
		
		URL url=new URL(restUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setAllowUserInteraction(true);
		urlConnection.setRequestMethod(httpMethod);
		String userCredentials = getProperties().getProperty("CLOUDANT_USER")+":"+getProperties().getProperty("CLOUDANT_PASS");
		String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
		urlConnection.setRequestProperty ("Authorization", basicAuth);
		urlConnection.setRequestProperty("Content-type", "application/json");
		urlConnection.setRequestProperty("Accept", "application/json");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.connect();
		return urlConnection;
}
	
	public static Properties getProperties() {
		if(properties!= null) {
			return properties;
		}
		properties = new Properties();
		InputStream inStream;
		try {
			inStream = HttpConnection.class.getClassLoader().getResourceAsStream("conf.properties");
			if(inStream!=null) {
			properties.load(inStream );
			}else {
				System.out.println("File not found");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	public static void main(String args[]){
		System.out.println("print Properties " + HttpConnection.getProperties());
	}


	
}