package com.secbro2.service;

/**
 * @author sec
 * @version 1.0
 * @date 2021/3/21
 **/
public class BlackListService {

	public boolean isInBlacklist(String phone) {
		// 简单模拟黑名单检查，通常黑名单为列表，位于数据库或缓存或三方服务中
		return "13888888888".equals(phone);
	}
}
