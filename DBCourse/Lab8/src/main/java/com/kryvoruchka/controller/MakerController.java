package com.kryvoruchka.controller;

import com.kryvoruchka.DTO.MakerDTO;
import com.kryvoruchka.domain.Maker;
import com.kryvoruchka.exceptions.ExistsClothesForMakerException;
import com.kryvoruchka.exceptions.NoSuchClothesException;
import com.kryvoruchka.exceptions.NoSuchCustomerException;
import com.kryvoruchka.exceptions.NoSuchMakerException;
import com.kryvoruchka.service.MakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MakerController {
    @Autowired
    MakerService makerService;

    @GetMapping(value = "api/maker")
    public ResponseEntity<List<MakerDTO>> getAllMakers() throws NoSuchClothesException, NoSuchCustomerException,
            NoSuchMakerException {
        List<Maker> makerList = makerService.getAllMakers();
        Link link = linkTo(methodOn(MakerController.class).getAllMakers()).withSelfRel();

        List<MakerDTO> makersDTO = new ArrayList<>();
        for (Maker entity : makerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getCompanyName()).withSelfRel();
            MakerDTO dto = new MakerDTO(entity, selfLink);
            makersDTO.add(dto);
        }

        return new ResponseEntity<>(makersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/maker/{company_name}")
    public ResponseEntity<MakerDTO> getMaker(@PathVariable String company_name) throws NoSuchMakerException,
            NoSuchCustomerException, NoSuchClothesException {
        Maker maker = makerService.getMaker(company_name);
        Link link = linkTo(methodOn(MakerController.class).getMaker(company_name)).withSelfRel();

        MakerDTO makerDTO = new MakerDTO(maker, link);

        return new ResponseEntity<>(makerDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/maker/{company_name}")
    public ResponseEntity<MakerDTO> addMaker(@RequestBody Maker newMaker) throws NoSuchMakerException,
            NoSuchClothesException, NoSuchCustomerException {
        makerService.createMaker(newMaker);
        Link link = linkTo(methodOn(MakerController.class).getMaker(newMaker.getCompanyName())).withSelfRel();

        MakerDTO makerDTO = new MakerDTO(newMaker, link);

        return new ResponseEntity<>(makerDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/maker/{company_name}")
    public ResponseEntity<MakerDTO> updateMaker(@RequestBody Maker uMaker, @PathVariable String company_name) throws
            NoSuchCustomerException, NoSuchClothesException, NoSuchMakerException {
        makerService.updateMaker(uMaker, company_name);
        Maker maker = makerService.getMaker(company_name);
        Link link = linkTo(methodOn(MakerController.class).getMaker(company_name)).withSelfRel();

        MakerDTO makerDTO = new MakerDTO(maker, link);

        return new ResponseEntity<>(makerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/maker/{company_name}")
    public ResponseEntity deleteMaker(@PathVariable String company_name) throws NoSuchMakerException,
            ExistsClothesForMakerException {
        makerService.deleteMaker(company_name);

        return new ResponseEntity(HttpStatus.OK);
    }
}
