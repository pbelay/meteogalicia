import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
        try {
            URL google = new URL("http://www.meteogalicia.es/web/predicion/maritima/listLuas.action?data=14/02/2015");
            BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
            String inputLine; 
            
            
            while ((inputLine = in.readLine()) != null) {
                // Process each line.
            	stringBuilder.append(inputLine+"\n");
               // System.out.println(inputLine);
            }
            in.close(); 
 
        } catch (MalformedURLException me) {
            System.out.println(me); 
 
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        Document doc = convertStringToDocument(stringBuilder.toString());
        System.out.println(stringBuilder.toString());
        
    }//end main
	
	private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try 
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
}
