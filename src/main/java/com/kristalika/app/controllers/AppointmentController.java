package com.kristalika.app.controllers;

import com.kristalika.app.models.Appointment;
import com.kristalika.app.models.Masters;
import com.kristalika.app.models.Post;
import com.kristalika.app.repo.AppointmentRepository;
import com.kristalika.app.repo.MastersRepository;
import com.kristalika.app.repo.PostRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class AppointmentController {


	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private MastersRepository mastersRepository;

	@GetMapping("/appointment")
	public String appointment(Model model) {
		model.addAttribute("title", " страница про pfgbcm");
		return "appointment";
	}

	@GetMapping("/appointment/add")
	public String appointmentAdd(Model model) {
		model.addAttribute("title", " страница про добав");
		return "appointment-add";
	}

	@PostMapping("/appointment/add")
	public String appointmentDBAdd(@RequestParam("datepick") String date, @RequestParam("timepick") String time ,HttpServletRequest request, Model model){

		if (Objects.equals(date, "") || Objects.equals(time, "")) return "appointment-add";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userName")) {
				String username = cookie.getValue();
				Long id = mastersRepository.findIdByName(username);
				Appointment appointment = new Appointment(id, date, time);
				appointmentRepository.save(appointment);
				break;
			}
		}

		// Обработать дату
		System.out.println("Selected date:" + date + "dasd");
		System.out.println("Selected time: " + time);



		return "appointment-add";
	}


}