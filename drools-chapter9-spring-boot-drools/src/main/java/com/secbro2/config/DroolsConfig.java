package com.secbro2.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * 初始化Drools相关配置
 **/
@Configuration
public class DroolsConfig {

	private static final String RULES_PATH = "rules/";
	private static final KieServices KIE_SERVICES = KieServices.get();

	@Bean
	@ConditionalOnMissingBean(KieFileSystem.class)
	public KieFileSystem kieFileSystem() throws IOException {
		KieFileSystem kieFileSystem = KIE_SERVICES.newKieFileSystem();
		for (Resource file : getRuleFiles()) {
			kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
		}
		return kieFileSystem;
	}

	@Bean
	@ConditionalOnMissingBean(KieContainer.class)
	public KieContainer kieContainer() throws IOException {
		final KieRepository kieRepository = KIE_SERVICES.getRepository();
		// 创建一个KieModule并添加到KieRepository中
		kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
		KieBuilder kieBuilder = KIE_SERVICES.newKieBuilder(kieFileSystem());
		kieBuilder.buildAll();
		// 如果构建有错误信息，则抛出异常。异常逻辑可根据具体业务逻辑定制化处理。
		Results results = kieBuilder.getResults();
		if (results.hasMessages(Message.Level.ERROR)) {
			throw new RuntimeException(results.getMessages().toString());
		}
		return KIE_SERVICES.newKieContainer(kieRepository.getDefaultReleaseId());
	}

	@Bean
	@ConditionalOnMissingBean(KieBase.class)
	public KieBase kieBase() throws IOException {
		return kieContainer().getKieBase();
	}

	private Resource[] getRuleFiles() throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
	}
}
