package com.digiratina.task.controller;

import com.digiratina.task.dto.CustomerDTO;
import com.digiratina.task.dto.ResponseDTO;
import com.digiratina.task.entity.CustomerEntity;
import com.digiratina.task.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class CustomerController {
    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @SchemaMapping(typeName = "Query" ,value = "findAll")
    public ResponseDTO getAllCustomer(){
        LOGGER.info("Get All Customer Details - Started");
        return customerService.getAllCustomer();
    }


    @SchemaMapping(typeName = "Query" ,value = "findCustomer")
    public ResponseDTO getCustomer(@Argument String firstName){
        LOGGER.info("Get Customer Details - Started | firstName={}",firstName);
        return customerService.getCustomer(firstName);
    }

    @SchemaMapping(typeName = "Mutation" ,value = "addNewCustomer")
    public String saveCustomer(@Argument CustomerEntity customer){
        LOGGER.info("Save Customer Details - Started | customer={}",customer);
        return customerService.saveCustomer(customer);
    }

    @SchemaMapping(typeName = "Mutation" ,value = "updateCustomer")
    public String updateCustomer(@Argument CustomerEntity customer){
        LOGGER.info("Update Customer Details - Started | customer={}",customer);
        return customerService.updateCustomer(customer);
    }

    @SchemaMapping(typeName = "Mutation" ,value = "deleteCustomer")
    public String deleteCustomer(@Argument int id){
        LOGGER.info("Update Customer Details - Started | customerId={}",id);
        return customerService.deleteCustomer(id);
    }
}
