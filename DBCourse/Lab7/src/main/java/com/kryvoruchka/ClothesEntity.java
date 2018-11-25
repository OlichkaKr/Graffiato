package com.kryvoruchka;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clothes", schema = "onlineshop")
public class ClothesEntity {
    private int id;
    private String type;
    private int amount;
    private String color;
    private String size;
    private MakerEntity fkClothesMaker;
    private List<CustomerEntity> customers;

    public ClothesEntity() {
    }

    public ClothesEntity(String type, int amount, String color, String size, MakerEntity fkClothesMaker) {
        this.type = type;
        this.amount = amount;
        this.color = color;
        this.size = size;
        this.fkClothesMaker = fkClothesMaker;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClothesEntity that = (ClothesEntity) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "FK_clothes_maker", referencedColumnName = "company_name", nullable = false)
    public MakerEntity getFkClothesMaker() {
        return fkClothesMaker;
    }

    public void setFkClothesMaker(MakerEntity fkClothesMaker) {
        this.fkClothesMaker = fkClothesMaker;
    }

    @ManyToMany
    @JoinTable(name = "clothes_has_customer", schema = "onlineshop", joinColumns = @JoinColumn(name = "clothes_id",
            referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name =
            "customer_pass_name", referencedColumnName = "pass_name", nullable = false))
    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }
}
