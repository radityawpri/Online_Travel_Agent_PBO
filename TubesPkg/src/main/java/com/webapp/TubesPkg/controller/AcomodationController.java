package com.webapp.TubesPkg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.webapp.TubesPkg.models.Accomodation;
import com.webapp.TubesPkg.service.AccomodationService;

@Controller
@RequestMapping("/acomodation")
public class AcomodationController {

    @Autowired
    private AccomodationService acomodationService;

    @GetMapping
    public String getAllAcommodations(Model model) {
        List<Accomodation> acomodations = acomodationService.getAllAccomodation();
        model.addAttribute("acommodation", acomodations);
        return "admin/acommodation/listAcommodation";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("acommodation", new Object());
        return "admin/acommodation/addAcommodation";
    }

    @PostMapping("/save")
    public String saveAcommodation(@ModelAttribute Accomodation acommodation) {
        acomodationService.createAcomodation(acommodation);
        return "redirect:/acommodation";
    }

    @GetMapping("/edit/{id}")
    public String editAcommodation(@PathVariable("id") int id, Model model) {
        Optional<Accomodation> acommodation = AccomodationService.getAccomodationById(id);
        if (acommodation.isPresent()) {
            model.addAttribute("acommodation", acommodation.get());
            return "admin/acommodation/editAcommodation";
        } else {
            return "redirect:/acommodation?error=notFound";
        }
    }

    @PostMapping("/update/{id}")
    public String updateAcommodation(@PathVariable("id") int id, @ModelAttribute Accomodation acommodation) {
        Accomodation updatedAcommodation = acomodationService.updateAccomodation(id, acommodation);
        if (updatedAcommodation != null) {
            return "redirect:/acommodation";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/{id}")
    public String getAcommodationById(@PathVariable("id") int id, Model model) {
        Optional<Accomodation> acommodation = AccomodationService.getAccomodationById(id);
        if (acommodation.isPresent()) {
            model.addAttribute("acommodation", acommodation.get());
            return "admin/acommodation/detailAcommodation";
        } else {
            return "redirect:/acommodation?error=notfound";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteAcommodation(@PathVariable("id") int id) {
        boolean isDeleted = acomodationService.deleteAccomodation(id);
        if (isDeleted) {
            return "redirect:/acommodation";
        } else {
            return "redirect:/acommodation?error=deleteFailed";
        }
    }
}
