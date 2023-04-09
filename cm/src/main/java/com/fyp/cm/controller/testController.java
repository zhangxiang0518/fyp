package com.fyp.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
	@GetMapping("/hi")
	public String test() {
		return "hi";
	}
	

}
