package com.secbro2;

import com.secbro2.entity.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * FactHandler使用演示
 *
 * @author sec
 * @version 1.0
 **/
public class StaticMethodImportTest {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("static-method");

		Person p = new Person();
		p.setAge(17);
		kieSession.insert(p);
		int count = kieSession.fireAllRules();
		System.out.println("触发了" + count + "条规则。");
	}
}
