package com.secbro2.service;

/**
 * 消息通知接口
 *
 * @author sec
 * @version 1.0
 * @date 2021/3/21
 **/
public class MessageService {

	public void notify(String message) {
		System.out.println("MessageService消息通知：" + message);
	}
}
