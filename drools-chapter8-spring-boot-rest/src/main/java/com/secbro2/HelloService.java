package com.secbro2;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.internal.command.CommandFactory;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sec
 * @version 1.0
 * @date 2022/7/25
 **/
@Service
public class HelloService {

	@Value("${kie.containerId}")
	private String containerId;

	@Value("${kie.server.user}")
	private String user;

	@Value("${kie.server.pwd}")
	private String password;

	@Value("${kie.server.url}")
	private String url;

	private String outIdentifier = "response";

	public Person calculateIncomeTax(Person incomeObj) {
		
		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(url, user, password, 60000);
		config.setMarshallingFormat(MarshallingFormat.JSON);

		RuleServicesClient client = KieServicesFactory.newKieServicesClient(config)
				.getServicesClient(RuleServicesClient.class);

		BatchExecutionCommand batchExecutionCommand = batchCommand(incomeObj);
		ServiceResponse<ExecutionResults> result = client.executeCommandsWithResults(containerId,
				batchExecutionCommand);

		Person response = null;
		if (result.getType() == ServiceResponse.ResponseType.SUCCESS) {
			response = (Person) result.getResult().getValue(outIdentifier);
		} else {
			System.out.println("Something went wrong!!");
		}
		return response;
	}

	private BatchExecutionCommand batchCommand(Person incomeObj) {
		List<Command<?>> cmds = buildCommands(incomeObj);
		return CommandFactory.newBatchExecution(cmds);
	}

	private List<Command<?>> buildCommands(Person incomeObj) {
		List<Command<?>> cmds = new ArrayList<>();
		KieCommands commands = KieServices.Factory.get().getCommands();
		cmds.add(commands.newInsert(incomeObj, outIdentifier));
		cmds.add(commands.newFireAllRules());
		return cmds;
	}
}
