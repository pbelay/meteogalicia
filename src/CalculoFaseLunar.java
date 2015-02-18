import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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


/**
 * @author Pablo Belay Fernández - pbelay@gmail.com
 * Fecha: 18/02/2015
 * Licencia: GPL v.3
 * */
public class CalculoFaseLunar {
	//Fonte do Algoritmo http://mizar.blogalia.com/historias/21732
	//https://groups.google.com/forum/#!topic/es.comp.lenguajes.delphi/bJU55MWCwu8
	public static int calculeFaseLunar(Date fecha){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer ano = cal.get(Calendar.YEAR);
		Integer mes = cal.get(Calendar.MONTH);
		Integer dia = cal.get(Calendar.DAY_OF_MONTH);
		char[] numerosAno = ano.toString().toCharArray();
		int sumCifrasAno  = 0;
		int resultado 	  = 0;
		
		for (int i=0; i<numerosAno.length; i++){
			sumCifrasAno +=  numerosAno[i];
		}
		resultado = (11*sumCifrasAno+mes+dia)%30;
		return 0;
	}
	
	public static void main(String[] args) {
		calculeFaseLunar(new Date());
	}
}
