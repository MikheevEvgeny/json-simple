package org.json.simple;

import junit.framework.TestCase;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.writer.JSONWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class JSONArrayTest extends TestCase {

	public void testJSONArray() {
		final JSONArray jsonArray = new JSONArray();

		assertEquals("[]", jsonArray.toJSONString());
	}

	public void testJSONArrayCollection() {
		final ArrayList<String> testList = new ArrayList<>();
		testList.add("First item");
		testList.add("Second item");

		final JSONArray<String> jsonArray = new JSONArray<>(testList);

		assertEquals("[\"First item\",\"Second item\"]", jsonArray.toJSONString());
	}

	public void testWriteJSONStringCollectionWriter() throws IOException, ParseException {
		final HashSet<String> testSet = new HashSet<>();
		testSet.add("First item");
		testSet.add("Second item");

		final JSONArray<String> jsonArray = new JSONArray<>(testSet);
		final StringWriter writer = new StringWriter();

		jsonArray.writeJSONString(writer);

		final JSONParser parser = new JSONParser();
		final JSONArray parsedArray = (JSONArray) parser.parse(writer.toString());

		assertTrue(parsedArray.containsAll(jsonArray));
		assertTrue(jsonArray.containsAll(parsedArray));
		assertEquals(2, jsonArray.size());
	}

	public void testToJSONStringCollection() throws ParseException {
		final HashSet<String> testSet = new HashSet<>();
		testSet.add("First item");
		testSet.add("Second item");

		final JSONArray<String> jsonArray = new JSONArray<>(testSet);

		final JSONParser parser = new JSONParser();
		final JSONArray parsedArray = (JSONArray) parser.parse(jsonArray.toJSONString());

		assertTrue(parsedArray.containsAll(jsonArray));
		assertTrue(jsonArray.containsAll(parsedArray));
		assertEquals(2, jsonArray.size());
	}

	public void testByteArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((byte[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new byte[0]));
		assertEquals("[12]", JSONWriter.toJSONString(new byte[]{12}));
		assertEquals("[-7,22,86,-99]", JSONWriter.toJSONString(new byte[]{-7, 22, 86, -99}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((byte[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new byte[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new byte[]{12}, writer);
		assertEquals("[12]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new byte[]{-7, 22, 86, -99}, writer);
		assertEquals("[-7,22,86,-99]", writer.toString());
	}

	public void testShortArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((short[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new short[0]));
		assertEquals("[12]", JSONWriter.toJSONString(new short[]{12}));
		assertEquals("[-7,22,86,-99]", JSONWriter.toJSONString(new short[]{-7, 22, 86, -99}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((short[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new short[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new short[]{12}, writer);
		assertEquals("[12]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new short[]{-7, 22, 86, -99}, writer);
		assertEquals("[-7,22,86,-99]", writer.toString());
	}

	public void testIntArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((int[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new int[0]));
		assertEquals("[12]", JSONWriter.toJSONString(new int[]{12}));
		assertEquals("[-7,22,86,-99]", JSONWriter.toJSONString(new int[]{-7, 22, 86, -99}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((int[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new int[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new int[]{12}, writer);
		assertEquals("[12]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new int[]{-7, 22, 86, -99}, writer);
		assertEquals("[-7,22,86,-99]", writer.toString());
	}

	public void testLongArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((long[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new long[0]));
		assertEquals("[12]", JSONWriter.toJSONString(new long[]{12}));
		assertEquals("[-7,22,9223372036854775807,-99]", JSONWriter.toJSONString(new long[]{-7, 22, 9223372036854775807L, -99}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((long[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new long[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new long[]{12}, writer);
		assertEquals("[12]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new long[]{-7, 22, 86, -99}, writer);
		assertEquals("[-7,22,86,-99]", writer.toString());
	}

	public void testFloatArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((float[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new float[0]));
		assertEquals("[12.8]", JSONWriter.toJSONString(new float[]{12.8f}));
		assertEquals("[-7.1,22.234,86.7,-99.02]", JSONWriter.toJSONString(new float[]{-7.1f, 22.234f, 86.7f, -99.02f}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((float[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new float[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new float[]{12.8f}, writer);
		assertEquals("[12.8]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new float[]{-7.1f, 22.234f, 86.7f, -99.02f}, writer);
		assertEquals("[-7.1,22.234,86.7,-99.02]", writer.toString());
	}

	public void testDoubleArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((double[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new double[0]));
		assertEquals("[12.8]", JSONWriter.toJSONString(new double[]{12.8}));
		assertEquals("[-7.1,22.234,86.7,-99.02]", JSONWriter.toJSONString(new double[]{-7.1, 22.234, 86.7, -99.02}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((double[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new double[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new double[]{12.8}, writer);
		assertEquals("[12.8]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new double[]{-7.1, 22.234, 86.7, -99.02}, writer);
		assertEquals("[-7.1,22.234,86.7,-99.02]", writer.toString());
	}

	public void testBooleanArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((boolean[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new boolean[0]));
		assertEquals("[true]", JSONWriter.toJSONString(new boolean[]{true}));
		assertEquals("[true,false,true]", JSONWriter.toJSONString(new boolean[]{true, false, true}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((boolean[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new boolean[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new boolean[]{true}, writer);
		assertEquals("[true]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new boolean[]{true, false, true}, writer);
		assertEquals("[true,false,true]", writer.toString());
	}

	public void testCharArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((char[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new char[0]));
		assertEquals("[\"a\"]", JSONWriter.toJSONString(new char[]{'a'}));
		assertEquals("[\"a\",\"b\",\"c\"]", JSONWriter.toJSONString(new char[]{'a', 'b', 'c'}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((char[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new char[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new char[]{'a'}, writer);
		assertEquals("[\"a\"]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new char[]{'a', 'b', 'c'}, writer);
		assertEquals("[\"a\",\"b\",\"c\"]", writer.toString());
	}

	public void testObjectArrayToString() throws IOException {
		assertEquals("null", JSONWriter.toJSONString((Object[]) null));
		assertEquals("[]", JSONWriter.toJSONString(new Object[0]));
		assertEquals("[\"Hello\"]", JSONWriter.toJSONString(new Object[]{"Hello"}));
		assertEquals("[\"Hello\",12,[1,2,3]]", JSONWriter.toJSONString(new Object[]{"Hello", 12, new int[]{1, 2, 3}}));

		StringWriter writer;

		writer = new StringWriter();
		JSONWriter.writeJSONString((Object[]) null, writer);
		assertEquals("null", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new Object[0], writer);
		assertEquals("[]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new Object[]{"Hello"}, writer);
		assertEquals("[\"Hello\"]", writer.toString());

		writer = new StringWriter();
		JSONWriter.writeJSONString(new Object[]{"Hello", 12, new int[]{1, 2, 3}}, writer);
		assertEquals("[\"Hello\",12,[1,2,3]]", writer.toString());
	}
}
