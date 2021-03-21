package com.choupangxia;

import com.choupangxia.entity.User;
import com.choupangxia.service.BlackListService;
import com.choupangxia.service.MessageService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 信用评级实例演示
 *
 * @author sec
 * @version 1.0
 **/
public class CalculateAmountTest {

	public static void main(String[] args) {
		// 代码块①：初始化规则引擎及获得KieSession
		KieServices kieServices = KieServices.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("calculate-amount");

		// 代码块②：设置全局变量
		// 黑名单校验服务
		BlackListService blackListService = new BlackListService();
		kieSession.setGlobal("blackListService", blackListService);
		// 消息通知服务
		MessageService messageService = new MessageService();
		kieSession.setGlobal("messageService", messageService);

		// 代码块③：封装Fact对象并插入
		User user = new User();
		// 黑名单中的手机号
		user.setPhone("13888888888");
		user.setFixedAssets(80);
		kieSession.insert(user);

		// 代码块④：设定规则分组
		kieSession.getAgenda().getAgendaGroup("calculate-max-amount-group").setFocus();

		// 代码块⑤：调用规则并获得触发规则数量
		int count = kieSession.fireAllRules();
		// 代码块⑥：关闭会话
		kieSession.dispose();
		System.out.println("触发了" + count + "条规则。");

		// 代码块⑦：根据返回结果处理对应业务逻辑
		// 规则未通过逻辑处理
		if ("REFUSED".equals(user.getCode())) {
			System.out.println("规则校验未通过，错误信息：" + user.getDesc());
			return;
		}
		// 规则通过逻辑处理
		System.out.println("用户信用额度上限为：" + user.getMaxAmount() + "万元");
	}
}
