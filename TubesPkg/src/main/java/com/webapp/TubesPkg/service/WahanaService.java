package com.webapp.TubesPkg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.models.Wahana;
import com.webapp.TubesPkg.repository.PackageRepository;
import com.webapp.TubesPkg.repository.WahanaRepository;

@Service
public class WahanaService {
    
    @Autowired
    private WahanaRepository wahanaRepository;

    @Autowired
    private PackageRepository packageRepository;

    public List<Wahana> getAllWahana(){
        return wahanaRepository.findAll();
    }

    public Optional<Wahana> getWahanaById(int id){
        return wahanaRepository.findById(id);
    }

    public Wahana createWahana(Wahana wahana){
        return wahanaRepository.save(wahana);
    }

    public Wahana updateWahana(int id, Wahana wahana){
        if (wahanaRepository.existsById(id)){
            wahana.setId(id);
            return wahanaRepository.save(wahana);
        }
        return null;
    }

    public boolean deleteWahana(int id){
        List<PackageTravel> packages = packageRepository.findByWahanaId(id);
        for (PackageTravel pkg : packages){
            pkg.setWahana(null);
            packageRepository.save(pkg);
        }

        wahanaRepository.deleteById(id);
        return true;
    }
}
