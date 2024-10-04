package xuly;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class FormatTienVaDate {
	public 	DecimalFormat tienF = new DecimalFormat("#0,000 VND");
	public SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
	public 	DecimalFormat khuyenMaiF = new DecimalFormat("#%");
	public String taoTienFormat(Double tien) {	
		return tienF.format(tien);
	}
	public Double huyTienFormat(String tien) {	
		try {
			 Number parsedNumber = tienF.parse(tien);
		       return parsedNumber.doubleValue();
		} catch (ParseException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
	}
	public String taoDateFormat(Date date) {	
		return dateF.format(date);
	}
	  public Date huyDateFormat(String date) {
	        try {
	            // Parse the string to a java.util.Date
	            Date utilDate = dateF.parse(date);
	            
	            // Convert java.util.Date to java.sql.Date
	            return new java.sql.Date(utilDate.getTime());
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	public String taoKhuyenMaiFormat(Double khuyenMai) {
		return khuyenMaiF.format(khuyenMai);
	}
	public Double huyKhuyenMaiFormat(String khuyenMai) {
		Double km;
		try {
			km = (Double) khuyenMaiF.parse(khuyenMai);
			return km/10;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
	}
}
