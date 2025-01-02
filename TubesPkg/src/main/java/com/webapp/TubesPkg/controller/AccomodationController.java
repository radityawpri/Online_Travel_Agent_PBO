package com.webapp.TubesPkg.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.TubesPkg.models.Accomodation;
import com.webapp.TubesPkg.service.AccomodationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/accomodation")
public class AccomodationController {

    @Autowired
    private AccomodationService accomodationService;

    @GetMapping
    public String getAllAccomodation(Model model) {
        List<Accomodation> accomodations = accomodationService.getAllAccomodation();
        model.addAttribute("accomodation", accomodations);
        return "admin/accomodation/listAccomodation";
    }

    @GetMapping("/add")
        public String showAddForm(Model model) {
        model.addAttribute("accomodation", new Accomodation()); // Tambahkan objek model
        return "admin/accomodation/addAccomodation";
    }




    @PostMapping("/save")
    public String saveAccomodation(@ModelAttribute Accomodation accomodation, 
                                   @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path filePath = Paths.get("Online_Travel_Agent_PBO/TubesPkg/src/main/resources/static/image/", fileName);
            
                Files.createDirectories(filePath.getParent());

                // Save the image to the server
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Set the image URL or file path in the transportation entity
                accomodation.setImageUrl("/image/" + fileName);
                System.out.println("Image URL set to: " + accomodation.getImageUrl());
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/accomodation?error=uploadFailed";
            }
        }

        // Menyimpan objek accomodation ke database
        accomodationService.createAccomodation(accomodation);
        return "redirect:/accomodation";
    }

    @GetMapping("/edit/{id}")
    public String editAccomodation(@PathVariable("id") int id, Model model) {
        Optional<Accomodation> accomodation = accomodationService.getAccomodationById(id);
        if (accomodation.isPresent()) {
            model.addAttribute("accomodation", accomodation.get());
            return "admin/accomodation/editAccomodation";
        } else {
            return "redirect:/accomodation?error=notFound";
        }
    }

    @PostMapping("/update/{id}")
    public String updateAccomodation(@PathVariable("id") int id, @ModelAttribute Accomodation accomodation) {
        Accomodation updatedAccomodation = accomodationService.updateAccomodation(id, accomodation);
        if (updatedAccomodation != null) {
            return "redirect:/accomodation";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/{id}")
    public String getAccomodationById(@PathVariable("id") int id, Model model) {
        Optional<Accomodation> accomodation = accomodationService.getAccomodationById(id);
        if (accomodation.isPresent()) {
            model.addAttribute("accomodation", accomodation.get());
            return "admin/accomodation/detailAccomodation";
        } else {
            return "redirect:/accomodation?error=notFound";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteAccomodation(@PathVariable("id") int id) {
        boolean isDeleted = accomodationService.deleteAccomodation(id);
        if (isDeleted) {
            return "redirect:/accomodation";
        } else {
            return "redirect:/accomodation?error=deleteFailed";
        }
    }
}
