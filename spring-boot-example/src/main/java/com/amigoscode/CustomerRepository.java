package com.amigoscode;

import org.springframework.data.jpa.repository.JpaRepository;

// This JpaRepository works with Generics, and must have two types in the angle brackets.;
// The first parameter we pass is the entitiy, in our case, Customer.
// The second parameter is the data type for the Customer id, which in our case is Integer
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
