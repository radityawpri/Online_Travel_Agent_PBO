package com.webapp.TubesPkg.controller;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.service.PackageTravelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class PackageTravelController {
    
    private final PackageTravelService packageTravelService;

    public PackageTravelController(PackageTravelService packageTravelService){
        this.packageTravelService = packageTravelService;
    }

    @GetMapping("/")
        public String viewHomePage(Model models) {
            List<PackageTravel> pkg =packageTravelService.getAllPackages();
            models.addAttribute("packages", pkg != null ? pkg : List.of());
            return "index";
        }
    
}


