package main;

import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class JsonExample {

	public static void main(String[] args) {

		JSONParser parser = new JSONParser();
		String s = "[" + "{\"id\": 1, \"item\":100}" + "{\"id\": 2, \"item\":200}" + "]";

		try {
			Object obj = parser.parse(s);
			JSONArray array = (JSONArray) obj;

			System.out.println("The 2nd element of array");
			System.out.println(array.get(1));
			System.out.println();

			JSONObject obj2 = (JSONObject) array.get(1);
			System.out.println("Field \"id\"");
			System.out.println(obj2.get("id"));

			Map<String, Integer> m = ((Map) (JSONObject) array.get(1));

			for (Entry<String, Integer> entry : m.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}

//	         s = "{}";
//	         obj = parser.parse(s);
//	         System.out.println(obj);
//
//	         s = "[5,]";
//	         obj = parser.parse(s);
//	         System.out.println(obj);
//
//	         s = "[5,,2]";
//	         obj = parser.parse(s);
//	         System.out.println(obj);
		} catch (ParseException pe) {

			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);

		}

	}

}
