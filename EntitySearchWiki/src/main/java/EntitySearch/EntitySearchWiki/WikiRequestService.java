package EntitySearch.EntitySearchWiki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WikiRequestService{   
//private static final String USER_AGENT = "Mozilla/5.0";
//public static Object finalresult = null;
public static String AGDISTICS_result="[{\"disambiguatedURL\":\"http:\\/\\/www.wikidata.org\\/entity\\/Q76\",\"offset\":7,\"namedEntity\":\"dresden\",\"start\":1}]";
public static URL url;
public static HttpURLConnection con;
public static String unique_identifier;
public static String value;

public static void main(String[] args)throws IOException, InterruptedException{  
	try {
	JSONObject result_json=new JSONObject(AGDISTICS_result.toString().substring(1,AGDISTICS_result.length()-1));
	System.out.println(result_json);
	String url_response=result_json.getString("disambiguatedURL");
	System.out.println(url_response);
    url=new URL(url_response+".json"); 
    System.out.println("Sent URL: ");
    System.out.println(url.toString());
    unique_identifier= url_response.replace("http://www.wikidata.org/entity/","");
    System.out.println(unique_identifier);
    getDescription(url.toString());
    }catch(Exception e){System.out.println(e);}  
}

private static void getDescription(String url2) throws IOException, JSONException {
	// TODO Auto-generated method stub
	/* URL myurl = new URL("https://www.wikidata.org/wiki/Special:EntityData/Q76.json");
		con = (HttpURLConnection)myurl.openConnection();
		con.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(
			  new InputStreamReader(con.getInputStream()));
			    StringBuffer response = new StringBuffer();
			    String responseLine;
			    while ((responseLine = br.readLine()) != null) {
			        response.append(responseLine);
			    }
			    br.close();
			    System.out.println("response: ");
			    //System.out.println(response.toString());
			  JSONObject obj_json= new JSONObject(response.toString());
			  //System.out.println(obj_json);
			  String loudScreaming = obj_json.getJSONObject("entities").getJSONObject("Q76").getJSONObject("descriptions").getJSONObject("en").getString("value");
			  System.out.println(loudScreaming);
			  
	*/

		    try {

			String url = url2;

			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");

			System.out.println("Request URL ... " + url);

			boolean redirect = false;

			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}

			System.out.println("Response Code ... " + status);

			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");

				// get the cookie if need, for login
				String cookies = conn.getHeaderField("Set-Cookie");

				// open the new connnection again
				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestProperty("Cookie", cookies);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");

				System.out.println("Redirect to URL : " + newUrl);

			}

			BufferedReader in = new BufferedReader(
		                              new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();

			System.out.println("URL Content... \n" + html.toString());
			
			JSONObject obj_json= new JSONObject(html.toString());
		    //String loudScreaming = obj_json.getJSONObject("entities").getJSONObject(unique_identifier).getJSONObject("descriptions").getJSONObject("en").getString("value");
		    if(obj_json.getJSONObject("entities")!=null) {
		    	JSONObject entities=obj_json.getJSONObject("entities");
		    	if(entities.getJSONObject(unique_identifier)!=null) {
		    		JSONObject unique_id= entities.getJSONObject(unique_identifier);
		    		if(unique_id.getJSONObject("descriptions")!=null) {
		    			JSONObject description= unique_id.getJSONObject("descriptions");
		    			if(description.has("en")) {
		    				//if(description.getJSONObject("en")!=null) {
			    				JSONObject en_obj=description.getJSONObject("en");
			    				if(en_obj.getString("value")!=null) {
			    					String value= en_obj.getString("value");
			    					System.out.println(value);
			    				}
				    		//}
		    			}
		    			else if(description.has("de")) {
		    			
		    			
		    			//if (description.getJSONObject("de")!=null) {
		    				JSONObject en_obj=description.getJSONObject("de");
		    				if(en_obj.getString("value")!=null) {
		    					String value= en_obj.getString("value");
		    					System.out.println(value);
		    				}
		    			//}
		    			}
		    			else {
		    				System.out.println("Description is not available in English/German");
		    			}
		    			
		    			
		    			
		    		}
		    		
		    	}
		    }

		    } catch (Exception e) {
			e.printStackTrace();
		    }

		  

		    


}
}



	


 