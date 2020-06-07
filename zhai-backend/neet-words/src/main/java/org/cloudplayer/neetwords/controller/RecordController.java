package org.cloudplayer.neetwords.controller;

import org.cloudplayer.neetwords.pojo.Record;
import org.cloudplayer.neetwords.pojo.RecordList;
import org.cloudplayer.neetwords.service.UserService;
import org.cloudplayer.neetwords.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecordController {

	@Autowired
	private UserService userService;

	/**
	 * 同步记录
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "/record", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> syncRecord(@RequestBody Map<String, Object> body) {
        List<Record> list = (List<Record>) body.get("record");
        RecordList recordList = new RecordList();
        recordList.setUserId((String) body.get("userId"));
        recordList.setRecordList(list);
        userService.record(recordList);
		return ResponseEntity.ok(JsonUtil.success());
    }

	/**
	 * 获取同步
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/record", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getRecord(@RequestParam("id")String userId){

	    RecordList list = userService.getRecord(userId);
        Map<String, Object> res = new HashMap<>();
		res.put("recordList", list);

        return ResponseEntity.ok(JsonUtil.success(res));
    }
}

