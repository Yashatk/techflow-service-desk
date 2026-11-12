package com.techflow.ws.sys.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class Utils {

	/** Zone used to read dates typed by users (APP_TIMEZONE, default America/Sao_Paulo). */
	public static final ZoneId APP_ZONE = ZoneId.of(
			System.getenv().getOrDefault("APP_TIMEZONE", "America/Sao_Paulo"));

	private Utils() {
	}

	/**
	 * Parses a local date-time typed in the UI (no offset) using the application zone.
	 * Returns null for null or empty input.
	 */
	public static Instant parseToInstant(String dateStr, String format) {
		return parseToInstant(dateStr, format, APP_ZONE);
	}

	public static Instant parseToInstant(String dateStr, String format, ZoneId zone) {
		if (dateStr == null || dateStr.isEmpty()) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime ldt = LocalDateTime.parse(dateStr, formatter);
		return ldt.atZone(zone).toInstant();
	}
}
