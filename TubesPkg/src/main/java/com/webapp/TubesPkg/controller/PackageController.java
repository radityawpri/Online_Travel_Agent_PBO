package com.webapp.TubesPkg.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.service.AccomodationService;
import com.webapp.TubesPkg.service.PackageTravelService;
import com.webapp.TubesPkg.service.TransportationService;
import com.webapp.TubesPkg.service.WahanaService;

@Controller
@RequestMapping("/packagesTravel")
public class PackageController {

    @Autowired
    private PackageTravelService packageTravelService;

    @Autowired
    private TransportationService transportationService;

    @Autowired
    private AccomodationService accomodationService;

    @Autowired
    private WahanaService wahanaService;

    @GetMapping
    public String getAllPackage(Model models) {
        List<PackageTravel> packageTravel = packageTravelService.getAllPackages();
        models.addAttribute("packagesTravel", packageTravel);
        return "admin/package/listPackage";
    }

    @GetMapping("/add")
    public String createPackageTravel(Model models) {
        models.addAttribute("packageTravel", new PackageTravel());
        models.addAttribute("transportations", transportationService.getAllTransportation());
        models.addAttribute("accomodation", accomodationService.getAllAccomodation());
        models.addAttribute("wahana", wahanaService.getAllWahana());
        return "admin/package/addPackage";
    }

    @PostMapping("/save")
    public String savePackageTravel(@ModelAttribute PackageTravel packageTravel,
                                    @RequestParam("image") MultipartFile image,
                                    Model model) {
        if (!image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path filePath = Paths.get("TubesPkg/src/main/resources/static/image/", fileName);
                Files.createDirectories(filePath.getParent());
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                packageTravel.setImageUrl("/image/" + fileName);
            } catch (IOException e) {
                model.addAttribute("error", "File upload failed.");
                return "admin/package/addPackage";
            }
        }
        packageTravelService.createPackageTravel(packageTravel);
        return "redirect:/packagesTravel";
    }

    @GetMapping("/edit/{id}")
    public String editPackage(@PathVariable("id") int id, Model model) {
        PackageTravel packageTravel = packageTravelService.getPackageById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        model.addAttribute("packagesTravel", packageTravel);
        model.addAttribute("transportations", transportationService.getAllTransportation());
        model.addAttribute("accomodation", accomodationService.getAllAccomodation());
        model.addAttribute("wahana", wahanaService.getAllWahana());
        return "admin/package/editPackage";
    }

    @PostMapping("/update/{id}")
    public String updatePackage(@PathVariable("id") int id,
                                @ModelAttribute PackageTravel packageTravel,
                                @RequestParam(value = "image", required = false) MultipartFile image,
                                Model model) {
        if (image != null && !image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path filePath = Paths.get("Online_Travel_Agent_PBO/TubesPkg/src/main/resources/static/image/", fileName, fileName);
                Files.createDirectories(filePath.getParent());
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                packageTravel.setImageUrl("/image/" + fileName);
            } catch (IOException e) {
                model.addAttribute("error", "File upload failed.");
                return "admin/package/editPackage";
            }
        }
        packageTravelService.updatePackageTravel(id, packageTravel);
        return "redirect:/packagesTravel";
    }

    @GetMapping("/{id}")
    public String getPackageById(@PathVariable("id") int id, Model model) {
        PackageTravel packageTravel = packageTravelService.getPackageById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        model.addAttribute("packageTravel", packageTravel);
        return "admin/package/detail";
    }

    @PostMapping("/delete/{id}")
    public String deletePackages(@PathVariable("id") int id) {
        if (packageTravelService.deletePackage(id)) {
            return "redirect:/packagesTravel";
        }
        return "redirect:/packagesTravel?error=deleteFailed";
    }
}
