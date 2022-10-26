package com.openmedical.server.service;

import com.openmedical.entity.Customer;

import java.util.List;

public interface UserService {

    public Customer saveUser(Customer customer);

    public void deleteUser(String emailId);

    public List<Customer> getUsers();
}
