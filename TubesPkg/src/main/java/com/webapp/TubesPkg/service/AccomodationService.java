package com.webapp.TubesPkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.Accomodation;
import com.webapp.TubesPkg.repository.AccomodationRepository;

@Service
public class AccomodationService {

    @Autowired
    private AccomodationRepository accomodationRepository;  // Repository untuk interaksi dengan database

    // Implementasi metode saveAccomodation
    public void saveAccomodation(Accomodation accomodation) {
        // Simpan data accomodation ke database
        accomodationRepository.save(accomodation);
    }
}
