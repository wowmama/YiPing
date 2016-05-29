package com.yiping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiping.customer.model.Customer100;

public interface CustomerRepository extends JpaRepository<Customer100, Integer>{

}
