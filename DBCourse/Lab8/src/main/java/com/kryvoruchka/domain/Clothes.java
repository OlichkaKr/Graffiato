package com.kryvoruchka.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "type", length = 45)
    private String type;
    @Column(name = "amount", length = 11)
    private Long amount;
    @Column(name = "color", length = 45)
    private String color;
    @Column(name = "size", length = 45)
    private String size;

    @ManyToOne
    @JoinColumn(name = "FK_clothes_maker", referencedColumnName = "company_name")
    private Maker maker;
    @ManyToMany
    @JoinTable(name = "clothes_has_customer", joinColumns = @JoinColumn(name = "clothes_id", referencedColumnName =
            "id"),
        inverseJoinColumns = @JoinColumn(name = "customer_pass_name", referencedColumnName = "pass_name"))
    private Set<Customer> customers;

    public Clothes() {
    }

    public Clothes(String type, Long amount, String color, String size, Maker maker) {
        this.type = type;
        this.amount = amount;
        this.color = color;
        this.size = size;
        this.maker = maker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Objects.equals(id, clothes.id) &&
                Objects.equals(type, clothes.type) &&
                Objects.equals(amount, clothes.amount) &&
                Objects.equals(color, clothes.color) &&
                Objects.equals(size, clothes.size) &&
                Objects.equals(maker, clothes.maker);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, amount, color, size, maker);
    }
}
