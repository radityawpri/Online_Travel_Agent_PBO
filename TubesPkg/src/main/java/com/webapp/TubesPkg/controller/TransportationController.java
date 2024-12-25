package com.webapp.TubesPkg.controller;

import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TransportationController {

    @GetMapping("/addTransportation")
    public String showAddTransportationForm(Models model) {
       
        model.addAttribute("transportation", new Transportation());
        return "addTransportation"; 
    }

    @PostMapping("/saveTransportation")
        public String saveTransportation(@ModelAttribute Transportation transportation){
        TransportationController.saveTransportation(Transportation);
    return "redirect:/index";
        }
        
}