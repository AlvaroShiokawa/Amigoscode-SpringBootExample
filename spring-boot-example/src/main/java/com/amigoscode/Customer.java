package com.amigoscode;

import jakarta.persistence.*;

import java.util.Objects;

// This class represents our Model

@Entity // This will allow us to map our class into a table of the DB
public class Customer {
    @Id // We have to select the primary key
    @SequenceGenerator( // Because we are using PostgreSQL we have to put a sequence here
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1 // we can change the increment of the sequence by changint this property. Now it increments 1 by 1
    )
    @GeneratedValue( // We want to tell how our numbers will be generated
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence" // we will use our customer_id_sequence as a generator
    )
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    // Canonical constructor
    public Customer(Integer id,
                    String name,
                    String email,
                    Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Customer() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}