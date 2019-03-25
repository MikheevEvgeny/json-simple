package org.json.simple.reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Read string into JSONAware
 *
 * @author Evgeny Mikheev, Dmitry Gourjev
 */
public class JSONReader {
	/**
	 * Read string into JSON array
	 *
	 * @param string source string
	 * @param <T>    items object type
	 * @return JSONArray if parsing successed, null  otherwise
	 */
	public static <T> JSONArray<T> readArray(String string) {
		return readArray(string, true);
	}

	/**
	 * Read string into JSON array
	 *
	 * @param string     source string
	 * @param nullOnFail returns null if can't parse
	 * @param <T>        items object type
	 * @return JSONArray if parsing successed, null or empty object (according to nullOnFail flag) otherwise
	 */
	public static <T> JSONArray<T> readArray(String string, boolean nullOnFail) {
		try {
			return (JSONArray<T>) JSONValue.parseWithException(string);
		} catch (ParseException e) {
			if (nullOnFail) {
				return null;
			} else {
				return new JSONArray<>();
			}
		}
	}

	/**
	 * Read string into JSON object
	 *
	 * @param string source string
	 * @param <K>    key object type
	 * @param <V>    value object type
	 * @return JSONObject if parsing successed, null otherwise
	 */
	public static <K, V> JSONObject<K, V> readObject(String string) {
		return readObject(string);
	}

	/**
	 * Read string into JSON object
	 *
	 * @param string     source string
	 * @param nullOnFail returns null if can't parse
	 * @param <K>        key object type
	 * @param <V>        value object type
	 * @return JSONObject if parsing successed, null or empty object (according to nullOnFail flag) otherwise
	 */
	public static <K, V> JSONObject<K, V> readObject(String string, boolean nullOnFail) {
		try {
			return (JSONObject<K, V>) JSONValue.parseWithException(string);
		} catch (ParseException e) {
			if (nullOnFail) {
				return null;
			} else {
				return new JSONObject<>();
			}
		}
	}
}
