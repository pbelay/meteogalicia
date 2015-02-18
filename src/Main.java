/* --------------------------------------------------------------------
 
Copyright (C) 2015 - Pablo Belay Fernández 
This program is free software: you can redistribute it
and/or modify it under the terms of the GNU General
Public License as published by the Free Software
Foundation, either version 3 of the License, or (at your
option) any later version.
This program is distributed in the hope that it will be
useful, but WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE. See the GNU General Public License
for more details.
You should have received a copy of the GNU General Public
License along with this program. If not, see
http://www.gnu.org/licenses/gpl-3.0-standalone.html
--------------------------------------------------------------------*/


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
/**
 * @author Pablo Belay Fernández - pbelay@gmail.com
 * Fecha: 18/02/2015
 * Licencia: GPL v.3
 * */

public class Main {
	public static void main(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		String inputLine;
		
		//Leemos a url na que se almacenan os datos nun string builder
		try {
            URL google = new URL("http://www.meteogalicia.es/web/predicion/maritima/listLuas.action?data=14/02/2015");
            BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
            while ((inputLine = in.readLine()) != null) {
             	stringBuilder.append(inputLine+"\n");
            }
            in.close(); 
        } catch (MalformedURLException me) {
            System.out.println(me); 
 
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
		//Convertimos a web leida como string nun documento
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
