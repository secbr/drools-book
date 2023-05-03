package com.secbro2;

import com.secbro2.entity.User;
import com.secbro2.service.BlackListService;
import com.secbro2.service.MessageService;
import com.secbro2.unit.UserUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

/**
 * 信用评级实例演示
 *
 * @author sec
 * @version 1.0
 **/
public class CalculateAmountTest {


	public static void main(String[] args) {

		UserUnit userUnit = new UserUnit();
		BlackListService blackListService = new BlackListService();
		MessageService messageService = new MessageService();
		userUnit.setBlackListService(blackListService);
		userUnit.setMessageService(messageService);
		RuleUnitProvider ruleUnitProvider = RuleUnitProvider.get();
		try (RuleUnitInstance<UserUnit> instance = ruleUnitProvider.createRuleUnitInstance(userUnit)) {
			User user = new User();
			// 黑名单中的手机号
			user.setPhone("13888888888");
			user.setFixedAssets(80);
			userUnit.getUsers().add(user);

			int count = instance.fire();
			System.out.println("触发了" + count + "条规则。");
			System.out.println("规则执行结果，code=" + user.getCode() + ",desc=" + user.getDesc() + ", maxAmount=" + user.getMaxAmount());
		}
	}
}
