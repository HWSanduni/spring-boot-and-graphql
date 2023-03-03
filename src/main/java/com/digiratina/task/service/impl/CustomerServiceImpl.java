package com.digiratina.task.service.impl;

import com.digiratina.task.controller.CustomerController;
import com.digiratina.task.dto.CustomerDTO;
import com.digiratina.task.dto.ResponseDTO;
import com.digiratina.task.entity.CustomerEntity;
import com.digiratina.task.repository.CustomerRepository;
import com.digiratina.task.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.digiratina.task.service.constant.ServiceConstant.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

   @Override
   public ResponseDTO getAllCustomer() {

       List<CustomerEntity> customerEntityList = customerRepository.findAll();

       List<CustomerDTO> customerDTOList = new ArrayList<>();
       customerEntityList.forEach(data -> {
           customerDTOList.add(setCustomerEntityData(data));
       });

       if (Objects.nonNull(customerDTOList)){
           LOGGER.info("Get All Customer Details Request Successfully Invoked");
           return ResponseDTO.builder().message(LOAD).data(customerDTOList).build();
       }
       LOGGER.error("Get All Customer Details Request Failed");
       return ResponseDTO.builder().message(ERROR).data(null).build();
   }

    @Override
    public ResponseDTO getCustomer(String firstName) {
        CustomerEntity customer = customerRepository.findByFirstName(firstName);
        if (Objects.nonNull(customer)) {
            LOGGER.info("Get All Customer Details Request Successfully Invoked");
            return ResponseDTO.builder().message(LOAD).data(setCustomerEntityData(customer)).build();
        }
        LOGGER.error("Get Customer Details Request Failed");
        return ResponseDTO.builder().message(ERROR).data(setCustomerEntityData(null)).build();
    }

    @Override
    public String saveCustomer(CustomerEntity customer) {
        CustomerEntity result  = customerRepository.save(customer);
        if (Objects.nonNull(result)){
            LOGGER.info("Save Customer Details Request Successfully Invoked");
            return SAVE;
        }
        LOGGER.error("Save Customer Details Request Failed");
        return ERROR;

    }

    @Override
    public String updateCustomer(CustomerEntity customer) {
        CustomerEntity result  = customerRepository.save(customer);
        if (Objects.nonNull(result)){
            LOGGER.info("Get All Customer Details Request Successfully Invoked");
            return UPDATE;
        }
        LOGGER.error("Update Customer Details Request Failed");
        return ERROR;
    }

    @Override
    public String deleteCustomer(int id) {

        CustomerEntity customer=customerRepository.findById(id).get();
        if (Objects.isNull(customer)){
            LOGGER.info("Get All Customer Details Request Successfully Invoked");
            customerRepository.deleteById(id);
            return DELETE;
        }
        LOGGER.error("Delete Customer Details Request Failed");
        return ERROR;
    }


    private CustomerEntity setCustomerDtoData(CustomerDTO customerDto) {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setLastName(customerDto.getLastName());
        customerEntity.setAddress(customerDto.getAddress());
        customerEntity.setPhoneNumber(customerDto.getPhoneNumber());
        customerEntity.setEmail(customerDto.getEmail());

        return customerEntity;
    }

    private CustomerDTO setCustomerEntityData(CustomerEntity customerDTO) {

        return CustomerDTO.builder().id(customerDTO.getId())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .address(customerDTO.getAddress())
                .email(customerDTO.getEmail())
                .phoneNumber(customerDTO.getPhoneNumber()).build();
    }
}
