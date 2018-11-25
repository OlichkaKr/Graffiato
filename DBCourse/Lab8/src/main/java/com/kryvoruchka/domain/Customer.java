package com.kryvoruchka.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_name", nullable = false)
    private Long passName;
    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "adress", length = 45)
    private String adress;
    @Column(name = "payment", length = 45)
    private String payment;

    @ManyToMany(mappedBy = "customers")
    private Set<Clothes> clothes;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String adress, String payment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.payment = payment;
    }

    public Long getPassName() {
        return passName;
    }

    public void setPassName(Long passName) {
        this.passName = passName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Set<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(Set<Clothes> clothes) {
        this.clothes = clothes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(passName, customer.passName) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(adress, customer.adress) &&
                Objects.equals(payment, customer.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passName, firstName, lastName, adress, payment);
    }
}
