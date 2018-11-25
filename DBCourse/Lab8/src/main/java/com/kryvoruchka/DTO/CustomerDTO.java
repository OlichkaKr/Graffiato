package com.kryvoruchka.DTO;

import com.kryvoruchka.controller.ClothesController;
import com.kryvoruchka.domain.Customer;
import com.kryvoruchka.exceptions.NoSuchClothesException;
import com.kryvoruchka.exceptions.NoSuchCustomerException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CustomerDTO extends ResourceSupport {
    Customer customer;
    public CustomerDTO(Customer customer, Link selfLink) throws NoSuchCustomerException, NoSuchClothesException {
        this.customer = customer;
        add(selfLink);
        add(linkTo(methodOn(ClothesController.class).getClothesByCustomerPassName(customer.getPassName())).withRel
                ("clothes"));
    }

    public Long getCustomerPassName() {
        return customer.getPassName();
    }

    public String getFirstName() {
        return customer.getFirstName();
    }

    public String getLastName() {
        return customer.getLastName();
    }

    public String getAdress() {
        return customer.getAdress();
    }

    public String getPayment() {
        return customer.getPayment();
    }
}
