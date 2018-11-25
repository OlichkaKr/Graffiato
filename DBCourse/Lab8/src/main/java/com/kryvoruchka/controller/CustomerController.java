package com.kryvoruchka.controller;

import com.kryvoruchka.DTO.CustomerDTO;
import com.kryvoruchka.domain.Customer;
import com.kryvoruchka.exceptions.ExistsClothesForCustomerException;
import com.kryvoruchka.exceptions.NoSuchClothesException;
import com.kryvoruchka.exceptions.NoSuchCustomerException;
import com.kryvoruchka.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/api/customer/clothes/{clothes_id}")
    public ResponseEntity<List<CustomerDTO>> getCustomerByClothesId(@PathVariable Long clothes_id) throws
            NoSuchClothesException, NoSuchCustomerException {
        Set<Customer> customerList = customerService.getCustomerByClothesId(clothes_id);
        Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();

        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer entity : customerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getPassName()).withSelfRel();
            CustomerDTO dto = new CustomerDTO(entity, selfLink);
            customersDTO.add(dto);
        }

        return new ResponseEntity<>(customersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/customer/{customer_pass_name}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long customer_pass_name) throws
            NoSuchCustomerException, NoSuchClothesException {
        Customer customer = customerService.getCustomer(customer_pass_name);
        Link link = linkTo(methodOn(CustomerController.class).getCustomer(customer_pass_name)).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/customer")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws NoSuchCustomerException, NoSuchClothesException {
        List<Customer> customerList = customerService.getAllCustomers();
        Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();

        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer entity : customerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getPassName()).withSelfRel();
            CustomerDTO dto = new CustomerDTO(entity, selfLink);
            customersDTO.add(dto);
        }

        return new ResponseEntity<>(customersDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/customer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer newCustomer) throws NoSuchCustomerException,
            NoSuchClothesException {
        customerService.createCustomer(newCustomer);
        Link link = linkTo(methodOn(CustomerController.class).getCustomer(newCustomer.getPassName())).withSelfRel();
        CustomerDTO customerDTO = new CustomerDTO(newCustomer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/customer/{customer_pass_name}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody Customer uCustomer, @PathVariable Long
            customer_pass_name) throws NoSuchCustomerException, NoSuchClothesException {
        customerService.updateCustomer(uCustomer, customer_pass_name);
        Customer customer = customerService.getCustomer(customer_pass_name);
        Link link = linkTo(methodOn(CustomerController.class).getCustomer(customer_pass_name)).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/customer/{customer_pass_name}")
    public ResponseEntity deleteCustomer(@PathVariable Long customer_pass_name) throws
            ExistsClothesForCustomerException, NoSuchCustomerException {
        customerService.deleteCustomer(customer_pass_name);

        return new ResponseEntity(HttpStatus.OK);
    }
}
