package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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

    @PostMapping("/procflight")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name="date") String date) {
        String pattern = "yyyy-MM-dd";
        // System.out.println(date);
        // System.out.println(date.substring(1,date.length()));
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
}
