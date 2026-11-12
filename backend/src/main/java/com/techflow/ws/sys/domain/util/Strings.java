package com.techflow.ws.sys.domain.util;


public final class Strings {
	private Strings() {
	}

	/**
	 * Returns the given string if it is non-null; the empty string otherwise.
	 * 
	 * @param string
	 *            the string to test and possibly return
	 * @return {@code string} itself if it is non-null; {@code ""} if it is null
	 */
	public static String nullToEmpty(String string) {
		return (string == null) ? "" : string;
	}

	/**
	 * Returns the given string if it is nonempty; {@code null} otherwise.
	 * 
	 * @param string
	 *            the string to test and possibly return
	 * @return {@code string} itself if it is nonempty; {@code null} if it is
	 *         empty or null
	 */
	public static 
	String emptyToNull( String string) {
		return isNullOrEmpty(string) ? null : string;
	}

	/**
	 * Returns {@code true} if the given string is null or is the empty string.
	 * 
	 * <p>
	 * Consider normalizing your string references with {@link #nullToEmpty}. If
	 * you do, you can use {@link String#isEmpty()} instead of this method, and
	 * you won't need special null-safe forms of methods like
	 * {@link String#toUpperCase} either. Or, if you'd like to normalize "in the
	 * other direction," converting empty strings to {@code null}, you can use
	 * {@link #emptyToNull}.
	 * 
	 * @param string
	 *            a string reference to check
	 * @return {@code true} if the string is null or is the empty string
	 */
	public static boolean isNullOrEmpty( String string) {
		return string == null || string.isEmpty(); 
	}

	/**
	 * Returns a string, of length at least {@code minLength}, consisting of
	 * {@code string} prepended with as many copies of {@code padChar} as are
	 * necessary to reach that length. For example,
	 * 
	 * <ul>
	 * <li>{@code padStart("7", 3, '0')} returns {@code "007"}
	 * <li>{@code padStart("2010", 3, '0')} returns {@code "2010"}
	 * </ul>
	 * 
	 * <p>
	 * See {@link Formatter} for a richer set of formatting capabilities.
	 * 
	 * @param string
	 *            the string which should appear at the end of the result
	 * @param minLength
	 *            the minimum length the resulting string must have. Can be zero
	 *            or negative, in which case the input string is always
	 *            returned.
	 * @param padChar
	 *            the character to insert at the beginning of the result until
	 *            the minimum length is reached
	 * @return the padded string
	 */
	public static String padStart(String string, int minLength, char padChar) {
		checkNotNull(string); // eager for GWT.
		if (string.length() >= minLength) {
			return string;
		}
		StringBuilder sb = new StringBuilder(minLength);
		for (int i = string.length(); i < minLength; i++) {
			sb.append(padChar);
		}
		sb.append(string);
		return sb.toString();
	}

	/**
	 * Returns a string, of length at least {@code minLength}, consisting of
	 * {@code string} appended with as many copies of {@code padChar} as are
	 * necessary to reach that length. For example,
	 * 
	 * <ul>
	 * <li>{@code padEnd("4.", 5, '0')} returns {@code "4.000"}
	 * <li>{@code padEnd("2010", 3, '!')} returns {@code "2010"}
	 * </ul>
	 * 
	 * <p>
	 * See {@link Formatter} for a richer set of formatting capabilities.
	 * 
	 * @param string
	 *            the string which should appear at the beginning of the result
	 * @param minLength
	 *            the minimum length the resulting string must have. Can be zero
	 *            or negative, in which case the input string is always
	 *            returned.
	 * @param padChar
	 *            the character to append to the end of the result until the
	 *            minimum length is reached
	 * @return the padded string
	 */
	public static String padEnd(String string, int minLength, char padChar) {
		checkNotNull(string); // eager for GWT.
		if (string.length() >= minLength) {
			return string;
		}
		StringBuilder sb = new StringBuilder(minLength);
		sb.append(string);
		for (int i = string.length(); i < minLength; i++) {
			sb.append(padChar);
		}
		return sb.toString();
	}

