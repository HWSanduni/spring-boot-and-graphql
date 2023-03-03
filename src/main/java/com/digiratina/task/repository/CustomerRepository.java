package com.digiratina.task.repository;

import com.digiratina.task.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {

    CustomerEntity findByFirstName(String firstName);
}
