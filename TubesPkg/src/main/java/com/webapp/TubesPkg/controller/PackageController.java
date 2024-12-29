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
        List<Transportation> transportations = transportationService.gettAllTransportation();
        List<Accomodation> accomodations = accomodationService.getAllAccomodations();
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
    
    
    

}