	/**
	 * Returns a string consisting of a specific number of concatenated copies
	 * of an input string. For example, {@code repeat("hey", 3)} returns the
	 * string {@code "heyheyhey"}.
	 * 
	 * @param string
	 *            any non-null string
	 * @param count
	 *            the number of times to repeat it; a nonnegative integer
	 * @return a string containing {@code string} repeated {@code count} times
	 *         (the empty string if {@code count} is zero)
	 * @throws IllegalArgumentException
	 *             if {@code count} is negative
	 */
	public static String repeat(String string, int count) {
		checkNotNull(string); // eager for GWT.

		if (count <= 1) {
			checkArgument(count >= 0, "invalid count: %s", count);
			return (count == 0) ? "" : string;
		}

		// IF YOU MODIFY THE CODE HERE, you must update StringsRepeatBenchmark
		final int len = string.length();
		final long longSize = (long) len * (long) count;
		final int size = (int) longSize;
		if (size != longSize) {
			throw new ArrayIndexOutOfBoundsException(
					"Required array size too large: "
							+ String.valueOf(longSize));
		}

		final char[] array = new char[size];
		string.getChars(0, len, array, 0);
		int n;
		for (n = len; n < size - n; n <<= 1) {
			System.arraycopy(array, 0, array, n, n);
		}
		System.arraycopy(array, 0, array, n, size - n);
		return new String(array);
	}

	/**
	 * Returns the longest string {@code prefix} such that
	 * {@code a.toString().startsWith(prefix) && b.toString().startsWith(prefix)}
	 * , taking care not to split surrogate pairs. If {@code a} and {@code b}
	 * have no common prefix, returns the empty string.
	 * 
	 * @since 11.0
	 */
	public static String commonPrefix(CharSequence a, CharSequence b) {
		checkNotNull(a);
		checkNotNull(b);

		int maxPrefixLength = Math.min(a.length(), b.length());
		int p = 0;
		while (p < maxPrefixLength && a.charAt(p) == b.charAt(p)) {
			p++;
		}
		if (validSurrogatePairAt(a, p - 1) || validSurrogatePairAt(b, p - 1)) {
			p--;
		}
		return a.subSequence(0, p).toString();
	}

	/**
	 * Returns the longest string {@code suffix} such that
	 * {@code a.toString().endsWith(suffix) && b.toString().endsWith(suffix)},
	 * taking care not to split surrogate pairs. If {@code a} and {@code b} have
	 * no common suffix, returns the empty string.
	 * 
	 * @since 11.0
	 */
	public static String commonSuffix(CharSequence a, CharSequence b) {
		checkNotNull(a);
		checkNotNull(b);

		int maxSuffixLength = Math.min(a.length(), b.length());
		int s = 0;
		while (s < maxSuffixLength
				&& a.charAt(a.length() - s - 1) == b.charAt(b.length() - s - 1)) {
			s++;
		}
		if (validSurrogatePairAt(a, a.length() - s - 1)
				|| validSurrogatePairAt(b, b.length() - s - 1)) {
			s--;
		}
		return a.subSequence(a.length() - s, a.length()).toString();
	}

	/**
	 * True when a valid surrogate pair starts at the given {@code index} in the
	 * given {@code string}. Out-of-range indexes return false.
	 */
	static boolean validSurrogatePairAt(CharSequence string, int index) {
		return index >= 0 && index <= (string.length() - 2)
				&& Character.isHighSurrogate(string.charAt(index))
				&& Character.isLowSurrogate(string.charAt(index + 1));
	}

