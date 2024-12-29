
package com.webapp.TubesPkg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webapp.TubesPkg.models.Accomodation;
import com.webapp.TubesPkg.service.AccomodationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/accomodation")
public class AccomodationController {
    
    @Autowired
    private AccomodationService accomodationService;

    @GetMapping
    public String getAllAcomodation(Model models) {
        List<Accomodation> accomodations = accomodationService.getAllAccomodations();
        models.addAttribute("accomodations", accomodations);
        return "index";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model models) {
        models.addAttribute("accomodation", new Accomodation());
        return "admin/accomodation/addAccomodation";
    }

    @PostMapping("/save")
    public String postMethodName(@ModelAttribute Accomodation accomodation) {
        //TODO: process POST request
        accomodationService.createAccomodation(accomodation);
        return "redirect:/accomodation";
    }

    @GetMapping("/{id}")
    public String getAccomodationById(@PathVariable("id") int id, Model models) {
        Optional<Accomodation> accomodation = accomodationService.getAccomodationById(id);
        if (accomodation.isPresent()){
            models.addAttribute("accomodation", accomodation.get());
            return "admin/accomodation/detaiilAccomodation";
        }else {
            return "redirect:/accomodation?error=notFound";
        }
    }
    
    
    
}
