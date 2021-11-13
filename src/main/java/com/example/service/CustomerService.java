package com.example.service;

import com.example.exception.CustomerNotFoundException;
import com.example.model.Customer;
import jakarta.inject.Singleton;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class CustomerService {

    private final Map<Integer, Customer> customers = new ConcurrentHashMap<>(
            Map.of(1, new Customer(1,"Sam Roger", 18),
            2, new Customer(2,"Barbara Michael", 35),
            3, new Customer(3,"Thomas Smith", 29)));

    public Collection<Customer> getCustomers() {
        return customers.values();
    }
    public Customer getCustomer(int id) {
        Customer customer = customers.get(id);
        if (customer == null) throw new CustomerNotFoundException("No customer found with id: "+id);
        return customer;
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public void removeCustomer(int id) {
        Customer customer = customers.remove(id);
        if (customer == null) throw new CustomerNotFoundException("No customer found with id: "+id);
    }

    public void updateCustomer(Customer customer) {
        Customer currentData = customers.get(customer.getId());
        if (currentData == null)
            throw new CustomerNotFoundException("No customer found with id: "+customer.getId());
        if (customer.getAge()!= 0) currentData.setAge(customer.getAge());
        if (customer.getName()!= null) currentData.setName(customer.getName());
    }
}
