package com.kristalika.app.controllers;

import com.kristalika.app.models.Appointment;
import com.kristalika.app.models.Masters;
import com.kristalika.app.models.Post;
import com.kristalika.app.repo.AppointmentRepository;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class MastersController {

    @Autowired
    private MastersRepository mastersRepository;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        // попытка сделать автозаход
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("userName")) {
//                return "redirect:/service";
//            }
//        }
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
        Date today = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy"); // "dd" - день, "MM" - месяц, "yyyy" - год
        String formattedDate = formatter.format(today);


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


    //доделать выход с аккаунта
    @PostMapping
    public String service(Model model, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        System.out.println("wwwwwww");
        if (cookies != null)
            for (Cookie cookie : cookies) {
                System.out.println();
                cookie.setValue("");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

        return "redirect:/login";
    }


    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/pin-reset")
    public String pinReset(Model model) {
        return "pin-reset";
    }

    @PostMapping("/pin-reset")
    public String pinResetPost(@RequestParam int oldPin, @RequestParam int newPin, Model model) {

        String master = mastersRepository.findNameByPin(oldPin);

        if (master != null) {
            mastersRepository.updatePin(newPin, oldPin);
        }
        return "redirect:/service";
    }

}
