package org.cloudplayer.neetwords.utils;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
	public static HashMap<String, Object> success(Map<String, Object> data) {
		HashMap<String, Object> res = new HashMap<>();
		res.put("code", ResCode.SUCCESS.getCode());
		res.put("msg", "");
		res.put("data", data);
		return res;
	}

	public static HashMap<String, Object> success() {
		HashMap<String, Object> res = new HashMap<>();
		res.put("code", ResCode.SUCCESS.getCode());
		res.put("msg", "");
		return res;
	}

	public static HashMap<String, Object> fail(String msg, int code) {
		HashMap<String, Object> res = new HashMap<>();
		res.put("code", code);
		res.put("msg", msg);
		return res;
	}

	public static void main(String[] args) {
		char[] arr = "lujyfwapvu".toCharArray();

		for (int i = 1; i < 26; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < arr.length; j++) {
				System.out.print((char)((arr[j]-97-i+26)%26+97));
			}
			System.out.println();
		}
	}
}
