package com.webapp.TubesPkg.service;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.repository.PackageTravelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageTravelService {

    private final PackageTravelRepository packageTravelRepository;

    public PackageTravelService(PackageTravelRepository packageTravelRepository) {
        this.packageTravelRepository = packageTravelRepository;
    }

    public List<PackageTravel> getAllPackages() {
        return packageTravelRepository.findAll();
    }

    public void savePackage(PackageTravel packageTravel) {
        packageTravelRepository.save(packageTravel);
    }
}
