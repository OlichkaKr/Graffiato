package com.kryvoruchka.service;

import com.kryvoruchka.Repository.ClothesRepository;
import com.kryvoruchka.Repository.MakerRepository;
import com.kryvoruchka.domain.Maker;
import com.kryvoruchka.exceptions.ExistsClothesForMakerException;
import com.kryvoruchka.exceptions.NoSuchMakerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MakerService {
    @Autowired
    MakerRepository makerRepository;
    private boolean ascending;

    @Autowired
    ClothesRepository clothesRepository;

    public List<Maker> getAllMakers() {
        return makerRepository.findAll();
    }

    public Maker getMaker(String company_name) throws NoSuchMakerException {
        Maker maker = makerRepository.findById(company_name).get();
        if (maker == null) throw new NoSuchMakerException();
        return maker;
    }

    @Transactional
    public void createMaker(Maker maker) {
        makerRepository.save(maker);
    }

    @Transactional
    public void updateMaker(Maker uMaker, String company_name) throws NoSuchMakerException {
        Maker maker = makerRepository.findById(company_name).get();
        if (maker == null) throw new NoSuchMakerException();
        maker.setFirstName(uMaker.getFirstName());
        maker.setLastName(uMaker.getLastName());
        maker.setPrice(uMaker.getPrice());
        makerRepository.save(maker);
    }

    @Transactional
    public void deleteMaker(String company_name) throws NoSuchMakerException, ExistsClothesForMakerException {
        Maker maker = makerRepository.findById(company_name).get();
        if (maker == null) throw new NoSuchMakerException();
        if (maker.getClothes().size() != 0) throw new ExistsClothesForMakerException();
        makerRepository.delete(maker);
    }
}
