package com.kryvoruchka;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer", schema = "onlineshop")
public class CustomerEntity {
    private String firstName;
    private String lastName;
    private String adress;
    private int passName;
    private String payment;
    private List<ClothesEntity> clothes;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, String adress, int passName, String payment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.passName = passName;
        this.payment = payment;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Id
    @Column(name = "pass_name")
    public int getPassName() {
        return passName;
    }

    public void setPassName(int passName) {
        this.passName = passName;
    }

    @Basic
    @Column(name = "payment")
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (passName != that.passName) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (payment != null ? !payment.equals(that.payment) : that.payment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + passName;
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "customers")
    public List<ClothesEntity> getClothes() {
        return clothes;
    }

    public void addClothesEntity(ClothesEntity clothesEntity) {
        if (!getClothes().contains(clothesEntity)) {
            getClothes().add(clothesEntity);
        }
        if (!clothesEntity.getCustomers().contains(this)) {
            clothesEntity.getCustomers().add(this);
        }
    }

    public void setClothes(List<ClothesEntity> clothes) {
        this.clothes = clothes;
    }
}
