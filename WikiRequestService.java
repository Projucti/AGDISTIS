package org.aksw.agdistis.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import org.json.JSONObject;

public class WikiRequestService{    
public static void main(String[] args){    
try{    
URL url=new URL("https://www.wikidata.org/wiki/Special:EntityData/Q76.json"); 
HttpURLConnection con = (HttpURLConnection)url.openConnection();
con.setRequestMethod("GET");
//con.setRequestProperty("Accept", "application/json");
try(BufferedReader br = new BufferedReader(
		  new InputStreamReader(con.getInputStream()))) {
		    StringBuffer response = new StringBuffer();
		    String responseLine;
		    while ((responseLine = br.readLine()) != null) {
		        response.append(responseLine);
		    }
		    br.close();
		    System.out.println(response.toString());
		  JSONObject obj_json= new JSONObject(response.toString());
			  
		}

    
   
  
}
catch(Exception e){System.out.println(e);}  

}    
}   