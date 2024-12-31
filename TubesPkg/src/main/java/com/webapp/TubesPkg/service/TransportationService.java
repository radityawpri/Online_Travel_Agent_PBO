package com.webapp.TubesPkg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.repository.PackageRepository;
import com.webapp.TubesPkg.repository.TransportationRepository;

@Service
public class TransportationService {
    
    @Autowired
    private TransportationRepository transportationRepository;

    @Autowired
    private PackageRepository packageRepository;

    public List<Transportation> getAllTransportation(){
        return transportationRepository.findAll();
    }

    public Optional<Transportation> getTransportationById(int id){
        return transportationRepository.findById(id);
    }

    public Transportation createTransportation(Transportation transportation){
        return transportationRepository.save(transportation);
    }

    public Transportation updateTransportation(int id, Transportation transportation){
        if (transportationRepository.existsById(id)){
            transportation.setId(id);
            return transportationRepository.save(transportation);
        }
        return null;
    }

    public boolean deleteTransportation(int id){
        List<PackageTravel> pcakages = packageRepository.findByTransportationId(id);
        for (PackageTravel pkg : pcakages){
            pkg.setTransportation(null);
            packageRepository.save(pkg);
        }

        transportationRepository.deleteById(id);
        return true;
    }

}
