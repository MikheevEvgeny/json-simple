package org.json.simple.writer;

import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Convert JSONAware to string
 *
 * @author Evgeny Mikheev
 */
public class JSONWriter {
	/**
	 * Convert a list to JSON text. The result is a JSON array.
	 * If this list is also a JSONAware, JSONAware specific behaviours will be omitted at this top level.
	 *
	 * @param collection source collection
	 * @return JSON text, or "null" if list is null.
	 * @see org.json.simple.JSONValue#toJSONString(Object, DateFormat)
	 */
	public static String toJSONString(Collection collection) {
		return toJSONString(collection, null);
	}


	/**
	 * Convert a list to JSON text. The result is a JSON array.
	 * If this list is also a JSONAware, JSONAware specific behaviours will be omitted at this top level.
	 *
	 * @param collection source collection
	 * @param dateFormat format for converting dates to string
	 * @return JSON text, or "null" if list is null.
	 * @see org.json.simple.JSONValue#toJSONString(Object, DateFormat)
	 */
	public static String toJSONString(Collection collection, DateFormat dateFormat) {
		final StringWriter writer = new StringWriter();

		try {
			writeJSONString(collection, writer, dateFormat);
			return writer.toString();
		} catch (IOException e) {
			// This should never happen for a StringWriter
			throw new RuntimeException(e);
		}
	}

	/**
	 * Convert a map to JSON text. The result is a JSON object.
	 * If this map is also a JSONAware, JSONAware specific behaviours will be omitted at this top level.
	 *
	 * @param map source map
	 * @return JSON text, or "null" if map is null.
	 * @see org.json.simple.JSONValue#toJSONString(Object, DateFormat)
	 */
	public static String toJSONString(Map map) {
		return toJSONString(map, null);
	}

	/**
	 * Convert a map to JSON text. The result is a JSON object.
	 * If this map is also a JSONAware, JSONAware specific behaviours will be omitted at this top level.
	 *
	 * @param map        source map
	 * @param dateFormat format for converting dates to string
	 * @return JSON text, or "null" if map is null.
	 * @see org.json.simple.JSONValue#toJSONString(Object, DateFormat)
	 */
	public static String toJSONString(Map map, DateFormat dateFormat) {
		final StringWriter writer = new StringWriter();

		try {
			writeJSONString(map, writer, dateFormat);
			return writer.toString();
		} catch (IOException e) {
			// This should never happen with a StringWriter
			throw new RuntimeException(e);
		}
	}

