package org.cloudplayer.neetwords.controller;

import org.cloudplayer.neetwords.service.UserService;
import org.cloudplayer.neetwords.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 打卡
 */
@RestController
public class FinishController {

	@Autowired
	private UserService userService;

	/**
	 * 打卡
	 * @param count 当日完成单词数
	 * @param openId
	 * @return
	 */
    @RequestMapping("/finish")
    //和前端配合时加入,@RequestBody int uid
    public ResponseEntity addFinish(@RequestParam int count, @RequestParam("openId")String openId){
		userService.finish(count, openId); //数据修改
        return ResponseEntity.ok(JsonUtil.success());
    }
}
