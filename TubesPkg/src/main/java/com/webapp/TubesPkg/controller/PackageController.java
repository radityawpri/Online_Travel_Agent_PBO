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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.TubesPkg.models.Accomodation;
import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.models.Wahana;
import com.webapp.TubesPkg.service.AccomodationService;
import com.webapp.TubesPkg.service.PackageTravelService;
import com.webapp.TubesPkg.service.TransportationService;
import com.webapp.TubesPkg.service.WahanaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;








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
        List<Transportation> transportations = transportationService.getAllTransportation();
        List<Accomodation> accomodations = accomodationService.getAllAccomodation();
        List<Wahana> wahana = wahanaService.getAllWahana();
        models.addAttribute("packageTravel", new PackageTravel());
        models.addAttribute("transportations", transportations);
        models.addAttribute("accomodation", accomodations);
        models.addAttribute("wahana", wahana);
        return "admin/package/addPackage";
    }

    @PostMapping("/save")
    public String savePackageTravel(@ModelAttribute PackageTravel packageTravel, 
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

            // Set the image URL or file path in the PackageTravel entity
            packageTravel.setImageUrl("/image/" + fileName);
            System.out.println("Image URL set to: " + packageTravel.getImageUrl());
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/packageTravel?error=uploadFailed";
        }
    }

    // Save PackageTravel object to the database
    packageTravelService.createPackageTravel(packageTravel); 
    return "redirect:/packagesTravel";
}


    @GetMapping("/edit/{id}")
    public String editPackage(@PathVariable("id") int id, Model model) {
        Optional<PackageTravel> packageTravel = packageTravelService.getPackageById(id);
        if (packageTravel.isPresent()){
            model.addAttribute("packagesTravel", packageTravel.get());
            model.addAttribute("transportations", transportationService.getAllTransportation());
            model.addAttribute("accomodation", accomodationService.getAllAccomodation());
            model.addAttribute("wahana", wahanaService.getAllWahana());
            return "admin/package/editPackage";
        }else {
            return "redirect:/packagesTravel?error=notFound";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePackage(@PathVariable("id") int id, @ModelAttribute PackageTravel packageTravel) {
        //TODO: process POST request
        PackageTravel updatePackageTravel = packageTravelService.updatePackageTravel(id, packageTravel);
        if (updatePackageTravel != null){
            return "redirect:/packagesTravel";
        }else {
            return "redirect:/error";
        }
    }

    @GetMapping("/{id}")
    public String getPackageById(@PathVariable("id") int id, Model model) {
        Optional<PackageTravel> packageTravel = packageTravelService.getPackageById(id);
        if (packageTravel.isPresent()) {
            model.addAttribute("packageTravel", packageTravel.get());
            return "admin/package/detail"; 
        } else {
            return "redirect:/packagesTravel?error=notfound";
        }
    }
    
    @PostMapping("/delete/{id}")
    public String deletePackages(@PathVariable("id") int id) {
        //TODO: process POST request
        boolean isDelete = packageTravelService.deletePackage(id);
        if (isDelete){
            return "redirect:/packagesTravel";
        }else {
            return "redirect:/packagesTravel?error=deleteFailed";
        }
    }
    

}
