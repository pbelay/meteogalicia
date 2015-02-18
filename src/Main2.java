import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Main2 {
	
	
	public static void main(String[] args) throws ParseException {
		FileWriter writer = null;
		int numDias = 900;
 		String sourceDate = "2015/02/17";
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date myDate = format.parse(sourceDate);
		try {
			writer = new FileWriter("lunas.csv");
		
			for(int i = 1; i<numDias; i++){
				
				Date nextDay = addDays(myDate, i);
				String valor = getValor(nextDay);
 				System.out.println(" "+formatDate(nextDay)+" ; "+valor);
 				writer.append(formatDate(nextDay)+";"+valor+"; \n");
			}
			writer.flush();
			writer.close();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements newsHeadlines = doc.select(".valores");
		//System.out.println(newsHeadlines.text());
		return newsHeadlines.text();
	}
	
	 public static Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days); //minus number would decrement the days
	        return cal.getTime();
	    }
	 public static String formatDate(Date date){
		  
		 SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
		 return dt1.format(date);
	 }

}
