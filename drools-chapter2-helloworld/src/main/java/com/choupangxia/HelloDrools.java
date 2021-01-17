package com.choupangxia;

import com.choupangxia.entity.PersonFact;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author sec
 * @version 1.0
 * @date 2021/1/17
 **/
public class HelloDrools {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession();
		PersonFact p = new PersonFact();
		kieSession.insert(p);
		int count = kieSession.fireAllRules();
		System.out.println("触发了" + count + "规则。");
	}
}
