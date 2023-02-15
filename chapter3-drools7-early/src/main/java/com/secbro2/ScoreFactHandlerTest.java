package com.secbro2;

import com.secbro2.entity.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * FactHandler使用演示
 *
 * @author sec
 * @version 1.0
 **/
public class ScoreFactHandlerTest {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("score-rule");
		try {
			Person p = new Person();
			p.setAge(17);

			FactHandle factHandle = kieSession.insert(p);
			int count = kieSession.fireAllRules();
			System.out.println("触发了" + count + "条规则。");
			// 删除工作内存中的Fact对象
			kieSession.delete(factHandle);
			count = kieSession.fireAllRules();
			System.out.println("触发了" + count + "条规则。");
		} finally {
			kieSession.dispose();
		}
	}
}
