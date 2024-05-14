package com.kristalika.app.controllers;

import com.kristalika.app.models.Masters;
import com.kristalika.app.models.Post;
import com.kristalika.app.repo.MastersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MastersController {

	@Autowired
	private MastersRepository mastersRepository;

	@GetMapping("/login")
	public String login(Model model){
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam int pin , Model model) {
		Masters master = mastersRepository.findByPin(pin);
//		String master = mastersRepository.findNameByPin(pin);
		if (master != null) {
			model.addAttribute("userName", master);
			return "service";
		} else {
			return "redirect:/home";
		}
	}

	@GetMapping("/service")
	public String service(Model model) {
		return "service";
	}

	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}



}
