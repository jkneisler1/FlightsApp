package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/")
    public String messageList(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addFlight(Model model) {
        model.addAttribute("flight", new Flight());
        return "flightForm";
    }

    @PostMapping("/procsearch")
    public String searchResult(Model model, @RequestParam(name="search") String search) {
        model.addAttribute("flights", flightRepository.findFlightsByAirline(search));
        return "searchlist";
    }

    /*
    @PostMapping("/procflight")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name="date") String date) {
        String pattern = "yyyy-MM-dd";

        try {
            String formattedDate = date.substring(1,date.length());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(formattedDate);
            flight.setDate(realDate);
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }

        flightRepository.save(flight);

        return "redirect:/";
    }
    */

    /*
    @PostMapping("/procflight")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name="date") String date, @RequestParam(name="ldt") String ldt) {
        System.out.println("Got here");
        String pattern = "yyyy-MM-dd";
        String ldtPattern = "yyyy-MM-dd HH:mm";
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ldtPattern);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println(ldt);
        try {
            String formattedDate = date.substring(1,date.length());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(formattedDate);
            flight.setDate(realDate);
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }
        System.out.println(ldt);
        System.out.println(formatter);
        //LocalDateTime dateTime = LocalDateTime.parse(aStr, formatter);
        LocalDateTime dateTime = LocalDateTime.parse(ldt, formatter);
        System.out.println(dateTime);
        flight.setLdt(dateTime);

        flightRepository.save(flight);

        return "redirect:/";
    }
     */

     /*     */
    @PostMapping("/procflight")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name="ldt") String ldt) {
        String ldtPattern = "yyyy-MM-dd HH:mm";

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ldtPattern);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        // System.out.println(ldt);
        // System.out.println(formatter);
        String formattedDate = ldt.substring(1,ldt.length());
        LocalDateTime dateTime = LocalDateTime.parse(formattedDate, formatter);
        flight.setLdt(dateTime);
        flightRepository.save(flight);

        return "redirect:/";
    }

}
