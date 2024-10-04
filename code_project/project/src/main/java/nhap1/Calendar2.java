// Program to demonstrate get() method 
// of Calendar class 
package nhap1;


import java.util.*; 
public class Calendar2 { 
	public static void main(String[] args) 
	{ 
		// creating Calendar object 
		Calendar calendar = Calendar.getInstance(); 
		// Demonstrate Calendar's get()method 
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int daysToSubtract = (dayOfWeek == Calendar.SUNDAY) ? 6 : (dayOfWeek - Calendar.MONDAY);
		calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
		ArrayList<Date> arr= new ArrayList<Date>();
		for (int i = 1; i <= 7; i++) {
			
			arr.add( (Date) calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH,1);
		}
		for (Date date : arr) {
			System.out.println(date);
		}
	} 
} 
