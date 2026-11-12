package com.techflow.ws.sys.domain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarUtil {
	
	public static class PATTERNS{
		/**
		 * Pattern de data no formato dd/MM/yyyy
		 */
		public static final String SIMPLE_DATE = "dd/MM/yyyy";
		
		/**
		 * Pattern de data no formato dd/MM/yyyy HH:mm:ss
		 */
		public static final String DATE_TIME_24 = "dd/MM/yyyy HH:mm:ss";
		
		/**
		 * Pattern de data no formato dd/MM/yyyy hh:mm:ss
		 */
		public static final String DATE_TIME_AMPM = "dd/MM/yyyy hh:mm:ss";
		
		/** PadrÃ£o utilizado na persistencia de informaÃ§Ãµes **/
		public static final String MACADAME_PERSISTENCE = "yyyyMMddHHmmss";
		
	}

	/** 
	 * Trunca a data hora para somente dia, mes e ano.
	 * @param aDate data a ser truncada.
	 * @return
	 */
	public static Calendar truncateDateToYMD(final Calendar aDate) {
		Calendar truncatedDate = (Calendar) aDate.clone();
		truncatedDate.set(Calendar.HOUR_OF_DAY, 0);
		truncatedDate.set(Calendar.MINUTE, 0);
		truncatedDate.set(Calendar.SECOND, 0);
		truncatedDate.set(Calendar.MILLISECOND, 0);
		return truncatedDate;
	}
	
	public static Calendar truncateDateToLastHoursOfDay(final Calendar aDate){
		Calendar truncatedDate = (Calendar) aDate.clone();
		truncatedDate.set(Calendar.HOUR_OF_DAY, 23);
		truncatedDate.set(Calendar.MINUTE, 59);
		truncatedDate.set(Calendar.SECOND, 59);
		return truncatedDate;		
	}
	
	/** 
	 * Formata um Calendar no padrÃ£o especificado.<br>
	 * <b>Este mÃ©todo utiliza o timeZone default do servidor (NÃ£o utiliza o timeZone do Calendar)</b>
	 * @param aDate data a ser formatada.
	 * @param aPattern padrÃ£o utilizado para formataÃ§Ã£o.
	 * @return
	 */
	public static String formatDate(final Calendar aDate, final String aPattern) {
		SimpleDateFormat format = new SimpleDateFormat(aPattern);
		return format.format(new Date(aDate.getTimeInMillis()));
	}
	
	/** 
	 * Formata um Calendar no padrÃ£o especificado utilizando o timeZone informado
	 * @param aCalendar data a ser formatada.
	 * @param aTimeZone timeZone a ser utilizado na formataÃ§Ã£o
	 * @param aPattern padrÃ£o utilizado para formataÃ§Ã£o.
	 * @return String da data com o Pattern fornecido
	 */
	public static String formatDate(final Calendar aCalendar, final String aPattern, final TimeZone aTimeZone) {
		SimpleDateFormat formatter = new SimpleDateFormat(aPattern);
		formatter.setTimeZone(aTimeZone);
		return formatter.format(new Date(aCalendar.getTimeInMillis()));
	}
	
	public static String formatDate(final Date aDate, final String aPattern) {
		SimpleDateFormat format = new SimpleDateFormat(aPattern);
		return format.format(aDate);
	}

	public static Calendar parseDate(final String aDate, final String aPattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(aPattern);
		Date date = format.parse(aDate);
		Calendar ret = Calendar.getInstance();
		ret.setTime(date);
		return ret;
	}
	
	/**
	 * <p>
	 * Realiza a conversÃ£o de java.util.Date para um java.util.Calendar
	 * </p>
	 * @param aDate
	 * @return
	 */
	public static Calendar toCalendar(final java.util.Date aDate){
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(aDate);
		return calendar;
	}
	
	/**
	 * Converte Unix Time (em UTC) para Calendar no {@link TimeZone} local da VM
	 * @param utcUnixTime
	 * @return
	 */
	public static Calendar fromUTCUnixTime(final long utcUnixTime){
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis((utcUnixTime * 1000L));
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.getTime();
		return calendar;
	}
	
	/**
	 * Converte Calendar para UnixTime (em UTC)
	 * @param calendar
	 * @return
	 */
	/*
	public static long toUTCUnixTime(final Calendar calendar) {
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.getTime();
		return (calendar.getTimeInMillis() / 1000L);
	}
	
	private static final int YEAR_VALUE = 2000;
	public static void main(String[] args) {
		//142240221500000211
		String idTransaction = "142240221500000211"; 
		
		Calendar occurenceDay = Calendar.getInstance();
		int endIndex = idTransaction.length();
		String year = idTransaction.substring(0, 2);
		Integer yearVal = new Integer(year) + YEAR_VALUE;
		Integer dayOfYear = new Integer(idTransaction.substring(2, 5));
		occurenceDay.set(Calendar.DAY_OF_YEAR, dayOfYear);
		occurenceDay.set(Calendar.YEAR, yearVal);
		occurenceDay.set(Calendar.HOUR, 0);
		occurenceDay.set(Calendar.MINUTE, 0);
		occurenceDay.set(Calendar.SECOND, 0);
		occurenceDay.getTime();
		
		System.out.println(occurenceDay.getTime());
	}
	*/
	
   /**
    * Metodo realiza a conversÃ£o do timezone em datas numÃ©ricas em UTC nos seguintes formatos
    * <code> <br> yyyyMMddHHmmss 
    * <br> yyyyMMddHHmm
	* <br> yyyyMMddHH
	* <br> yyyyMMdd
	* </code> <br>
    * SerÃ¡ devolvido uma instÃ¢ncia de Calendar com o timezone adaptado para o GMT local
     * @param Long data numÃ©rica
    * @return Calendar com o TimeZone adaptado
    */
	public static Calendar parseLongToCalendarAdaptingTimeZone(
			final Long numericDate) throws ParseException {
		if (numericDate == null || numericDate.toString().length() < 8) {
			return null;
		}
		
		// completar a data com zeros a direita
		String originalDate = Strings.padEnd(numericDate.toString(), 14, '0');

		// Adquirindo o timezone Local
		TimeZone gmtZone = TimeZone.getTimeZone("GMT");
		SimpleDateFormat formatter = new SimpleDateFormat(
				CalendarUtil.PATTERNS.MACADAME_PERSISTENCE);
		formatter.setTimeZone(gmtZone);
		Date date = formatter.parse(originalDate);
		return CalendarUtil.toCalendar(date);
	}
	
	/**
	 * Converte calendar para data numÃ©rica no formato yyyyMMddHHmmss em UTC
	 * @param aCalendar
	 * @return long no formato yyyyMMddHHmmss em UTC
	 */
	public static Long parseCalendarToLongAdaptingTimeZone(final Calendar aCalendar) {
	  TimeZone gmtZone = TimeZone.getTimeZone("GMT");
    SimpleDateFormat formatter = new SimpleDateFormat(
        CalendarUtil.PATTERNS.MACADAME_PERSISTENCE);
    aCalendar.setTimeZone( gmtZone );
    formatter.setTimeZone( gmtZone );
    return Long.valueOf( formatter.format( Calendar.getInstance() ) );
	}
}