package com.webapp.TubesPkg.controller;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class TransportationController {

    @Autowired
    private TransportationService transportationService;

    @GetMapping("/addTransportation")
    public String showAddTransportationForm(Model models) {
        models.addAttribute("packageTravel", new PackageTravel());
        models.addAttribute("transportation", new Transportation());
        return "addTransportation"; 
    }

  
    @PostMapping("/saveTransportation")
    public String saveTransportation(
        @ModelAttribute Transportation transportation,
        @ModelAttribute PackageTravel packageTravel) {
       
          transportationService.saveTransportation(transportation, packageTravel);
        return "redirect:/index";
    }
        
}