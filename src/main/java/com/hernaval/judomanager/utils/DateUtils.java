package com.hernaval.judomanager.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static LocalDate fromStr(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
	}
}
