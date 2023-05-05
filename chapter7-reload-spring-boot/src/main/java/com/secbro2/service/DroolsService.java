package com.secbro2.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Drools服务类，用于获得KieContainer、重新加载规则等
 **/
public interface DroolsService {

	/**
	 * 获取KieContainer
	 *
	 * @return KieContainer
	 */
	KieContainer getKieContainer();

	/**
	 * 重复重新加载规则
	 *
	 * @return KieContainer
	 */
	KieContainer reloadKieContainer();

	/**
	 * 创建默认KieSession
	 *
	 * @return KieSession
	 */
	KieSession newKieSession();

	/**
	 * 创建指定名称的KieSession
	 *
	 * @param sessionName KieSession名称
	 * @return KieSession
	 */
	KieSession newKieSession(String sessionName);

}
