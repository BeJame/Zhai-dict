package org.cloudplayer.neetwords.controller;

import org.cloudplayer.neetwords.pojo.Collection;
import org.cloudplayer.neetwords.pojo.Configuration;
import org.cloudplayer.neetwords.service.CollectionService;
import org.cloudplayer.neetwords.service.UserService;
import org.cloudplayer.neetwords.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CollectionService collectionService;

	/**
	 * 获取配置
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/config",produces = "application/json;charset=utf-8",method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getConfig(@RequestParam("id") String userId){
		Configuration configuration = userService.getConfig(userId);
		Map<String, Object> res = new HashMap<>();
		res.put("config", configuration);
        return ResponseEntity.ok(JsonUtil.success(res));
    }


	/**
	 * 同步配置
	 * @param config
	 * @return
	 */
	@RequestMapping(value = "/config",produces = "application/json;charset=utf-8",method= RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> postConfig(@RequestBody Configuration config){
		userService.saveConfig(config);

        return ResponseEntity.ok(JsonUtil.success());
    }

    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> postCollection(@RequestBody Collection collection) {
		collectionService.saveCollection(collection);
		return ResponseEntity.ok(JsonUtil.success());
    }

    @RequestMapping("/collection")
    public ResponseEntity<Map<String, Object>> getCollection(@RequestParam("id")String userId) {
		Collection collection = collectionService.getCollection(userId);

		Map<String, Object> res = new HashMap<>();
		res.put("collection", collection);

		return ResponseEntity.ok(JsonUtil.success(res));
    }
}
