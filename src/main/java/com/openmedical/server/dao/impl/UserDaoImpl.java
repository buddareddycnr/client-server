package com.openmedical.server.dao.impl;

import com.openmedical.entity.Customer;
import com.openmedical.server.dao.UserDao;
import com.openmedical.server.exceptions.UserDeleteOperationFailedException;
import com.openmedical.server.exceptions.UserNotFoundException;
import com.openmedical.server.exceptions.UserSaveOrUpdateOperationFailedException;
import com.openmedical.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserRepository userRepository) {
     this.userRepository = userRepository;
    }

    @Override
    public Customer save(Customer customer) {
        try{
            return userRepository.saveAndFlush(customer);
        }catch (Exception exception){
            LOGGER.error("Exception occurred while persisting customer data {}",exception);
            throw new UserSaveOrUpdateOperationFailedException("Unable to Save or update operation failed for the customer",exception);
        }
    }

    @Override
    public void deleteUser(String emailId) {
        try{
            if(userRepository.findByEmailId(emailId).isPresent())
            userRepository.delete(userRepository.findByEmailId(emailId).get());
            else
                throw new UserNotFoundException("User details not found in the database table");
        }catch (Exception exception){
            LOGGER.error("Exception occurred while deleting customer data {}",exception);
            throw new UserDeleteOperationFailedException("Unable to delete operation failed for the customer",exception);
        }
    }

    @Override
    public List<Customer> fetchAll() {
        return userRepository.findAll();
    }
}
