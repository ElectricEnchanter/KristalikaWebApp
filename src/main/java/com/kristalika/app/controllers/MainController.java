package com.kristalika.app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping("/")
	public String home(Model model) {

		model.addAttribute("title", "Главная страница");
		return "home";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", " страница про нас");
		return "about";
	}

	@GetMapping("/masters")
	public String masters(Model model) {
		model.addAttribute("title", " страница про мастер");
		return "masters";
	}

	@GetMapping("/price")
	public String price(Model model) {
		model.addAttribute("title", " страница про цену");
		return "price";
	}

	@GetMapping("/appointment")
	public String appointment(Model model) {
		model.addAttribute("title", " страница про pfgbcm");
		return "appointment";
	}






}