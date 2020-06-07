package org.cloudplayer.neetwords.utils;

import org.cloudplayer.neetwords.pojo.LoginDto;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class WxUtil {


	private static final String APP_ID = "wx5e80f1c9bd94cb38";
	private static final String APP_SECRET = "fc0f1e6cd50b28b6b82d7833438dfd25";


	public static LoginDto getLoginDto(String code) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
		LoginDto loginDto = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid="+APP_ID+"&secret="+APP_SECRET+"&js_code="+code+"&grant_type=authorization_code", LoginDto.class );

		return loginDto;
	}

	private static class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
		public WxMappingJackson2HttpMessageConverter() {

			List<MediaType> mediaTypes = new ArrayList<>();

			mediaTypes.add(MediaType.TEXT_PLAIN);

			mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持

			setSupportedMediaTypes(mediaTypes);//

		}
	}
}
