package com.kristalika.app.controllers;

import com.kristalika.app.models.Appointment;
import com.kristalika.app.models.Clients;
import com.kristalika.app.repo.AppointmentRepository;
import com.kristalika.app.repo.ClientRepository;
import com.kristalika.app.repo.MastersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.Objects;
import java.util.Optional;

@Controller
public class AppointmentController {


    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MastersRepository mastersRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/appointment")
    public String appointment(Model model) {
        model.addAttribute("title", " страница про запись");
        return "appointment";
    }

    @PostMapping("/appointment")
    public String appointmentPost(@RequestParam("datepick") String date, @RequestParam("masterpick") Long masterName, Model model) {
        System.out.println(date);

        Iterable<Appointment> appointment = appointmentRepository.findAppointmentNotExist(date, masterName);

        System.out.println(appointment);
        ArrayList<Integer> ids = appointmentRepository.findIdByDateAndId(date, masterName);
        System.out.println(ids);

        if (appointment.toString().equals("[]")) {
            model.addAttribute("no", "Нет записей. Выбери другую дату или мастера!");
        } else {
            model.addAttribute("no", "Доступные записи:");
            model.addAttribute("appointment", appointment);
            model.addAttribute("master", mastersRepository.findNameById(masterName));

        }


//        System.out.println(appointment);
//        System.out.println(date);
//        System.out.println(masterName);
        return "appointment";
    }

    @GetMapping("/appointment/{id}/make")
    public String appointmentMake(@PathVariable(value = "id") Long id, Model model) {
        if (!appointmentRepository.existsById(id)) {
            return "redirect:/appointment";
        }
//        System.out.println(appointmentRepository.findIdByAppointments(id));
//        System.out.println();

        Optional<Appointment> appointment = appointmentRepository.findById(id);
        ArrayList<Appointment> res = new ArrayList<>();
        appointment.ifPresent(res::add);

        model.addAttribute("appointment", res);
        model.addAttribute("master", appointmentRepository.findMasterNameById(appointmentRepository.findIdByAppointments(id)));


        return "appointment-make";
    }

    @PostMapping("/appointment/{id}/make")
    public String appointmentMakeAdd(@PathVariable(value = "id") Long apppointId, @RequestParam("client") String name, @RequestParam("info") String info, @RequestParam("note") String note, Model model) {
        Clients client = new Clients(apppointId, name, info, note);
        System.out.println(clientRepository.findAppointmentById(apppointId));

        if (clientRepository.findAppointmentById(apppointId).toString().equals("[]")) {
            System.out.println(clientRepository.findAppointmentById(apppointId));
            clientRepository.save(client);
            System.out.println("save");
        }
        return "redirect:/appointment-success";
    }

    @GetMapping("/appointment/add")
    public String appointmentAdd(Model model) {
        model.addAttribute("title", " страница про добав");
        return "appointment-add";
    }

    @PostMapping("/appointment/add")
    public String appointmentDBAdd(@RequestParam("datepick") String date, @RequestParam("timepick") String time, HttpServletRequest request, Model model) {

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

    @GetMapping("/appointment/{id}/delete")
    public String AppointmentDelete(@PathVariable(value = "id") Long id, Model model) {
        appointmentRepository.deleteById(id);
        clientRepository.deleteByAppointmentId(id);
        return "redirect:/service";
    }

    @GetMapping("/appointment/{id}/success")
    public String AppointmentSuccess(@PathVariable(value = "id") Long id, Model model) {
//		postRepository.deleteById(id);
        return "redirect:/blog";
    }

    @GetMapping("/appointment/show")
    public String appointmentShow(Model model, HttpServletRequest request) {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM"); // "dd" - день, "MM" - месяц, "yyyy" - год
        String formattedDate = formatter.format(today);
        model.addAttribute("date", formattedDate);
        formatter = new SimpleDateFormat("dd.MM.yy"); // "dd" - день, "MM" - месяц, "yyyy" - год
        formattedDate = formatter.format(today);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) {
                String username = cookie.getValue();
                Long id = mastersRepository.findIdByName(username);
                model.addAttribute("userName", username);
                Iterable<Appointment> appointment = appointmentRepository.findAppointmentByDateAndId(formattedDate, id);

                System.out.println(formattedDate);
                System.out.println(appointment);
                System.out.println(id);

                formatter = new SimpleDateFormat("dd.MM"); // "dd" - день, "MM" - месяц, "yyyy" - год
                formattedDate = formatter.format(today);

                model.addAttribute("appointment", appointment);
                model.addAttribute("date", formattedDate);
                break;
            }
        }

        return "appointment-show";
    }

    @PostMapping("/appointment/show")
    public String appointmentShowElseDate(@RequestParam("datepick") String date, Model model, HttpServletRequest request) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM"); // "dd" - день, "MM" - месяц, "yyyy" - год
//        String formattedDate = formatter.format(date);


        // тут можно переписать
//        SELECT * FROM CLIENTS JOIN public.appointment a on a.id = CLIENTS.appointment_id;


        model.addAttribute("date", date);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) {
                String username = cookie.getValue();
                Long id = mastersRepository.findIdByName(username);
                Iterable<Appointment> appointment = appointmentRepository.findAppointmentByDateAndId(date, id);
                ArrayList<Integer> ids = appointmentRepository.findIdByDateAndId(date, id);
                System.out.println(ids);
                model.addAttribute("appointment", appointment);

                ArrayList<String> status = new ArrayList<>();
                for (int i = 0; i < ids.toArray().length; i++) {
                    System.out.println(ids.get(i));
                    Iterable<Clients> clients = clientRepository.findAppointmentById(Long.valueOf(ids.get(i)));
                    System.out.println(clients);
                    if (Objects.equals(clients.toString(), "[]")) {
                        System.out.println("Свободно");
                        status.addLast("Свободно");
                    } else {
                        System.out.println("Занято");
                        status.addLast("Занято");
                        model.addAttribute("client", clients);
                    }
                }
                model.addAttribute("status", status);

            }
        }
        return "appointment-show";
    }


    @GetMapping("/appointment-success")
    public String success(Model model) {
        return "appointment-success";
    }
}
