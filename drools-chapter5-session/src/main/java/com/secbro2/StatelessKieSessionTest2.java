package com.secbro2;

import com.secbro2.entity.Order;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sec
 * @version 1.0
 * @date 2021/6/8
 **/
public class StatelessKieSessionTest2 {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		// 通过KieContainer创建无状态会话
		StatelessKieSession session = kieContainer.newStatelessKieSession("test-stateless-session");

		// 构建Fact
		Order order = new Order();
		order.setAmount(99.99);
		order.setOrderNo("N001");

		List<Command> cmds = new ArrayList<>();
		cmds.add(CommandFactory.newInsert(order, "order"));
		ExecutionResults results = session.execute(CommandFactory.newBatchExecution(cmds));

		// 获得返回的Order
		Order order1 = (Order) results.getValue("order");
		System.out.println("Order No:" + order1.getOrderNo());


	}
}
