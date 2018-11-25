package com.kryvoruchka;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "maker", schema = "onlineshop", catalog = "")
public class MakerEntity {
    private String companyName;
    private String firstName;
    private String lastName;
    private int price;
    private List<ClothesEntity> clothesByMaker;

    public MakerEntity() {
    }

    public MakerEntity(String companyName, String firstName, String lastName, int price) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
    }

    public MakerEntity(String companyName) {
        this.companyName = companyName;
    }

    @Id
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MakerEntity that = (MakerEntity) o;

        if (price != that.price) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyName != null ? companyName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @OneToMany(mappedBy = "fkClothesMaker")
    public List<ClothesEntity> getClothesByMaker() {
        return clothesByMaker;
    }

    public void setClothesByMaker(List<ClothesEntity> clothesByMaker) {
        this.clothesByMaker = clothesByMaker;
    }
}
