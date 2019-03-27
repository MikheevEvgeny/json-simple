package org.json.simple.reader;

import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.util.Date;

public class JSONReaderTest extends TestCase {
	public void testRead() {
		final JSONObject<String, Object> jsonObject0 = new JSONObject<>();
		final JSONObject<String, Object> jsonObject1 = new JSONObject<>();
		final JSONObject<String, Object> jsonObject2 = new JSONObject<>();


		jsonObject1.put("testStr", "test");
		jsonObject1.put("testDate", new Date());

		jsonObject2.put("testInt", 1);
		jsonObject2.put("testDouble", 1.43d);

		final JSONArray<JSONObject<String, Object>> jsonArray0 = new JSONArray<>();
		final JSONArray<JSONObject<String, Object>> jsonArray1 = new JSONArray<>();
		final JSONArray<JSONObject<String, Object>> jsonArray2 = new JSONArray<>();
		final JSONArray<JSONObject<String, Object>> jsonArray3 = new JSONArray<>();

		jsonArray1.add(jsonObject0);

		jsonArray1.add(jsonObject1);

		jsonArray2.add(jsonObject1);
		jsonArray2.add(jsonObject2);

		jsonArray3.add(jsonObject0);
		jsonArray3.add(jsonObject1);
		jsonArray3.add(jsonObject2);


		JSONAware testNull0 = JSONReader.read(null);
		assertNull(testNull0);
		JSONAware testNull1 = JSONReader.read("");
		assertNull(testNull1);
		JSONAware testNull2 = JSONReader.read("435254235432");
		assertNull(testNull2);

		JSONAware testEmpty1 = JSONReader.read(jsonObject0.toJSONString());
		assertEquals(jsonObject0, testEmpty1);

		JSONAware testEmpty2 = JSONReader.read(jsonArray0.toJSONString());
		assertEquals(jsonArray0, testEmpty2);

		JSONAware testObject1 = JSONReader.read(jsonObject1.toJSONString());
		assertEquals(jsonObject1.toJSONString(), testObject1.toJSONString());

		JSONAware testObject2 = JSONReader.read(jsonObject2.toJSONString());
		assertEquals(jsonObject2.toJSONString(), testObject2.toJSONString());

		JSONAware testArray1 = JSONReader.read(jsonArray1.toJSONString());
		assertEquals(jsonArray1.toJSONString(), testArray1.toJSONString());

		JSONAware testArray2 = JSONReader.read(jsonArray2.toJSONString());
		assertEquals(jsonArray2.toJSONString(), testArray2.toJSONString());

		JSONAware testArray3 = JSONReader.read(jsonArray3.toJSONString());
		assertEquals(jsonArray3.toJSONString(), testArray3.toJSONString());
	}
}