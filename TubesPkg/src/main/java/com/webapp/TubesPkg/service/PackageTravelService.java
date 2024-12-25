package com.webapp.TubesPkg.service;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.repository.PackageTravelRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PackageTravelService {
    @Autowired
    private PackageTravelRepository packageTravelRepository;

    public PackageTravelService(PackageTravelRepository packageTravelRepository) {
        this.packageTravelRepository = packageTravelRepository;
    }

    public List<PackageTravel> getAllPackages() {
        return packageTravelRepository.findAll();
    }

    public void savePackage(PackageTravel packageTravel) {
        packageTravelRepository.save(packageTravel);
    }

    public PackageTravel getPackageById(Long id) {
        // Ambil data berdasarkan ID termasuk relasi
        return packageTravelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PackageTravel not found with id: " + id));
    }

    
}
