package EntitySearch.EntitySearchWiki;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.*;


public class ServiceWikiAGDISTICS{ 
	public static String SendRequest(String search_entity) {
	        String payload = "text={<entity>"+search_entity+"</entity>.}";

	        StringEntity entity = new StringEntity(payload,
	                ContentType.APPLICATION_FORM_URLENCODED);

	        HttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost request = new HttpPost("http://localhost:8080/AGDISTIS");
	        request.setEntity(entity);

	        HttpResponse response = null;
	        try {
	            response = httpClient.execute(request);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println(response.getStatusLine().getStatusCode());
	        String responseString = new BasicResponseHandler().handleResponse(response);
	        System.out.println(responseString);
	        return responseString;
	       
	}    
	
}
