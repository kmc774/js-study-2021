package com.prac.spring.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		
		return "home";
	}
	
	@GetMapping("/index")
	public String main() {
		
		return "home";
	}
	
	@GetMapping("/result")
	public String result() {
		
		return "common/result";
	}

}
