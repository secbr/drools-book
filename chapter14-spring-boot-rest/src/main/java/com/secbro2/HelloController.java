package com.secbro2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sec
 * @version 1.0
 **/
@RestController
public class HelloController {

	@Resource
	private HelloService helloService;

	@GetMapping("/hello")
	public ResponseEntity<Person> getIncomeTax(@RequestParam(name = "age") Integer age) {
		Person person = new Person();
		person.setAge(age);
		return new ResponseEntity<>(helloService.calculateIncomeTax(person), HttpStatus.OK);
	}

}
