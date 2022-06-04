package com.csi.controller;

import com.csi.exception.RecordNotFoundExpection;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @PostMapping("/saveData")
    public Customer saveData(@RequestBody Customer customer){
        return customerServiceImpl.saveData(customer);
    }

    @PutMapping("/updatedata/{custId}")
    public Customer updateData(@PathVariable int custId , @RequestBody Customer customer) throws RecordNotFoundExpection {

        Customer customer1=customerServiceImpl.getDataById(custId).orElseThrow(()-> new RecordNotFoundExpection("Customer Id Does't exists"));
        return customerServiceImpl.updateData(customer);
    }

    @GetMapping("/getdatabyid/{custId}")
    public Optional<Customer> getDataById(int custId){
        return customerServiceImpl.getDataById(custId);
    }

    @GetMapping("/getalldata")
    public List<Customer> getAllData(){
        return customerServiceImpl.getAllData();
    }

    @DeleteMapping("/deletedatabyid/{custId}")
    public String deleteDataById(int custId){
        customerServiceImpl.deleteDataById(custId);
        return "Data deleted Sucessfully";
    }

    @DeleteMapping("/deletealldata")

    public String deleteAllData(){
        customerServiceImpl.deleteAllData();
        return "Data Deleted Sucessfully";
    }

}
