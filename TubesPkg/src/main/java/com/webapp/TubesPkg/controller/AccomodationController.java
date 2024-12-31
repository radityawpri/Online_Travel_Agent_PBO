
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
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/accomodation")
public class AccomodationController {
    
    @Autowired
    private AccomodationService accomodationService;

    @GetMapping
    public String getAllAcomodation(Model models) {
        List<Accomodation> accomodations = accomodationService.getAllAccomodations();
        models.addAttribute("accomodations", accomodations);
        System.out.println(accomodations);
        return "admin/accomodation/listAccomodation";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model models) {
        models.addAttribute("accomodation", new Accomodation());
        return "admin/accomodation/addAccomodation";
    }

    @PostMapping("/save")
    public String saveAccomodation(@ModelAttribute Accomodation accomodation) {
        //TODO: process POST request
        accomodationService.createAccomodation(accomodation);
        return "redirect:/accomodation";
    } 

    @GetMapping("/edit/{id}")
    public String editAccomodation(@PathVariable("id") int id, Model model) {
        Optional<Accomodation> accomodation = accomodationService.getAccomodationById(id);
        if (accomodation.isPresent()){
            model.addAttribute("accomodation", accomodation.get());
            return "admin/accomodation/editAccomodation";
        }else {
            return "redirect:/accomodation?error=notFound";
        }
    }

    @PostMapping("/update/{id}")
    public String updateAccomodation(@PathVariable("id") int id, @ModelAttribute Accomodation accomodation) {
        //TODO: process POST request
        Accomodation updateAccomodation = accomodationService.updateAccomodation(id, accomodation);
        if (updateAccomodation != null){
            return "redirect:/accomodation";
        }else {
            return "redirect:/error";
        }
    }

    @GetMapping("/{id}")
    public String getAccomodationById(@PathVariable("id") int id, Model models) {
        Optional<Accomodation> accomodation = accomodationService.getAccomodationById(id);
        if (accomodation.isPresent()){
            models.addAttribute("accomodation", accomodation.get());
            return "admin/accomodation/listAccomodation";
        }else {
            return "redirect:/accomodation?error=notFound";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteAccomodation(@PathVariable("id") int id) {
        //TODO: process POST request
        boolean isDelete = accomodationService.deleteAccomodation(id);
        if (isDelete){
            return "redirect:/accomodation";
        }else {
            return "redirect:/accomodation?error=deleteFailed";
        }
    }
    
    
    
    
}
