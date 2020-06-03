package org.cloudplayer.neetwords.utils;

import org.cloudplayer.neetwords.pojo.LoginDto;
import org.springframework.web.client.RestTemplate;

public class WxUtil {


	private static final String APP_ID = "aaa";
	private static final String APP_SECRET = "bbb";


	public static LoginDto getLoginDto(String code) {
		RestTemplate restTemplate = new RestTemplate();
		LoginDto loginDto = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid="+APP_ID+"&secret="+APP_SECRET+"&js_code="+code+"&grant_type=authorization_code", LoginDto.class);

		return loginDto;
	}
}
