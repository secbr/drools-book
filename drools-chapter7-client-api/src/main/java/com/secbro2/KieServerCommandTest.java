package com.secbro2;

import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieServiceResponse;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import java.util.Arrays;

/**
 * @author sec
 * @version 1.0
 * @date 2022/6/8
 **/
public class KieServerCommandTest {

	private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
	private static final String USER = "kieserver";
	private static final String PASSWORD = "password1!";
	private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

	private static KieServicesConfiguration conf;
	private static KieServicesClient kieServicesClient;


	public static void main(String[] args) {
		initialize();
		executeCommand();
	}

	/**
	 * 初始化连接配置和KieServicesClient
	 */
	public static void initialize() {
		conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
		conf.setMarshallingFormat(FORMAT);
		kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
	}

	public static void executeCommand() {
		// 封装待执行的命令
		KieCommands commandsFactory = KieServices.get().getCommands();
		// insert命令
		Command<?> insert = commandsFactory.newInsert("Drools");
		// 触发规则命令
		Command<?> fireAllRules = commandsFactory.newFireAllRules();
		// 构建成一个命令批次
		Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert, fireAllRules));

		// 获取客户端对象，并调用执行命令
		String containerId = "hello";
		RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
		ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(containerId,
				batchCommand);

		// 返回结果判断与处理
		if (executeResponse.getType() == KieServiceResponse.ResponseType.SUCCESS) {
			System.out.println("成功返回结果信息： " + executeResponse.getMsg());
		} else {
			System.out.println("失败返回结果信息: " + executeResponse.getMsg());
		}
	}

}
