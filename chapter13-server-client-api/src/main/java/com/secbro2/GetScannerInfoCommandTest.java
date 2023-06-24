package com.secbro2;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieScannerResource;
import org.kie.server.api.model.KieServiceResponse;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;

/**
 * @author sec
 * @version 1.0
 * @date 2022/6/8
 **/
public class GetScannerInfoCommandTest {

	private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
	private static final String USER = "kieserver";
	private static final String PASSWORD = "kieserver1!";
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

		ServiceResponse<KieScannerResource> response = kieServicesClient.getScannerInfo("hello");

		// 返回结果判断与处理
		if (response.getType() == KieServiceResponse.ResponseType.SUCCESS) {
			System.out.println("成功返回结果信息： " + response.getResult());
		} else {
			System.out.println("失败返回结果信息: " + response.getMsg());
		}
	}

}
