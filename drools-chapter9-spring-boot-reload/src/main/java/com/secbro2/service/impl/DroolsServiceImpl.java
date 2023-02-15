package com.secbro2.service.impl;

import com.secbro2.service.DroolsService;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/6
 **/
@Service("droolsService")
public class DroolsServiceImpl implements DroolsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsServiceImpl.class);

	/**
	 * 存储规则的磁盘根路径，可通过SpringBoot的配置文件来配置，以区分不同环境。
	 */
	private static final String RULES_PATH = "/Users/zzs/temp/rules";

	private static final KieServices KIE_SERVICES = KieServices.get();

	private static volatile KieContainer KIE_CONTAINER = null;

	@Override
	public KieContainer getKieContainer() {
		if (KIE_CONTAINER == null) {
			synchronized (KieContainer.class) {
				KIE_CONTAINER = initInstance();
			}
		}
		return KIE_CONTAINER;
	}

	private KieContainer initInstance() {
		final KieRepository kieRepository = KIE_SERVICES.getRepository();
		// 创建一个KieModule并添加到KieRepository中
		kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
		KieBuilder kieBuilder = KIE_SERVICES.newKieBuilder(initKieFileSystem());
		// 如果构建有错误信息，则抛出异常。异常逻辑可根据具体业务逻辑定制化处理。
		Results results = kieBuilder.getResults();
		if (results.hasMessages(Message.Level.ERROR)) {
			LOGGER.error("规则构建失败，返回原有KIE_CONTAINER");
			return KIE_CONTAINER;
		}
		kieBuilder.buildAll();
		return KIE_SERVICES.newKieContainer(kieRepository.getDefaultReleaseId());
	}

	@Override
	public synchronized KieContainer reloadKieContainer() {
		KIE_CONTAINER = this.initInstance();
		return KIE_CONTAINER;
	}

	@Override
	public KieSession newKieSession() {
		return getKieContainer().newKieSession();
	}

	@Override
	public KieSession newKieSession(String sessionName) {
		return getKieContainer().newKieSession(sessionName);
	}

	/**
	 * 初始化KieFileSystem
	 */
	private KieFileSystem initKieFileSystem() {
		KieFileSystem kieFileSystem = KIE_SERVICES.newKieFileSystem();
		for (File file : getRuleFiles()) {
			kieFileSystem.write(ResourceFactory.newFileResource(file.getAbsolutePath()));
		}
		return kieFileSystem;
	}

	/**
	 * 获取指定目录下的所有规则文件。
	 * PS：此处只是简单实现，正常需递归遍历调用，获取所有子目录、子文件。
	 */
	private List<File> getRuleFiles() {
		List<File> fileList = new ArrayList<>();
		File file = new File(RULES_PATH);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files != null) {
				fileList.addAll(Arrays.asList(files));
			}
		}
		return fileList;
	}


}
