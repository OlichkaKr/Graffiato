package com.kryvoruchka.controller;

import com.kryvoruchka.DTO.ClothesDTO;
import com.kryvoruchka.domain.Clothes;
import com.kryvoruchka.exceptions.*;
import com.kryvoruchka.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ClothesController {
    @Autowired
    ClothesService clothesService;

    @GetMapping(value = "/api/clothes/maker/{company_name}")
    public ResponseEntity<List<ClothesDTO>> getClothesByMakersCompanyName(@PathVariable String company_name) throws
            NoSuchMakerException, NoSuchClothesException, NoSuchCustomerException {
        List<Clothes> clothesList = clothesService.getClothesByMakerCompanyName(company_name);

        Link link = linkTo(methodOn(ClothesController.class).getAllClothes()).withSelfRel();

        List<ClothesDTO> clothesDTO = new ArrayList<>();
        for (Clothes entity : clothesList){
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ClothesDTO dto = new ClothesDTO(entity, selfLink);
            clothesDTO.add(dto);
        }

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/clothes/{clothes_id}")
    public ResponseEntity<ClothesDTO> getClothes(@PathVariable Long clothes_id) throws NoSuchCustomerException,
            NoSuchClothesException {
        Clothes clothes = clothesService.getClothes(clothes_id);
        Link link = linkTo(methodOn(ClothesController.class).getClothes(clothes_id)).withSelfRel();

        ClothesDTO clothesDTO = new ClothesDTO(clothes, link);

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/clothes")
    public ResponseEntity<List<ClothesDTO>> getAllClothes() throws NoSuchClothesException, NoSuchCustomerException {
        List<Clothes> clothesList = clothesService.getAllClothes();
        Link link = linkTo(methodOn(ClothesController.class).getAllClothes()).withSelfRel();

        List<ClothesDTO> clothesDTO = new ArrayList<>();
        for (Clothes entity : clothesList){
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ClothesDTO dto = new ClothesDTO(entity, selfLink);
            clothesDTO.add(dto);
        }

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/clothes/customer/{customer_pass_name}")
    public ResponseEntity<List<ClothesDTO>> getClothesByCustomerPassName(@PathVariable Long customer_pass_name)
            throws NoSuchCustomerException, NoSuchClothesException {
        Set<Clothes> clothesList = clothesService.getClothesByCustomerPassName(customer_pass_name);
        Link link = linkTo(methodOn(ClothesController.class).getAllClothes()).withSelfRel();

        List<ClothesDTO> clothesDTO = new ArrayList<>();
        for (Clothes entity : clothesList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ClothesDTO dto = new ClothesDTO(entity, selfLink);
            clothesDTO.add(dto);
        }

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/clothes/maker/{company_name}")
    public ResponseEntity<ClothesDTO> addClothes(@RequestBody Clothes newClothes, @PathVariable String company_name)
            throws NoSuchMakerException, NoSuchClothesException, NoSuchCustomerException {
        clothesService.createClothes(newClothes, company_name);
        Link link = linkTo(methodOn(ClothesController.class).getClothes(newClothes.getId())).withSelfRel();

        ClothesDTO clothesDTO = new ClothesDTO(newClothes, link);

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/api/clothes/{clothes_id}/maker/{company_name}")
    public ResponseEntity<ClothesDTO> updateClothes(@RequestBody Clothes uClothes, @PathVariable Long clothes_id,
                                                    String company_name) throws NoSuchMakerException,
            NoSuchCustomerException, NoSuchClothesException {
        clothesService.updateClothes(uClothes, clothes_id, company_name);
        Clothes clothes = clothesService.getClothes(clothes_id);
        Link link = linkTo(methodOn(ClothesController.class).getClothes(clothes_id)).withSelfRel();

        ClothesDTO clothesDTO = new ClothesDTO(clothes, link);

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/clothes/{clothes_id}")
    public ResponseEntity deleteClothes(@PathVariable Long clothes_id) throws NoSuchClothesException,
            ExistsCustomerForClothesException {
        clothesService.deleteClothes(clothes_id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/clothes/{clothes_id}/customer/{customer_pass_name}")
    public ResponseEntity<ClothesDTO> addCustomerForClothes(@PathVariable Long clothes_id, @PathVariable Long
            customer_pass_name) throws NoSuchClothesException, NoSuchCustomerException,
            AlreadyExistsCustomerInClothesException, CustomerAbsentException {
        clothesService.addCustomerForClothes(clothes_id, customer_pass_name);
        Clothes clothes = clothesService.getClothes(clothes_id);
        Link link = linkTo(methodOn(ClothesController.class).getClothes(clothes_id)).withSelfRel();

        ClothesDTO clothesDTO = new ClothesDTO(clothes, link);

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/clothes/{clothes_id}/customer/{customer_pass_name}")
    public ResponseEntity<ClothesDTO> removeCustomerForClothes(@PathVariable Long clothes_id, @PathVariable Long
            customer_pass_name) throws NoSuchCustomerException, NoSuchClothesException, ClothesHasNotCustomerException {
        clothesService.removeCustomerForClothes(clothes_id, customer_pass_name);
        Clothes clothes = clothesService.getClothes(clothes_id);
        Link link = linkTo(methodOn(ClothesController.class).getClothes(clothes_id)).withSelfRel();

        ClothesDTO clothesDTO = new ClothesDTO(clothes, link);

        return new ResponseEntity<>(clothesDTO, HttpStatus.OK);
    }
}
