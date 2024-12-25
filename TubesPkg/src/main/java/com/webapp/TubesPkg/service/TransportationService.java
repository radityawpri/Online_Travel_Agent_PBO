
package com.webapp.TubesPkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webapp.TubesPkg.models.Transportation;
import com.webapp.TubesPkg.repository.TransportationRepository;

@Service
public class TransportationService {
    @Autowired
    private TransportationRepository transportationRepository;

  
    // Implementasi metode saveAccomodation
    public void saveTransportation(Transportation transportation) {
        // Simpan data accomodation ke database
        transportationRepository.save(transportation);
    }
}