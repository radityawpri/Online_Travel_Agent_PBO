package com.webapp.TubesPkg.controller;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/indexAdmin"; 
    }

    @GetMapping("/admin/dashboard/details")
    public String adminDashboardWithDetails(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    if (!userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_admin"))) {
        return "redirect:/"; 
    }
    model.addAttribute("error", false);
    return "admin/indexAdmin";
    }
}