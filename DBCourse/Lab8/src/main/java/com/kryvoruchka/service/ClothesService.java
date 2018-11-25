package com.kryvoruchka.service;

import com.kryvoruchka.Repository.ClothesRepository;
import com.kryvoruchka.Repository.CustomerRepository;
import com.kryvoruchka.Repository.MakerRepository;
import com.kryvoruchka.domain.Clothes;
import com.kryvoruchka.domain.Customer;
import com.kryvoruchka.domain.Maker;
import com.kryvoruchka.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ClothesService {
    @Autowired
    ClothesRepository clothesRepository;

    @Autowired
    MakerRepository makerRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Clothes> getClothesByMakerCompanyName(String company_name) throws NoSuchMakerException {
        Maker maker = makerRepository.findById(company_name).get();
        if (maker == null) throw new NoSuchMakerException();
        return maker.getClothes();
    }

    public Clothes getClothes(Long clothes_id) throws NoSuchClothesException {
        Clothes clothes = clothesRepository.findById(clothes_id).get();
        if (clothes == null) throw new NoSuchClothesException();
        return clothes;
    }

    public List<Clothes> getAllClothes(){
        return clothesRepository.findAll();
    }

    public Set<Clothes> getClothesByCustomerPassName(Long customer_pass_name) throws NoSuchCustomerException {
        Customer customer = customerRepository.findById(customer_pass_name).get();
        if (customer == null) throw new NoSuchCustomerException();
        return customer.getClothes();
    }

    @Transactional
    public void createClothes(Clothes clothes, String company_name) throws NoSuchMakerException {
        if (company_name != null){
            Maker maker = makerRepository.findById(company_name).get();
            if (maker == null) throw new NoSuchMakerException();
            clothes.setMaker(maker);
        }
        clothesRepository.save(clothes);
    }

    @Transactional
    public void updateClothes(Clothes uClothes, Long clothes_id, String company_name) throws NoSuchMakerException,
            NoSuchClothesException {
        Maker maker = makerRepository.findById(company_name).get();
        if (company_name != null) {
            if (maker == null) throw new NoSuchMakerException();
        }

        Clothes clothes = clothesRepository.findById(clothes_id).get();
        if (clothes == null) throw new NoSuchClothesException();

        clothes.setAmount(uClothes.getAmount());
        clothes.setColor(uClothes.getColor());
        clothes.setSize(uClothes.getSize());
        clothes.setType(uClothes.getType());
        if (company_name != null) clothes.setMaker(maker);
        else clothes.setMaker(null);
        clothesRepository.save(clothes);
    }

    @Transactional
    public void deleteClothes(Long clothes_id) throws NoSuchClothesException, ExistsCustomerForClothesException {
        Clothes clothes = clothesRepository.findById(clothes_id).get();
        if (clothes == null) throw new NoSuchClothesException();
        if (clothes.getCustomers().size() != 0) throw new ExistsCustomerForClothesException();
        clothesRepository.delete(clothes);
    }

    @Transactional
    public void addCustomerForClothes(Long clothes_id, Long customer_pass_name) throws NoSuchClothesException,
            NoSuchCustomerException, AlreadyExistsCustomerInClothesException, CustomerAbsentException {
        Clothes clothes = clothesRepository.findById(clothes_id).get();
        if (clothes == null) throw new NoSuchClothesException();
        Customer customer = customerRepository.findById(customer_pass_name).get();
        if (customer == null) throw new NoSuchCustomerException();
        if (clothes.getCustomers().contains(customer)) throw new AlreadyExistsCustomerInClothesException();
        clothes.getCustomers().add(customer);
        clothesRepository.save(clothes);
    }

    @Transactional
    public void removeCustomerForClothes(Long clothes_id, Long customer_pass_name) throws NoSuchClothesException,
            NoSuchCustomerException, ClothesHasNotCustomerException {
        Clothes clothes = clothesRepository.findById(clothes_id).get();
        if (clothes == null) throw new NoSuchClothesException();
        Customer customer = customerRepository.findById(customer_pass_name).get();
        if (customer == null) throw new NoSuchCustomerException();
        if (!clothes.getCustomers().contains(customer)) throw new ClothesHasNotCustomerException();
        clothes.getCustomers().remove(customer);
        clothesRepository.save(clothes);
    }
}
