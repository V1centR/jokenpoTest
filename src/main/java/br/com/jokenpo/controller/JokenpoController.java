package br.com.jokenpo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class JokenpoController {
	
	@GetMapping("/play")
	public String execGame() {
		
		
		return "exec ok";
	}

}
