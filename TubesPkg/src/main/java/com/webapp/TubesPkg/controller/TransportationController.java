package com.webapp.TubesPkg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.service.TransportationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/transportation")
public class TransportationController {

    @Autowired
    private TransportationService transportationService;

    @GetMapping
    public String getAllTransportation(Model models) {
        List<Transportation> transportations = transportationService.gettAllTransportation();
        models.addAttribute("transportation", transportations);
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model models) {
        models.addAttribute("transportation", new Transportation());
        return "admin/transportation/addTransportation";
    }

    @PostMapping("/save")
    public String saveTransportation(@ModelAttribute Transportation transportation) {
        transportationService.createTransportation(transportation);
        return "redirect:/transportation";
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
}
