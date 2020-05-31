package EntitySearch.EntitySearchWiki;

import java.io.IOException;

public class ServiceWikiAGDISTICS{ 
	public static void main(String[] args)throws IOException, InterruptedException{ 
	
		Runtime.getRuntime().exec("curl --data-urlencode \"text='<entity>Barack Obama</entity>.'\" -d type='agdistis' http://localhost:8080/AGDISTIS");
	}
}
