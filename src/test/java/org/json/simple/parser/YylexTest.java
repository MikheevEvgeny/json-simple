package org.json.simple.parser;

import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.reader.JSONReader;
import org.json.simple.writer.JSONWriter;

import java.io.StringReader;

public class YylexTest extends TestCase {

	public void testYylex() throws Exception {
		String s = "\"\\/\"";
		System.out.println(s);
		StringReader in = new StringReader(s);
		Yylex lexer = new Yylex(in);
		Yytoken token = lexer.yylex();
		assertEquals(Yytoken.TYPE_VALUE, token.type);
		assertEquals("/", token.value);

		s = "\"abc\\/\\r\\b\\n\\t\\f\\\\\"";
		System.out.println(s);
		in = new StringReader(s);
		lexer = new Yylex(in);
		token = lexer.yylex();
		assertEquals(Yytoken.TYPE_VALUE, token.type);
		assertEquals("abc/\r\b\n\t\f\\", token.value);

		s = "[\t \n\r\n{ \t \t\n\r}";
		System.out.println(s);
		in = new StringReader(s);
		lexer = new Yylex(in);
		token = lexer.yylex();
		assertEquals(Yytoken.TYPE_LEFT_SQUARE, token.type);
		token = lexer.yylex();
		assertEquals(Yytoken.TYPE_LEFT_BRACE, token.type);
		token = lexer.yylex();
		assertEquals(Yytoken.TYPE_RIGHT_BRACE, token.type);

		s = "\b\f{";
		System.out.println(s);
		in = new StringReader(s);
		lexer = new Yylex(in);
		ParseException err = null;
		try {
			lexer.yylex();
		} catch (ParseException e) {
			err = e;
			System.out.println("error:" + err);
			assertEquals(ParseException.ERROR_UNEXPECTED_CHAR, e.getErrorType());
			assertEquals(0, e.getPosition());
			assertEquals('\b', e.getUnexpectedObject());
		}
		assertNotNull(err);

		s = "{a : b}";
		System.out.println(s);
		in = new StringReader(s);
		lexer = new Yylex(in);
		err = null;
		try {
			lexer.yylex();
			lexer.yylex();
		} catch (ParseException e) {
			err = e;
			System.out.println("error:" + err);
			assertEquals(ParseException.ERROR_UNEXPECTED_CHAR, e.getErrorType());
			assertEquals('a', e.getUnexpectedObject());
			assertEquals(1, e.getPosition());
		}
		assertNotNull(err);
	}


	public void testLongAndInt() {
		JSONArray<Integer> list1 = new JSONArray<Integer>() {{
			add(1);
			add(2);
		}};
		String list1Str = JSONWriter.toJSONString(list1);
		Object list1Read = JSONReader.read(list1Str);
		assertEquals(list1Read, list1);

		JSONArray<Long> list2 = new JSONArray<Long>() {{
			add(1L);
			add(1000L + Integer.MAX_VALUE);
		}};
		String list2Str = JSONWriter.toJSONString(list2);
		Object list2Read = JSONReader.readArray(list2Str);
		assertEquals(list2Read, list2);

		JSONArray<Long> list3 = new JSONArray<Long>() {{
			add(1L + Integer.MAX_VALUE);
			add(1000L + Integer.MAX_VALUE);
		}};
		String list3Str = JSONWriter.toJSONString(list3);
		Object list3Read = JSONReader.readArray(list3Str);
		assertEquals(list3Read, list3);

		JSONArray<String> list4 = new JSONArray<String>() {{
			add("" + 1);
			add("" + 1000L + Integer.MAX_VALUE);
			add("100");
		}};
		String list4Str = JSONWriter.toJSONString(list4);
		Object list4Read = JSONReader.readArray(list4Str);
		assertEquals(list4Read, list4);

		JSONArray<Double> list5 = new JSONArray<Double>() {{
			add(1D);
			add(1000D + Integer.MAX_VALUE);
			add(123.456);
		}};
		String list5Str = JSONWriter.toJSONString(list5);
		Object list5Read = JSONReader.readArray(list5Str);
		assertEquals(list5Read, list5);

		JSONArray<JSONObject<String, Object>> list6 = new JSONArray<JSONObject<String, Object>>() {{
			add(new JSONObject<String, Object>() {{
				put("111", "222");
			}});
			add(new JSONObject<String, Object>() {{
				put("333", "444");
				put("555", "666");
			}});
			add(new JSONObject<String, Object>() {{
				put("999", 999);
				put("000", true);
			}});
		}};
		String list6Str = JSONWriter.toJSONString(list6);
		Object list6Read = JSONReader.readArray(list6Str);
		assertEquals(list6Read, list6);
	}
}
