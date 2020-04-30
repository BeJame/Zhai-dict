package org.cloudplayer.neetwords.controller;


import org.cloudplayer.neetwords.utils.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

	private static final String APP_ID = "aaa";
	private static final String APP_SECRET = "bbb";


	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> body) {
		

		return ResponseEntity.ok(JsonUtil.success());
	}
}
