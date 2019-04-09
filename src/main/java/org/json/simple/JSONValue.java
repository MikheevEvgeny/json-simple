package org.json.simple;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.writer.JSONWriter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author FangYidong
 */
public class JSONValue {

	private static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru", "RU"));

	/**
	 * Parse JSON text into java object from the input source.
	 * Please use parseWithException() if you don't want to ignore the exception.
	 *
	 * @param in input
	 * @return Instance of the following:
	 * org.json.simple.JSONObject,
	 * org.json.simple.JSONArray,
	 * java.lang.String,
	 * java.lang.Number,
	 * java.lang.Boolean,
	 * null
	 * @see org.json.simple.parser.JSONParser#parse(Reader)
	 * @see #parseWithException(Reader)
	 * @deprecated this method may throw an {@code Error} instead of returning
	 * {@code null}; please use {@link JSONValue#parseWithException(Reader)}
	 * instead
	 */
	public static Object parse(Reader in) {
		try {
			JSONParser parser = new JSONParser();
			return parser.parse(in);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Parse JSON text into java object from the given string.
	 * Please use parseWithException() if you don't want to ignore the exception.
	 *
	 * @param s input string
	 * @return Instance of the following:
	 * org.json.simple.JSONObject,
	 * org.json.simple.JSONArray,
	 * java.lang.String,
	 * java.lang.Number,
	 * java.lang.Boolean,
	 * null
	 * @see org.json.simple.parser.JSONParser#parse(Reader)
	 * @see #parseWithException(Reader)
	 * @deprecated this method may throw an {@code Error} instead of returning
	 * {@code null}; please use {@link JSONValue#parseWithException(String)}
	 * instead
	 */
	public static Object parse(String s) {
		StringReader in = new StringReader(s);
		return parse(in);
	}

	/**
	 * Parse JSON text into java object from the input source.
	 *
	 * @param in input
	 * @return Instance of the following:
	 * org.json.simple.JSONObject,
	 * org.json.simple.JSONArray,
	 * java.lang.String,
	 * java.lang.Number,
	 * java.lang.Boolean,
	 * null
	 * @throws IOException    Something went wrong
	 * @throws ParseException Something went wrong
	 * @see org.json.simple.parser.JSONParser
	 */
	public static Object parseWithException(Reader in) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		return parser.parse(in);
	}

	public static Object parseWithException(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		return parser.parse(s);
	}

	/**
	 * Encode an object into JSON text and write it to out.
	 * <p>
	 * If this object is a Map or a List, and it's also a JSONStreamAware or a JSONAware, JSONStreamAware or JSONAware will be considered firstly.
	 * <p>
	 * DO NOT call this method from writeJSONString(Writer) of a class that implements both JSONStreamAware and (Map or List) with
	 * "this" as the first parameter, use JSONObject.writeJSONString(Map, Writer) or JSONArray.writeJSONString(List, Writer) instead.
	 *
	 * @param value      source value
	 * @param out        write result to
	 * @param dateFormat format for converting dates to string
	 * @throws IOException write errors
	 * @see org.json.simple.writer.JSONWriter#writeJSONString(Map, Writer, DateFormat)
	 * @see org.json.simple.writer.JSONWriter#writeJSONString(Collection, Writer)
	 */
	public static void writeJSONString(Object value, Writer out, DateFormat dateFormat) throws IOException {
		if (value == null) {
			out.write("null");
			return;
		}

		if (value instanceof String) {
			out.write('\"');
			out.write(escape((String) value));
			out.write('\"');
			return;
		}

		if (value instanceof Date) {
			out.write('\"');
			out.write(escape(dateFormat != null ? dateFormat.format(value) : DEFAULT_DATE_FORMAT.format(value)));
			out.write('\"');
			return;
		}

		if (value instanceof Double) {
			if (((Double) value).isInfinite() || ((Double) value).isNaN())
				out.write("null");
			else
				out.write(value.toString());
			return;
		}

		if (value instanceof Float) {
			if (((Float) value).isInfinite() || ((Float) value).isNaN())
				out.write("null");
			else
				out.write(value.toString());
			return;
		}

		if (value instanceof Number) {
			out.write(value.toString());
			return;
		}

		if (value instanceof Boolean) {
			out.write(value.toString());
			return;
		}

		if ((value instanceof JSONStreamAware)) {
			((JSONStreamAware) value).writeJSONString(out);
			return;
		}

		if ((value instanceof JSONAware)) {
			out.write(((JSONAware) value).toJSONString());
			return;
		}

		if (value instanceof Map) {
			JSONWriter.writeJSONString((Map) value, out, dateFormat);
			return;
		}

		if (value instanceof Collection) {
			JSONWriter.writeJSONString((Collection) value, out);
			return;
		}

		if (value instanceof byte[]) {
			JSONWriter.writeJSONString((byte[]) value, out);
			return;
		}

		if (value instanceof short[]) {
			JSONWriter.writeJSONString((short[]) value, out);
			return;
		}

		if (value instanceof int[]) {
			JSONWriter.writeJSONString((int[]) value, out);
			return;
		}

		if (value instanceof long[]) {
			JSONWriter.writeJSONString((long[]) value, out);
			return;
		}

		if (value instanceof float[]) {
			JSONWriter.writeJSONString((float[]) value, out);
			return;
		}

		if (value instanceof double[]) {
			JSONWriter.writeJSONString((double[]) value, out);
			return;
		}

		if (value instanceof boolean[]) {
			JSONWriter.writeJSONString((boolean[]) value, out);
			return;
		}

		if (value instanceof char[]) {
			JSONWriter.writeJSONString((char[]) value, out);
			return;
		}

		if (value instanceof Object[]) {
			JSONWriter.writeJSONString((Object[]) value, out);
			return;
		}

		out.write(value.toString());
	}


	public static void writeJSONString(Object value, Writer out) throws IOException {
		writeJSONString(value, out, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Convert an object to JSON text.
	 * <p>
	 * If this object is a Map or a List, and it's also a JSONAware, JSONAware will be considered firstly.
	 * <p>
	 * DO NOT call this method from toJSONString() of a class that implements both JSONAware and Map or List with
	 * "this" as the parameter, use JSONObject.toJSONString(Map) or JSONArray.toJSONString(List) instead.
	 *
	 * @param value source value
	 * @return JSON text, or "null" if value is null or it's an NaN or an INF number.
	 * @see org.json.simple.writer.JSONWriter#toJSONString(Map)
	 * @see org.json.simple.writer.JSONWriter#toJSONString(Collection)
	 */
	public static String toJSONString(Object value) {
		return toJSONString(value, DEFAULT_DATE_FORMAT);
	}

	public static String toJSONString(Object value, DateFormat dateFormat) {
		final StringWriter writer = new StringWriter();

		try {
			writeJSONString(value, writer, dateFormat);
			return writer.toString();
		} catch (IOException e) {
			// This should never happen for a StringWriter
			throw new RuntimeException(e);
		}
	}

	/**
	 * Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters (U+0000 through U+001F).
	 *
	 * @param s source string
	 * @return escaped string
	 */
	public static String escape(String s) {
		if (s == null)
			return null;
		StringBuffer sb = new StringBuffer();
		escape(s, sb);
		return sb.toString();
	}

	/**
	 * Escaping input and appending to output
	 *
	 * @param s  input string
	 * @param sb output buffer
	 */
	static void escape(String s, StringBuffer sb) {
		final int len = s.length();
		for (int i = 0; i < len; i++) {
			char ch = s.charAt(i);
			switch (ch) {
				case '"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '/':
					sb.append("\\/");
					break;
				default:
					//Reference: http://www.unicode.org/versions/Unicode5.1.0/
					if ((ch <= '\u001F') || (ch >= '\u007F' && ch <= '\u009F') || (ch >= '\u2000' && ch <= '\u20FF')) {
						String ss = Integer.toHexString(ch);
						sb.append("\\u");
						for (int k = 0; k < 4 - ss.length(); k++) {
							sb.append('0');
						}
						sb.append(ss.toUpperCase());
					} else {
						sb.append(ch);
					}
			}
		}//for
	}

	public static void setDefaultDateFormat(DateFormat defaultDateFormat) {
		DEFAULT_DATE_FORMAT = defaultDateFormat;
	}

	public static DateFormat getDefaultDateFormat() {
		return DEFAULT_DATE_FORMAT;
	}
}
