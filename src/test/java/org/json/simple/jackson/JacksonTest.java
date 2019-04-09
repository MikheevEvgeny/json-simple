package org.json.simple.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Test interaction with Jakson
 *
 * @author Mikheev Evgeny
 */
public class JacksonTest extends TestCase {
	private ObjectMapper mapper;

	public void setUp() {
		mapper = new ObjectMapper();
		mapper.setDateFormat(JSONValue.getDefaultDateFormat());
	}

	@SuppressWarnings("unchecked")
	public void testJSONObject() throws IOException {
		final JSONObject jsonObject = new JSONObject();
		final JSONObject<String, Object> jsonObject1 = new JSONObject<>();

		jsonObject.put("list", new ArrayList<>());
		jsonObject1.put("list", new ArrayList<>());

		jsonObject.put("int", 123);
		jsonObject1.put("int", 123);

		jsonObject.put("string", "qwerty");
		jsonObject1.put("string", "qwerty");


		String jsonObjectString = mapper.writeValueAsString(jsonObject);
		String jsonObject1String = mapper.writeValueAsString(jsonObject1);
		assertEquals(jsonObject.toJSONString(), jsonObjectString);
		assertEquals(jsonObject1.toJSONString(), jsonObject1String);

		JSONObject readObject = mapper.readValue(jsonObjectString, JSONObject.class);
		JSONObject readObject1 = mapper.readValue(jsonObjectString, new TypeReference<JSONObject<String, Object>>() {
		});
		assertEquals(jsonObject, readObject);
		assertEquals(jsonObject1, readObject1);
	}

	@SuppressWarnings("unchecked")
	public void testJSONArray() throws IOException {
		final JSONObject jsonObject = new JSONObject();
		final JSONObject<String, Object> jsonObject1 = new JSONObject<>();

		jsonObject.put("list", new ArrayList<>());
		jsonObject.put("int", 123);
		jsonObject.put("string", "qwerty");

		jsonObject1.put("list", new ArrayList<>());
		jsonObject1.put("int", 123);
		jsonObject1.put("string", "qwerty");

		final JSONArray jsonArray = new JSONArray();
		final JSONArray<JSONObject> jsonArray1 = new JSONArray<>();

		jsonArray.add(jsonObject);
		jsonArray.add(jsonObject1);

		jsonArray1.add(jsonObject);
		jsonArray1.add(jsonObject1);

		String jsonArrayString = mapper.writeValueAsString(jsonArray);
		String jsonArray1String = mapper.writeValueAsString(jsonArray1);
		assertEquals(jsonArray.toJSONString(), jsonArrayString);
		assertEquals(jsonArray1.toJSONString(), jsonArray1String);

		JSONArray readArray = mapper.readValue(jsonArrayString, JSONArray.class);
		JSONArray readArray1 = mapper.readValue(jsonArray1String, new TypeReference<JSONArray<JSONObject>>() {
		});
		assertEquals(jsonArray, readArray);
		assertEquals(jsonArray1, readArray1);
	}
}
