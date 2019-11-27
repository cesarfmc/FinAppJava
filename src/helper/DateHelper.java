package helper;

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
}
