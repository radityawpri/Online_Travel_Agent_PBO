package com.webapp.TubesPkg.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String savePackage(@ModelAttribute PackageTravel packageTravel) {
        //TODO: process POST request
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
