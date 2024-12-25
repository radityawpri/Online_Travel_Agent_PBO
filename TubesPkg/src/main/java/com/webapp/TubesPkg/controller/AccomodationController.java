package com.webapp.TubesPkg.controller;

import com.webapp.TubesPkg.models.Accomodation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.webapp.TubesPkg.service.AccomodationService;

@Controller
public class AccomodationController {

    @GetMapping("/addAccomodation")
    public String showAddAccommodationForm(Model models) {
        // Membuat objek 'accomodation' dan menambahkannya ke model
        Accomodation accomodation = new Accomodation();
        models.addAttribute("accomodation", accomodation);  // Pastikan nama 'accomodation' sesuai dengan yang ada di HTML

        return "addAccomodation";  // Nama template yang akan dirender
    }


    @Autowired
    private AccomodationService accomodationService;

   
    // Menyimpan data accomodation
    @PostMapping("/saveAccomodation")
    public String saveAccomodation(@ModelAttribute Accomodation accomodation) {
        // Memanggil metode saveAccomodation di service
        accomodationService.saveAccomodation(accomodation); 
        return "redirect:/index";  // Setelah disimpan, redirect ke halaman index
    }
}