	/**
	 * Ensures that an object reference passed as a parameter to the calling
	 * method is not null.
	 * 
	 * @param reference
	 *            an object reference
	 * @return the non-null reference that was validated
	 * @throws NullPointerException
	 *             if {@code reference} is null
	 */
	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}

	/**
	 * Ensures the truth of an expression involving one or more parameters to
	 * the calling method.
	 * 
	 * @param expression
	 *            a boolean expression
	 * @throws IllegalArgumentException
	 *             if {@code expression} is false
	 */
	public static void checkArgument(boolean expression) {
		if (!expression) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Ensures the truth of an expression involving one or more parameters to
	 * the calling method.
	 * 
	 * @param expression
	 *            a boolean expression
	 * @param errorMessage
	 *            the exception message to use if the check fails; will be
	 *            converted to a string using {@link String#valueOf(Object)}
	 * @throws IllegalArgumentException
	 *             if {@code expression} is false
	 */
	public static void checkArgument(boolean expression,
			 Object errorMessage) {
		if (!expression) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
	}

	/**
	 * Ensures the truth of an expression involving one or more parameters to
	 * the calling method.
	 * 
	 * @param expression
	 *            a boolean expression
	 * @param errorMessageTemplate
	 *            a template for the exception message should the check fail.
	 *            The message is formed by replacing each {@code %s} placeholder
	 *            in the template with an argument. These are matched by
	 *            position - the first {@code %s} gets
	 *            {@code errorMessageArgs[0]}, etc. Unmatched arguments will be
	 *            appended to the formatted message in square braces. Unmatched
	 *            placeholders will be left as-is.
	 * @param errorMessageArgs
	 *            the arguments to be substituted into the message template.
	 *            Arguments are converted to strings using
	 *            {@link String#valueOf(Object)}.
	 * @throws IllegalArgumentException
	 *             if {@code expression} is false
	 * @throws NullPointerException
	 *             if the check fails and either {@code errorMessageTemplate} or
	 *             {@code errorMessageArgs} is null (don't let this happen)
	 */
	public static void checkArgument(boolean expression,
			 String errorMessageTemplate,
			 Object... errorMessageArgs) {
		if (!expression) {
			throw new IllegalArgumentException(format(errorMessageTemplate,
					errorMessageArgs));
		}
	}

	/**
	 * Substitutes each {@code %s} in {@code template} with an argument. These
	 * are matched by position - the first {@code %s} gets {@code args[0]}, etc.
	 * If there are more arguments than placeholders, the unmatched arguments
	 * will be appended to the end of the formatted message in square braces.
	 * 
	 * @param template
	 *            a non-null string containing 0 or more {@code %s}
	 *            placeholders.
	 * @param args
	 *            the arguments to be substituted into the message template.
	 *            Arguments are converted to strings using
	 *            {@link String#valueOf(Object)}. Arguments can be null.
	 */
	static String format(String template,  Object... args) {
		template = String.valueOf(template); // null -> "null"

		// start substituting the arguments into the '%s' placeholders
		StringBuilder builder = new StringBuilder(template.length() + 16
				* args.length);
		int templateStart = 0;
		int i = 0;
		while (i < args.length) {
			int placeholderStart = template.indexOf("%s", templateStart);
			if (placeholderStart == -1) {
				break;
			}
			builder.append(template.substring(templateStart, placeholderStart));
			builder.append(args[i++]);
			templateStart = placeholderStart + 2;
		}
		builder.append(template.substring(templateStart));

		// if we run out of placeholders, append the extra args in square braces
		if (i < args.length) {
			builder.append(" [");
			builder.append(args[i++]);
			while (i < args.length) {
				builder.append(", ");
				builder.append(args[i++]);
			}
			builder.append(']');
		}

		return builder.toString();
	}
	
	
	/**
	 * Removes leading zeros on the left side of the String. <br>
	 * If the String does not have any zero, does not remove anything. <br>
	 * If a String has only zeros, will return an empty String. <br>
	 * @param input
	 * @return
	 */
	public static String removeZeros(String input) {
		int i = 0;
		while ( i< input.length() && input.charAt(i) == '0') {
			i++;
		}
		return input.substring(i);
	}
	/**
	 * 
	 * Verify if the string is a number
	 * @param input
	 * @return true - when the input is a integer number <br> 
	 * false - when is not a number
	 * 
	 */
	public static boolean isANumber(String input){
		char[] characteres = input.toCharArray();
		for (char character : characteres) {
			if( !Character.isDigit(character) )
				return false;
		}
		return true;
	}

	/**
	 * Obtem o valor de uma determinada posiÃ§Ã£o considerando o delimitador sugerido e a
	 * posiÃ§Ã£o que se deseja obter (indice da coluna)
	 * @param aString - String que serÃ¡ obtida a coluna
	 * @param delimiter - O delimitador de colunas da string
	 * @param columnIndex - O indice da coluna que se deseja obter
	 * @return String representando o valor da coluna solicitado
	 */
	public static String getColumn(final String aString, final char delimiter, final int columnIndex){
		int beginIndex = -1;
		int endIndex = -1;
		int count = -1;
		for (int i = 0; i < aString.length(); i++) {
			if(i == 0 && columnIndex == 0){
				beginIndex = i;
			}
			if(aString.charAt(i)==delimiter){
				count++;
				if(count>=0 && beginIndex >=0){
					endIndex = i;
					break;
				} else if((columnIndex==0 && count==columnIndex) || (columnIndex>=0 && columnIndex==count+1)){
					beginIndex = i+1;
				}
			}
		}
		if(endIndex<0){
			endIndex = aString.length();
		}
		return aString.substring(beginIndex, endIndex);
	}
	
	/** 
	 * Abrevia a String com a quantidade de caracter informado
	 */
	public static String abbreviate(final String str, int len) {
		if(len<=0){
			return str;
		}
		if(str.length()<=len){
			return str;
		}
		return str.substring(0,len);
	}
	
	/**
	 * Facilita concatenaÃ§Ã£o de Strings utilizando {@link StringBuilder} <br>
	 * Favor utilizar este mÃ©todo e evitar concatenar strings com {@link String#concat(String)} 
	 * ou usando '+' (sinal de mais) <br>
	 * Concatenar usando {@link StringBuilder} ou este mÃ©todo
	 * @param args
	 * @return
	 */
	public static String concat(Object...args) {
		StringBuilder builder = new StringBuilder();
		for (Object object : args) {
			builder.append(object);
		}
		return builder.toString();
	}
}
