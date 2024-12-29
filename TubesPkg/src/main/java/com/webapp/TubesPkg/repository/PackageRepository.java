package com.webapp.TubesPkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.TubesPkg.models.PackageTravel;

public interface PackageRepository extends JpaRepository<PackageTravel, Integer>{
} 
