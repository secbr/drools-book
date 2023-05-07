package com.secbro2.utils;

import org.drools.core.definitions.rule.impl.RuleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 记录触发规则之后的日志，可根据需要拓展其他操作或日志输出。也可用于规则调试时日志输出。
 **/
public class DroolsAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsAction.class);

	public static void info(String content, RuleImpl rule) {
		LOGGER.info("Rule[{}] is matched. And the message is '{}'", rule.getName(), content);
		// 其他操作或日志输出
	}

	public static void error(String content, RuleImpl rule) {
		LOGGER.info("Rule[{}]is matched. And something error with '{}'", rule.getName(), content);
		// 其他操作或日志输出
	}
}
