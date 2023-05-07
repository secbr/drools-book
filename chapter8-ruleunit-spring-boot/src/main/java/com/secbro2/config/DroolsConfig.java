package com.secbro2.config;

import org.drools.ruleunits.api.RuleUnitProvider;
import org.kie.api.KieBase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化Drools相关配置
 **/
@Configuration
public class DroolsConfig {

	private static final RuleUnitProvider RULE_UNIT_PROVIDER = RuleUnitProvider.get();

	@Bean
	@ConditionalOnMissingBean(KieBase.class)
	public RuleUnitProvider ruleUnitProvider() {
		return RULE_UNIT_PROVIDER;
	}
}
