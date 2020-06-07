package org.cloudplayer.neetwords.controller;


import org.cloudplayer.neetwords.exception.TencentException;
import org.cloudplayer.neetwords.pojo.Collection;
import org.cloudplayer.neetwords.pojo.Configuration;
import org.cloudplayer.neetwords.pojo.LoginDto;
import org.cloudplayer.neetwords.pojo.User;
import org.cloudplayer.neetwords.service.CollectionService;
import org.cloudplayer.neetwords.service.UserService;
import org.cloudplayer.neetwords.utils.JsonUtil;
import org.cloudplayer.neetwords.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {


	@Autowired
	private UserService userService;

	@Autowired
	private CollectionService collectionService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> body) {
		String code = (String) body.get("code");
		LoginDto loginDto = WxUtil.getLoginDto(code);
		Map<String, Object> res = new HashMap<>();
		System.out.println(loginDto.getErrcode());

		if (loginDto.getErrcode() == 0) {
			User user = userService.getUserById(loginDto.getOpenid());
			if (user == null) {
				//用户入库
				user = new User();
				user.setId(loginDto.getOpenid());
				Collection collection = new Collection(loginDto.getOpenid());
				Configuration configuration = new Configuration(loginDto.getOpenid());

				userService.insert(user);
				userService.saveConfig(configuration);
				collectionService.saveCollection(collection);
			}
			res.put("openid", loginDto.getOpenid());
			res.put("user", user);
		} else {
			return ResponseEntity.ok(JsonUtil.fail("tencent fail", TencentException.CODE));
		}
		return ResponseEntity.ok(JsonUtil.success(res));
	}
}
