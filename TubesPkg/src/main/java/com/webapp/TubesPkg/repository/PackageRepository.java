package com.webapp.TubesPkg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webapp.TubesPkg.models.PackageTravel;

import jakarta.transaction.Transactional;

public interface PackageRepository extends JpaRepository<PackageTravel, Integer>{

    List<PackageTravel> findByAccomodationId(int accomodationId);

    List<PackageTravel> findByTransportationId(int transportationId);

    List<PackageTravel> findByWahanaId(int wahanaId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PackageTravel p WHERE p.accomodation.id = :accomodationId ")
    void deleteByAccomodationId(@Param("accomodationId") int accomodationId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PackageTravel p WHERE p.transportation.id = :transportationId ")
    void deleteByTransportationId(@Param("transportationId") int transportationId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PackageTravel p WHERE p.wahana.id = :wahanId")
    void deleteByWahanaId(@Param("wahanaId") int wahanaId);
} 
