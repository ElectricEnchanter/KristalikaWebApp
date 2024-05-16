package com.kristalika.app.controllers;

import com.kristalika.app.models.Masters;
import com.kristalika.app.models.Post;
import com.kristalika.app.repo.MastersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	private HttpServletRequest request;

	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request){
//		HttpSession session = request.getSession();
//		Integer count = (Integer) session.getAttribute("count");
//		if(count == null){
//			session.setAttribute("count", 1);
//			count = 1;
//		}
//		else {
//			session.setAttribute("count", count + 1);
//		}

		//счетчик заходов

//		model.addAttribute("count", count);
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam int pin, Model model, HttpServletResponse response) {

		String master = mastersRepository.findNameByPin(pin);

		Cookie cookie1 = new Cookie("userName", master);
		cookie1.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie1);

		if (master != null) {
			model.addAttribute("userName", master);
			return "redirect:/service";
		} else {
			return "redirect:/home";
		}
	}

	@GetMapping("/service")
	public String service(Model model, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userName")) {
				String username = cookie.getValue();
				model.addAttribute("userName", username);
				break;
			}
		}
		return "service";
	}

	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}



}
