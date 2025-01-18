package com.webapp.TubesPkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.Accomodation;
import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.models.Wahana;
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

    private double calculateTotalPrice(Transportation transportation, Accomodation accomodation, Wahana wahana) {
        double transportationPrice = transportation != null ? transportation.getPrice() : 0;
        double accomodationPrice = accomodation != null ? accomodation.getPrice() : 0;
        double wahanaPrice = wahana != null ? wahana.getPrice() : 0;
        return transportationPrice + accomodationPrice + wahanaPrice;
    }


    public PackageTravel createPackageTravel(PackageTravel packageTravel){
        double totalPrice = calculateTotalPrice(
            packageTravel.getTransportation(),
            packageTravel.getAccomodation(),
            packageTravel.getWahana()
        );
        packageTravel.setTotalPrice(totalPrice);
        return packageRepository.save(packageTravel);
    }

    public PackageTravel updatePackageTravel(int id, PackageTravel packageTravel){
        if (packageRepository.existsById(id)) {
            double totalPrice = calculateTotalPrice(
                packageTravel.getTransportation(),
                packageTravel.getAccomodation(),
                packageTravel.getWahana()
            );
            packageTravel.setId(id);
            packageTravel.setTotalPrice(totalPrice);
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
