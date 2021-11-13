package com.example.controller;

import com.example.exception.CustomerNotFoundException;
import com.example.model.Customer;
import com.example.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import jakarta.inject.Inject;

import java.util.Collection;

@Controller("/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @Get
    public Collection<Customer> customers() {
        return customerService.getCustomers();
    }

    @Get("{id}")
    public Customer customer(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

    @Delete("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delCustomer(@PathVariable int id) {
        customerService.removeCustomer(id);
        return String.format("Customer %d removed successfully", id);
    }

    @Post
    @Produces(MediaType.TEXT_PLAIN)
    public String addCustomer(@Body Customer customer) {
        customerService.addCustomer(customer);
        return String.format("Customer %d added successfully", customer.getId());
    }

    @Put
    @Produces(MediaType.TEXT_PLAIN)
    public String updateCustomer(@Body Customer customer) {
        customerService.updateCustomer(customer);
        return String.format("Customer %d updated successfully", customer.getId());
    }

    @Error
    public HttpResponse<String> customerNotFound(CustomerNotFoundException exception) {
        return HttpResponse.<String>status(HttpStatus.OK)
                .body(exception.getMessage());
    }
}
