
package com.webapp.TubesPkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.repository.PackageTravelRepository;
import com.webapp.TubesPkg.repository.TransportationRepository;

@Service
public class TransportationService {
    @Autowired
    private TransportationRepository transportationRepository;
    @Autowired
    private PackageTravelRepository packageTravelRepository;
  
    // Implementasi metode saveAccomodation
    public void saveTransportation(Transportation transportation, PackageTravel packageTravel) {
      // Simpan Transportation terlebih dahulu
      Transportation savedTransportation = transportationRepository.save(transportation);

      // Hubungkan Transportation ke PackageTravel
      packageTravel.setTransportation(savedTransportation);

      // Simpan PackageTravel
      packageTravelRepository.save(packageTravel);
    }
    
}