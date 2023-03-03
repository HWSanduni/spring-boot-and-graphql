package com.digiratina.task.service;

import com.digiratina.task.dto.CustomerDTO;
import com.digiratina.task.dto.ResponseDTO;
import com.digiratina.task.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {

    ResponseDTO getAllCustomer();

    ResponseDTO getCustomer(String firstName);

    String saveCustomer(CustomerEntity customer);

    String updateCustomer(CustomerEntity customer);

    String deleteCustomer(int id);
}
