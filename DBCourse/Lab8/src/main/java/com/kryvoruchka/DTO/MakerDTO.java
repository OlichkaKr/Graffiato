package com.kryvoruchka.DTO;

import com.kryvoruchka.controller.ClothesController;
import com.kryvoruchka.domain.Maker;
import com.kryvoruchka.exceptions.NoSuchClothesException;
import com.kryvoruchka.exceptions.NoSuchCustomerException;
import com.kryvoruchka.exceptions.NoSuchMakerException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MakerDTO extends ResourceSupport{
    Maker maker;
    public MakerDTO(Maker maker, Link selfLink) throws NoSuchClothesException, NoSuchCustomerException,
            NoSuchMakerException {
        this.maker = maker;
        add(selfLink);
        add(linkTo(methodOn(ClothesController.class).getClothesByMakersCompanyName(maker.getCompanyName())).withRel
                ("clothes"));
    }

    public String getCompanyName() {
        return maker.getCompanyName();
    }

    public String getFirstName() {
        return maker.getFirstName();
    }

    public String getLastName() {
        return maker.getLastName();
    }

    public Long getPrice() {
        return maker.getPrice();
    }
}
