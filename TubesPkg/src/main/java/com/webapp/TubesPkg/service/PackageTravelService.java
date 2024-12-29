package com.webapp.TubesPkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.repository.PackageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PackageTravelService {
    
    @Autowired
    private PackageRepository packageRepository;

    public List<PackageTravel> getAllPackages(){
        return packageRepository.findAll();
    }

    public Optional<PackageTravel> getPackageById(int id){
        return packageRepository.findById(id);
    }

    public PackageTravel createPackageTravel(PackageTravel packageTravel){
        return packageRepository.save(packageTravel);
    }

    public PackageTravel updatePackageTravel(int id, PackageTravel packageTravel){
        if (packageRepository.existsById(id)){
            packageTravel.setId(id);
            return packageRepository.save(packageTravel);
        }
        return null;
    }

    public boolean deletePackage(int id){
        if (packageRepository.existsById(id)){
            packageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
