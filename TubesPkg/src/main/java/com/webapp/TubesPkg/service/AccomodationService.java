package com.webapp.TubesPkg.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.Accomodation;
import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.repository.AccomodationRepository;
import com.webapp.TubesPkg.repository.PackageRepository;

@Service
public class AccomodationService {
    
    @Autowired
    private AccomodationRepository accomodationRepository;

    @Autowired
    private PackageRepository packageRepository;

    public List<Accomodation> getAllAccomodation(){
        return accomodationRepository.findAll();
    }

    public Optional<Accomodation> getAccomodationById(int id){
        return accomodationRepository.findById(id);
    }

    public Accomodation createAccomodation(Accomodation accomodation){
        return accomodationRepository.save(accomodation);
    }

    public Accomodation updateAccomodation(int id, Accomodation accomodation){
        if (accomodationRepository.existsById(id)){
            accomodation.setId(id);
            return accomodationRepository.save(accomodation);
        }
        return null;
    }

    public boolean deleteAccomodation(int id){
        List<PackageTravel> packages = packageRepository.findByAccomodationId(id);
        for (PackageTravel pkg : packages){
            pkg.setAccomodation(null);
            packageRepository.save(pkg);
        }   

        accomodationRepository.deleteById(id);
        return true;
    }
}
