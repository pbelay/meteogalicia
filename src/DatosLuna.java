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
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Pablo Belay Fernández - pbelay@gmail.com
 * Fecha: 18/02/2015
 * Licencia: GPL v.3
 * */
public class DatosLuna {
	
	
	public static void main(String[] args) throws ParseException {
		FileWriter writer 	= null; 		  //Ficheiro no que imos xenerar a saida en CSV
		String iniDateString= "2015/02/17";   //Data de inicio
		int numDias 	  	= 10;   		  //Numero de dias a partir da data de inicio para procesar
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date iniDate 			= format.parse(iniDateString); //Data de inicio para procesar
		try {
			writer = new FileWriter("datosluna.csv");
			for(int i = 1; i<numDias; i++){
				
				Date nextDay = addDays(iniDate, i); //A data de inicio imoslle sumando dias
				String valor = getValor(nextDay);   //Obtemos os datos, %iluminada e fase
  				writer.append(formatDate(nextDay)+";"+valor+"; \n"); //Escribimos a liña de datos no ficheiro de saida
			}
			writer.flush();
			writer.close(); //Pechamos o ficheiro
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getValor(Date myDate){
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.meteogalicia.es/web/predicion/maritima/listLuas.action?data="+formatDate(myDate)).get();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		Elements porcentaxeIluminada = doc.select(".valores");
		Elements faseLua 			 = doc.select(".tituloLua");
 		return faseLua.text()+";"+porcentaxeIluminada.text();
	}
	
	 public static Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days);  
	        return cal.getTime();
	    }
	 
	 public static String formatDate(Date date){
		 SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
		 return dt1.format(date);
	 }

}
