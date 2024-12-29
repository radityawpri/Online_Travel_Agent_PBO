package com.webapp.TubesPkg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model models) {
        models.addAttribute("wahana", new Wahana());
        return "admin/wahana/addWahana";
    }

    @PostMapping("/save")
    public String saveWahana(@ModelAttribute Wahana wahana) {
        wahanaService.createWahana(wahana);
        return "redirect:/wahana";
    }

    @GetMapping("/{id}")
    public String getWahanaById(@PathVariable("id") int id, Model model) {
        Optional<Wahana> wahana = wahanaService.getWahanaById(id);
        if (wahana.isPresent()){
            model.addAttribute("wahana", wahana);
            return "admin/wahana/detailWahana";
        }else {
            return "redirect:/wahana?error=notfound";
        }
    }   
}
