package com.secbro2.controller;

import com.secbro2.service.DroolsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/6
 **/
@RestController
public class DroolsController {

	@Resource
	private DroolsService droolsService;

	@GetMapping("/reload")
	public String reloadRules() {
		droolsService.reloadKieContainer();
		return "SUCCESS";
	}
}
