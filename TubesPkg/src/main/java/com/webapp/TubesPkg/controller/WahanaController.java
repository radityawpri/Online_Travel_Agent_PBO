package com.webapp.TubesPkg.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.TubesPkg.models.Wahana;
import com.webapp.TubesPkg.service.WahanaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/wahana")
public class WahanaController {

    @Autowired
    private WahanaService wahanaService;

    @GetMapping
    public String getAllWahana(Model models) {
        List<Wahana> wahana = wahanaService.getAllWahana();
        models.addAttribute("wahana", wahana);
        return "admin/wahana/listWahana";
    }

    @GetMapping("/add")
    public String showAddForm(Model models) {
        models.addAttribute("wahana", new Wahana());
        return "admin/wahana/addWahana";
    }

    @PostMapping("/save")
    public String saveWahana(@ModelAttribute Wahana wahana, 
                              @RequestParam("image") MultipartFile image) {
        // Handle image upload
        if (!image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path filePath = Paths.get("Online_Travel_Agent_PBO/TubesPkg/src/main/resources/static/image/", fileName);

                // Ensure the directory exists
                Files.createDirectories(filePath.getParent());

                // Save the image to the server
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Set the image URL or file path in the wahana entity
                wahana.setImageUrl("/image/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/wahana?error=uploadFailed";
            }
        }

        // Save wahana object to the database
        wahanaService.createWahana(wahana);
        return "redirect:/wahana";
    }

    @GetMapping("/edit/{id}")
    public String editWahana(@PathVariable("id") int id, Model model) {
        Optional<Wahana> wahana = wahanaService.getWahanaById(id);
        if (wahana.isPresent()) {
            model.addAttribute("wahana", wahana.get());
            return "admin/wahana/editWahana";
        } else {
            return "redirect:/wahana?error=notFound";
        }
    }

    @PostMapping("/update/{id}")
    public String updateWahana(@PathVariable("id") int id, 
                                @ModelAttribute Wahana wahana, 
                                @RequestParam("image") MultipartFile image) {
        // Handle image upload
        if (!image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path filePath = Paths.get("Online_Travel_Agent_PBO/TubesPkg/src/main/resources/static/image/", fileName);

                // Ensure the directory exists
                Files.createDirectories(filePath.getParent());

                // Save the image to the server
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Set the image URL or file path in the wahana entity
                wahana.setImageUrl("/image/" + fileName);
                System.out.println("Updated Image URL set to: " + wahana.getImageUrl());
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/wahana?error=uploadFailed";
            }
        }

        Wahana updatedWahana = wahanaService.updateWahana(id, wahana);
        if (updatedWahana != null) {
            return "redirect:/wahana";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/{id}")
    public String getWahanaById(@PathVariable("id") int id, Model model) {
        Optional<Wahana> wahana = wahanaService.getWahanaById(id);
        if (wahana.isPresent()) {
            model.addAttribute("wahana", wahana.get());
            return "admin/wahana/detailWahana";
        } else {
            return "redirect:/wahana?error=notfound";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteWahana(@PathVariable("id") int id) {
        boolean isDelete = wahanaService.deleteWahana(id);
        if (isDelete) {
            return "redirect:/wahana";
        } else {
            return "redirect:/wahana?error=deleteFailed";
        }
    }
}