	public static String toJSONString(Object[] array) {
		final StringWriter writer = new StringWriter();

		try {
			writeJSONString(array, writer);
			return writer.toString();
		} catch (IOException e) {
			// This should never happen for a StringWriter
			throw new RuntimeException(e);
		}
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(byte[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(short[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(int[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(long[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(float[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(double[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(boolean[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	/**
	 * Convert array to JSON array string
	 *
	 * @param array source array
	 * @return JSON array string
	 */
	public static String toJSONString(char[] array) {
		final StringWriter writer = new StringWriter();
		try {
			writeJSONString(array, writer);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return writer.toString();
	}

	public static void writeJSONString(Object[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			JSONValue.writeJSONString(array[0], out);

			for (int i = 1; i < array.length; i++) {
				out.write(",");
				JSONValue.writeJSONString(array[i], out);
			}

			out.write("]");
		}
	}

	/**
	 * Encode a list into JSON text and write it to out.
	 * If this list is also a JSONStreamAware or a JSONAware, JSONStreamAware and JSONAware specific behaviours will be ignored at this top level.
	 *
	 * @param collection source collection
	 * @param out        output writer
	 * @see org.json.simple.JSONValue#writeJSONString(Object, Writer, DateFormat)
	 */
	public static <T> void writeJSONString(Collection<T> collection, Writer out) throws IOException {
		writeJSONString(collection, out, null);
	}

	/**
	 * Encode a list into JSON text and write it to out.
	 * If this list is also a JSONStreamAware or a JSONAware, JSONStreamAware and JSONAware specific behaviours will be ignored at this top level.
	 *
	 * @param collection source collection
	 * @param out        output writer
	 * @param dateFormat format for converting dates to string
	 * @see org.json.simple.JSONValue#writeJSONString(Object, Writer, DateFormat)
	 */
	public static <T> void writeJSONString(Collection<T> collection, Writer out, DateFormat dateFormat) throws IOException {
		if (collection == null) {
			out.write("null");
			return;
		}

		boolean first = true;
		Iterator iter = collection.iterator();

		out.write('[');
		while (iter.hasNext()) {
			if (first)
				first = false;
			else
				out.write(',');

			Object value = iter.next();
			if (value == null) {
				out.write("null");
				continue;
			}

			JSONValue.writeJSONString(value, out, dateFormat);
		}
		out.write(']');
	}


	/**
	 * Encode a map into JSON text and write it to out.
	 * If this map is also a JSONAware or JSONStreamAware, JSONAware or JSONStreamAware specific behaviours will be ignored at this top level.
	 *
	 * @param map source map
	 * @param out output writer
	 * @see org.json.simple.JSONValue#writeJSONString(Object, Writer, DateFormat)
	 */
	public static <K, V> void writeJSONString(Map<K, V> map, Writer out) throws IOException {
		writeJSONString(map, out, null);
	}


	/**
	 * Encode a map into JSON text and write it to out.
	 * If this map is also a JSONAware or JSONStreamAware, JSONAware or JSONStreamAware specific behaviours will be ignored at this top level.
	 *
	 * @param map        source map
	 * @param out        output writer
	 * @param dateFormat format for converting dates to string
	 * @see org.json.simple.JSONValue#writeJSONString(Object, Writer, DateFormat)
	 */
	public static <K, V> void writeJSONString(Map<K, V> map, Writer out, DateFormat dateFormat) throws IOException {
		if (map == null) {
			out.write("null");
			return;
		}

		boolean first = true;
		Iterator iter = map.entrySet().iterator();

		out.write('{');
		while (iter.hasNext()) {
			if (first)
				first = false;
			else
				out.write(',');
			Map.Entry entry = (Map.Entry) iter.next();
			out.write('\"');
			out.write(JSONValue.escape(String.valueOf(entry.getKey())));
			out.write('\"');
			out.write(':');
			JSONValue.writeJSONString(entry.getValue(), out, dateFormat);
		}
		out.write('}');
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(byte[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write(String.valueOf(array[0]));
			for (int i = 1; i < array.length; i++) {
				out.write("," + array[i]);
			}
			out.write("]");
		}
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(short[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write(String.valueOf(array[0]));
			for (int i = 1; i < array.length; i++) {
				out.write("," + array[i]);
			}
			out.write("]");
		}
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(int[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write(String.valueOf(array[0]));
			for (int i = 1; i < array.length; i++) {
				out.write("," + array[i]);
			}
			out.write("]");
		}
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(long[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write(String.valueOf(array[0]));
			for (int i = 1; i < array.length; i++) {
				out.write("," + array[i]);
			}
			out.write("]");
		}
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(float[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write(String.valueOf(array[0]));
			for (int i = 1; i < array.length; i++) {
				out.write("," + array[i]);
			}
			out.write("]");
		}
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(double[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write(String.valueOf(array[0]));
			for (int i = 1; i < array.length; i++) {
				out.write("," + array[i]);
			}
			out.write("]");
		}
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(boolean[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write(String.valueOf(array[0]));
			for (int i = 1; i < array.length; i++) {
				out.write("," + array[i]);
			}
			out.write("]");
		}
	}

	/**
	 * Convert array to JSON array string and write it to out
	 *
	 * @param array source array
	 * @param out   output
	 * @throws IOException write errors
	 */
	public static void writeJSONString(char[] array, Writer out) throws IOException {
		if (array == null) {
			out.write("null");
		} else if (array.length == 0) {
			out.write("[]");
		} else {
			out.write("[");
			out.write("\"" + array[0] + "\"");
			for (int i = 1; i < array.length; i++) {
				out.write(",\"" + array[i] + "\"");
			}
			out.write("]");
		}
	}
}
