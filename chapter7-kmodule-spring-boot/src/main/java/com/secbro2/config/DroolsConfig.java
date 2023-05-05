package com.secbro2.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化Drools相关配置
 **/
@Configuration
public class DroolsConfig {
	
	@Bean
	@ConditionalOnMissingBean(KieContainer.class)
	public KieContainer kieContainer() {
		return KieServices.get().getKieClasspathContainer();
	}

	@Bean
	@ConditionalOnMissingBean(KieBase.class)
	public KieBase kieBase() {
		return kieContainer().getKieBase();
	}
}
