package com.webapp.TubesPkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.TubesPkg.models.Accomodation;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Integer>{    
}
