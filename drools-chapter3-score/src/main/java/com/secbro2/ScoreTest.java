package com.secbro2;

import com.secbro2.entity.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Drools基础功能演示
 * @author sec
 * @version 1.0
 **/
public class ScoreTest {

    public static void main(String[] args) {
        // 生产中只需将代码部分进行包装，BRMS系统更多的是将代码部分与规则文件部分进行剥离和封装。
        KieServices kieServices = KieServices.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("score-rule");
        Person p = new Person();
        p.setAge(17);
        kieSession.insert(p);
        int count = kieSession.fireAllRules();
        System.out.println("触发了" + count + "条规则。");
        // PersonFact对象在WorkingMemory中以引用的形式传递，规则引擎不会创建新的对象，
        // 因此可通过引用直接获得修改之后的值。
        System.out.println("规则执行结果，score=" + p.getScore() + ",desc=" + p.getDesc() + "。");
    }
}
