package helper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateHelper {

	public static Date getDate(LocalDate data) {
		return java.sql.Date.valueOf(data);
	}

	public static LocalDate getLocaltDate(String data) {
		LocalDate date = LocalDate.parse(data);

		return date;
	}
	
	public static String formataData(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data).toString();
	}
}
