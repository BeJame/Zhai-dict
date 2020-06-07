package org.cloudplayer.neetwords.service;


import org.cloudplayer.neetwords.pojo.*;

public interface UserService {
    User getUserById(String id);
    void update(User user);
    void insert(User user);

	/**
	 * 打卡
	 * @param count
	 * @param id
	 */
	void finish(int count, String id);

	/**
	 * 同步学习记录
	 * @param recordList
	 */
	void record(RecordList recordList);

	/**
	 * 获取用户配置信息
	 * @param userId
	 * @return
	 */
	Configuration getConfig(String userId);

	/**
	 * 保存用户配置信息
	 * @param config
	 */
	void saveConfig(Configuration config);

	/**
	 * 获取记录
	 * @param userId
	 */
	RecordList getRecord(String userId);


}
