package com.webapp.TubesPkg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// CONTROLLER INI UNTUK BERFUNGSI UNTUK MENAMPILKAN ATAU MENGEMBALIKAN KE HALAMAN UTAMA WEB

@Controller
public class RegisterLoginController {

    @GetMapping({"/", "/index"})
    public String home() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    
    
}