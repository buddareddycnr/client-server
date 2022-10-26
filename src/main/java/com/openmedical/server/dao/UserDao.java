package com.openmedical.server.dao;



import com.openmedical.entity.Customer;

import java.util.List;

public interface UserDao {
    Customer save(final Customer customer);

    void deleteUser(final String emailId);

    List<Customer> fetchAll();
}
