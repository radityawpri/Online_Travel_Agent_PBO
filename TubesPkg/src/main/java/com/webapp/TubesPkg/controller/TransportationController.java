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

import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.service.TransportationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.nio.file.Path;
import java.nio.file.Paths;





@Controller
@RequestMapping("/transportation")
public class TransportationController {

    
        @Autowired
        private TransportationService transportationService;

        @GetMapping
        public String getAllTransportation(Model models) {
            List<Transportation> transportations = transportationService.getAllTransportation();
            models.addAttribute("transportation", transportations);
            return "admin/transportation/listTransportation";
        }
    
        @GetMapping("/add")
        public String showAddForm(Model models) {
            models.addAttribute("transportation", new Transportation());
            return "admin/transportation/addTransportation";
        }
    
        @PostMapping("/save")
        public String saveTransportation(@ModelAttribute Transportation transportation, 
                                         @RequestParam("image") MultipartFile image) {
            // Handle image upload
            if (!image.isEmpty()) {
                try {
                    // Create a unique filename based on the original file name
                    String fileName = image.getOriginalFilename();
                    Path filePath = Paths.get("Online_Travel_Agent_PBO/TubesPkg/src/main/resources/static/image/", fileName);
                
                // Ensure the directory exists
                Files.createDirectories(filePath.getParent());

                // Save the image to the server
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Set the image URL or file path in the transportation entity
                transportation.setImageUrl("/image/" + fileName);
                System.out.println("Image URL set to: " + transportation.getImageUrl());

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/transportation?error=uploadFailed";
            }
        }

        // Save transportation object to the database
        transportationService.createTransportation(transportation); 
        return "redirect:/transportation";
    }

    @GetMapping("/edit/{id}")
    public String editTransportation(@PathVariable("id") int id, Model model) {
        Optional<Transportation> transportation = transportationService.getTransportationById(id);
        if (transportation.isPresent()){
            model.addAttribute("transportation", transportation.get());
            return "admin/transportation/editTransportation";
        }else{
            return "redirect:/transportation?error=notFound";
        }
    }

    @PostMapping("/update/{id}")
    public String updateTransportation(@PathVariable("id") int id, @ModelAttribute Transportation transportation) {
        //TODO: process POST request
        Transportation updateTransportation = transportationService.updateTransportation(id, transportation);
        if (updateTransportation != null){
            return "redirect:/transportation";
        }else {
            return "redirect:/error";
        }
    }

    @GetMapping("/{id}")
    public String getTransportationById(@PathVariable("id") int id, Model model) {
        Optional<Transportation> transportation = transportationService.getTransportationById(id);
        if (transportation.isPresent()) {
            model.addAttribute("transportation", transportation.get());
            return "admin/transportation/detailTransportation";
        } else {
            return "redirect:/transportation?error=notfound";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteTransportation(@PathVariable("id") int id) {
        //TODO: process POST request
        boolean isDelete = transportationService.deleteTransportation(id);
        if (isDelete){
            return "redirect:/transportation";
        }else {
            return "redirect:/transportation?error=deleteFailed";
        }
    }
}
