package com.webapp.TubesPkg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.TubesPkg.models.BookingOrder;
import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.repository.PackageRepository;
import com.webapp.TubesPkg.service.BookingOrderService;
import com.webapp.TubesPkg.service.PackageTravelService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;




// CONTROLLER INI BERFUNGSI UNTUK MENAMPILKAN DARI HALAMAN USER HOME
@Controller
@RequestMapping("/user/home")
public class UserController {

    @Autowired
    private PackageTravelService packageTravelService;

    @Autowired
    private BookingOrderService bookingOrderService;

    @Autowired
    private PackageRepository packageRepository;

    // Menampilkan halaman utama dengan daftar paket
    @GetMapping
    public String userHome(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        List<PackageTravel> packageTravel = packageTravelService.getAllPackages();
        model.addAttribute("packagesTravel", packageTravel);
        return "user/indexUser"; 
    }

    // Menampilkan detail paket berdasarkan id
    @GetMapping("/viewPackage/{id}")
    public String getDetailPackage(Model model, 
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   @PathVariable("id") int id) {
        model.addAttribute("username", userDetails.getUsername());
        PackageTravel packagesTravel = packageTravelService.getPackageById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        model.addAttribute("packagesTravel", packagesTravel);
        return "user/packageUser";
    }

    @GetMapping("/show/{packageId}")
    public String showFormBooking(@PathVariable("packageId") int packageId, Model model) {
        PackageTravel packagesTravel = packageRepository.findById(packageId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Package ID"));

        model.addAttribute("packagesTravel", packagesTravel);
        return "user/addBookingOrder"; // View untuk form
    }

    
    // Menyimpan pemesanan dan melakukan redirect ke halaman detail paket setelah berhasil
    @PostMapping("/save/{packageId}")
    public String saveBooking(
        @PathVariable int packageId,
        @RequestParam String userName,
        @RequestParam String userEmail,
        @RequestParam String noTelp,
        @RequestParam String paymentMethod,
        Model model) {

        // Ambil data PackageTravel berdasarkan packageId
        PackageTravel packageTravel = packageRepository.findById(packageId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Package ID"));

        // Buat booking order dengan data yang diterima dari form
        BookingOrder bookingOrder = bookingOrderService.createBookingOrder(packageTravel, userName, userEmail, noTelp, paymentMethod);

        // Menambahkan booking order ke model
        model.addAttribute("bookingOrder", bookingOrder);

        // Redirect ke halaman detail package yang telah berhasil di-booking
        return "redirect:/user/home"; // Redirect ke halaman detail paket
    }
}
