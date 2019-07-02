package org.json.simple;

import org.json.simple.reader.JSONReader;
import org.json.simple.writer.JSONWriter;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
 *
 * @author FangYidong
 */
public class JSONObject<K, V> extends HashMap<K, V> implements Map<K, V>, JSONAware, JSONStreamAware, Serializable {

	private static final long serialVersionUID = -503443796854799292L;

	/**
	 * Constructs an empty JSONObject.
	 */
	public JSONObject() {
		super();
	}

	/**
	 * Constructs an empty JSONObject with the specified initial capacity and the default load factor (0.75).
	 *
	 * @param initialCapacity the initial capacity
	 */
	public JSONObject(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Constructs an empty JSONObject with the specified initial capacity and load factor.
	 *
	 * @param initialCapacity the initial capacity
	 * @param loadFactor      the load factor
	 */
	public JSONObject(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Allows creation of a JSONObject from a Map. After that, both the
	 * generated JSONObject and the Map can be modified independently.
	 *
	 * @param map the map whose mappings are to be placed in this map
	 */
	public JSONObject(Map<K, V> map) {
		super(map);
	}


	/**
	 * Constructs a JSONObject from json string. If parse failed, constructs empty object
	 *
	 * @param string source json string
	 */
	public JSONObject(String string) {
		this(JSONReader.readObject(string, false));
	}

	/**
	 * Return value typed as T. If value is null or cast exception return default value
	 *
	 * @param key          key
	 * @param defaultValue default value
	 * @param <T>          result type
	 * @return if exists value otherwise defaultValue
	 */
	@SuppressWarnings("unchecked")
	public <T extends V> T get(K key, T defaultValue) {
		try {
			return (T) super.getOrDefault(key, defaultValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * Try to get value and cast it to String. On any failure returns empty String
	 *
	 * @param key key
	 * @return String value or empty sting on failure
	 */
	public String getString(K key) {
		try {
			return super.get(key) != null ? (String) super.get(key) : "";
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Try to get value and cast it to int. On any failure returns 0
	 *
	 * @param key key
	 * @return int value or 0 on failure
	 */
	public int getInt(K key) {
		try {
			return super.get(key) != null ? (Integer) super.get(key) : 0;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Try to get value and cast it to Integer. On any failure returns 0
	 *
	 * @param key key
	 * @return Integer value or 0 on failure
	 */
	public Integer getInteger(K key) {
		try {
			return super.get(key) != null ? (Integer) super.get(key) : 0;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Try to get value and cast it to Long. On any failure returns 0
	 *
	 * @param key key
	 * @return Long value or 0 on failure
	 */
	public Long getLong(K key) {
		try {
			return super.get(key) != null ? (Long) super.get(key) : 0L;
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * Try to get value and cast it to Float. On any failure returns 0
	 *
	 * @param key key
	 * @return Float value or 0 on failure
	 */
	public Float getFloat(K key) {
		try {
			return super.get(key) != null ? (Float) super.get(key) : 0f;
		} catch (Exception e) {
			return 0f;
		}
	}

	/**
	 * Try to get value and cast it to Double. On any failure returns 0
	 *
	 * @param key key
	 * @return Double value or 0 on failure
	 */
	public Double getDouble(K key) {
		try {
			return super.get(key) != null ? (Double) super.get(key) : 0d;
		} catch (Exception e) {
			return 0d;
		}
	}

	/**
	 * Try to get value and cast it to JSONObject. On any failure empty JSONObject
	 *
	 * @param key key
	 * @return value as JSONObject or empty JSONObject on failure
	 */
	public JSONObject getJSONObject(K key) {
		try {
			return super.get(key) != null ? (JSONObject) super.get(key) : new JSONObject();
		} catch (Exception e) {
			return new JSONObject();
		}
	}

	/**
	 * Try to get value and cast it to JSONArray. On any failure empty JSONArray
	 *
	 * @param key key
	 * @return value as JSONArray or empty JSONArray on failure
	 */
	public JSONArray getJSONArray(K key) {
		try {
			return super.get(key) != null ? (JSONArray) super.get(key) : new JSONArray();
		} catch (Exception e) {
			return new JSONArray();
		}
	}

	public void writeJSONString(Writer out) throws IOException {
		JSONWriter.writeJSONString(this, out);
	}

	public void writeJSONString(Writer out, DateFormat dateFormat) throws IOException {
		JSONWriter.writeJSONString(this, out, dateFormat);
	}

	public String toJSONString() {
		return JSONWriter.toJSONString(this);
	}

	public String toJSONString(DateFormat dateFormat) {
		return JSONWriter.toJSONString(this, dateFormat);
	}

	public String toString() {
		return toJSONString();
	}

	public static String toString(String key, Object value) {
		StringBuffer sb = new StringBuffer();
		sb.append('\"');
		if (key == null)
			sb.append("null");
		else
			JSONValue.escape(key, sb);
		sb.append('\"').append(':');

		sb.append(JSONValue.toJSONString(value));

		return sb.toString();
	}

}
