package com.secbro2;

import com.secbro2.entity.Person;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.Test;

public class PersonRuleTest {

	@Test
	public void test() {
		PersonUnit personUnit = new PersonUnit();
		RuleUnitProvider ruleUnitProvider = RuleUnitProvider.get();
		try (RuleUnitInstance<PersonUnit> instance = ruleUnitProvider.createRuleUnitInstance(personUnit)) {
			Person p = new Person();
			p.setAge(17);
			personUnit.getPersons().add(p);
			int count = instance.fire();
			System.out.println("触发了" + count + "条规则。");
			System.out.println("规则执行结果，score=" + p.getScore() + ",desc=" + p.getDesc() + "。");
		}
	}
}