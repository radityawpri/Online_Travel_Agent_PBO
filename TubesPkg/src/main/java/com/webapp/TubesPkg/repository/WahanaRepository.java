package com.webapp.TubesPkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.TubesPkg.models.Wahana;

@Repository
public interface WahanaRepository extends JpaRepository<Wahana, Integer>{
}
