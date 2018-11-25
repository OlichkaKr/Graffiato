package com.kryvoruchka.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "maker")
public class Maker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "price", length = 11)
    private Long price;
    @OneToMany(mappedBy = "maker")
    private List<Clothes> clothes;

    public Maker() {
    }

    public Maker(String firstName, String lastName, Long price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(List<Clothes> clothes) {
        this.clothes = clothes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maker maker = (Maker) o;
        return Objects.equals(companyName, maker.companyName) &&
                Objects.equals(firstName, maker.firstName) &&
                Objects.equals(lastName, maker.lastName) &&
                Objects.equals(price, maker.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, firstName, lastName, price);
    }
}
