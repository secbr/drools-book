package com.secbro2;

import com.secbro2.entity.Order;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sec
 * @version 1.0
 * @date 2021/6/8
 **/
public class StatelessKieSessionTest1 {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		// 通过KieContainer创建无状态会话
		StatelessKieSession session = kieContainer.newStatelessKieSession("test-stateless-session");

		// 构建Fact
		Order order = new Order();
		order.setAmount(99.99);
		order.setOrderNo("N001");

		List<Order> orderList = new ArrayList<>();
		orderList.add(order);
		// 执行规则
		session.execute(orderList);

	}
}
