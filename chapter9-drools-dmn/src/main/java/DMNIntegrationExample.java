import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.impl.DMNContextImpl;

import java.math.BigDecimal;

public class DMNIntegrationExample {
	public static void main(String[] args) {
		// 初始化KIE服务
		KieServices kieServices = KieServices.Factory.get();

		// 创建KieFileSystem
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(kieServices.getResources().newClassPathResource("BookDiscountStrategy.dmn"));

		// 创建KieBuilder
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();

		// 创建KieContainer
		KieRepository kieRepository = kieServices.getRepository();
		ReleaseId releaseId = kieRepository.getDefaultReleaseId();
		KieContainer kieContainer = kieServices.newKieContainer(releaseId);

		// 获取DMN运行时
		DMNRuntime dmnRuntime = kieContainer.newKieSession().getKieRuntime(DMNRuntime.class);

		// 加载DMN模型
		DMNModel dmnModel = dmnRuntime.getModel("https://kiegroup.org/dmn/_EF742D4B-DEED-4406-A728-F82558A6FF7A",
				"BookDiscountStrategy");

		// 创建DMN上下文并设置输入数据
		DMNContext dmnContext = new DMNContextImpl();
		dmnContext.set("BookCategory", "小说");
		dmnContext.set("PublishYear", 2021);
		dmnContext.set("OriginalPrice", 80);

		// 执行DMN模型
		DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

		// 获取输出结果
		BigDecimal finalPrice = (BigDecimal) dmnResult.getDecisionResultByName("FinalPrice").getResult();
		System.out.println("最终售价: " + finalPrice);
	}
}
