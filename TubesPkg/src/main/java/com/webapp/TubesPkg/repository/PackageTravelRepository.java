package com.webapp.TubesPkg.repository;

import com.webapp.TubesPkg.models.PackageTravel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageTravelRepository extends JpaRepository<PackageTravel, Long> {
    Optional<PackageTravel>findById(Long id);
}
