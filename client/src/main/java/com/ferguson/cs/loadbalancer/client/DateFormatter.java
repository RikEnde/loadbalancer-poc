package com.ferguson.cs.loadbalancer.client;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DateFormatter implements Formatter<Date> {

	DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT
		.withZone(ZoneId.systemDefault());

	@Override
	public Date parse(String text, Locale locale) {
		LocalDateTime localDateTime = LocalDateTime.parse(text, formatter);
		Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
		return Date.from(instant);
	}

	@Override
	public String print(Date date, Locale locale) {
		return formatter.withLocale(locale).format(date.toInstant());
	}
}
