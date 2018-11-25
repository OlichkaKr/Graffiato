package com.kryvoruchka.DTO;

import com.kryvoruchka.controller.CustomerController;
import com.kryvoruchka.domain.Clothes;
import com.kryvoruchka.domain.Maker;
import com.kryvoruchka.exceptions.NoSuchClothesException;
import com.kryvoruchka.exceptions.NoSuchCustomerException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClothesDTO extends ResourceSupport {
    Clothes clothes;
    public ClothesDTO(Clothes clothes, Link selfLink) throws NoSuchClothesException, NoSuchCustomerException {
        this.clothes = clothes;
        add(selfLink);
        add(linkTo(methodOn(CustomerController.class).getCustomerByClothesId(clothes.getId())).withRel("customers"));
    }

    public Long getClothesId() {
        return clothes.getId();
    }

    public String getType() {
        return clothes.getType();
    }

    public Long getAmount() {
        return clothes.getAmount();
    }

    public String getColor() {
        return clothes.getColor();
    }

    public String getSize() {
        return clothes.getSize();
    }

    public String getMaker() {
        return clothes.getMaker().getCompanyName();
    }
}
