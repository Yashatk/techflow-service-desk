package com.techflow.ws.sys.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class Utils {

	private Utils() {
	}

	/**
	 * Parses a date-time string in the given pattern, interpreting it as UTC.
	 * Returns null for null or empty input.
	 */
	public static Instant parseToInstant(String dateStr, String format) {
		if (dateStr == null || dateStr.isEmpty()) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime ldt = LocalDateTime.parse(dateStr, formatter);
		return ldt.toInstant(ZoneOffset.UTC);
	}
}
