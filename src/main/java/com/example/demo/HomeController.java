package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @PostMapping("/procflight")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name="ldt") String ldt) {
        //String ldtPattern = "yyyy-MM-dd HH:mm";
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ldtPattern);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        String formattedDate = ldt.substring(1,ldt.length());
        LocalDateTime dateTime = LocalDateTime.parse(formattedDate, formatter);
        flight.setLdt(dateTime);
        flightRepository.save(flight);

        return "redirect:/";
    }

}
