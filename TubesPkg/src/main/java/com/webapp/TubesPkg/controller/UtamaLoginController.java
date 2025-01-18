package com.webapp.TubesPkg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// JADI CONTROLLER INI BERFUNGSI UNTUK MENAMPILKAN DARI HALAMAN /UTAMALOGIN

@Controller
public class UtamaLoginController { 
    @GetMapping("/UtamaLogin")
    public String showLoginPage() {
        return "Utamalogin";
    }
}
