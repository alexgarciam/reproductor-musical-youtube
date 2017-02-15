package es.alex.futvre.tests;

import java.util.Calendar;
import java.util.Date;

import es.alex.futvre.utils.Utils;

public class TestUtils {

	public static void main (String[] args){
		
		Date date=Utils.parseDate("23/01/2013 13:25:02", "dd/mm/yyyy hh:mm:ss");
		System.out.println("date: "+date);
		Date date2=Utils.increaseDate(date, Calendar.DAY_OF_MONTH, 1);
		System.out.println("date2: "+date2);
		
		Date actualdate=new Date();
		
		if(actualdate.after(date2)){
			System.out.println("date 1 es posterior a date2");
		}else{
			System.out.println("date 2 es posterior a date1");
		}
	}
}
