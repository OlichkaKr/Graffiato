package com.kryvoruchka.service;

import com.kryvoruchka.Repository.ClothesRepository;
import com.kryvoruchka.Repository.CustomerRepository;
import com.kryvoruchka.domain.Clothes;
import com.kryvoruchka.domain.Customer;
import com.kryvoruchka.exceptions.ExistsClothesForCustomerException;
import com.kryvoruchka.exceptions.NoSuchClothesException;
import com.kryvoruchka.exceptions.NoSuchCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ClothesRepository clothesRepository;

    public Set<Customer> getCustomerByClothesId(Long clothes_id) throws NoSuchClothesException {
        Clothes clothes = clothesRepository.findById(clothes_id).get();
        if (clothes == null) throw new NoSuchClothesException();
        return clothes.getCustomers();
    }

    public Customer getCustomer(Long customer_pass_name) throws NoSuchCustomerException {
        Customer customer = customerRepository.findById(customer_pass_name).get();
        if (customer == null) throw new NoSuchCustomerException();
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Customer uCustomer, Long customer_pass_name) throws NoSuchCustomerException {
        Customer customer = customerRepository.findById(customer_pass_name).get();
        if (customer == null) throw new NoSuchCustomerException();

        customer.setAdress(uCustomer.getAdress());
        customer.setFirstName(uCustomer.getFirstName());
        customer.setLastName(uCustomer.getLastName());
        customer.setPayment(uCustomer.getPayment());
    }

    @Transactional
    public void deleteCustomer(Long customer_pass_name) throws NoSuchCustomerException,
            ExistsClothesForCustomerException {
        Customer customer = customerRepository.findById(customer_pass_name).get();
        if (customer == null) throw new NoSuchCustomerException();
        if (customer.getClothes().size() != 0) throw new ExistsClothesForCustomerException();
        customerRepository.delete(customer);
    }
}
