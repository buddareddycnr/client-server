package com.openmedical.server.service.impl;

import com.openmedical.entity.Customer;
import com.openmedical.server.dao.UserDao;
import com.openmedical.server.exceptions.InvalidEmailIdPatternException;
import com.openmedical.server.exceptions.UserNotFoundException;
import com.openmedical.server.exceptions.UserSaveOrUpdateOperationFailedException;
import com.openmedical.server.service.UserService;
import com.openmedical.server.util.MandatoryFieldsLengthValidator;
import com.openmedical.server.validation.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("${user.mobileNumber.pattern}")
    private String mobileNumberFormat;
    @Value(("${user.emailId.pattern}"))
    private String emailPattern;
   private MandatoryFieldsLengthValidator<Customer> mandatoryFieldsLengthValidator;
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(MandatoryFieldsLengthValidator<Customer> mandatoryFieldsLengthValidator, UserDao userDao) {
        this.mandatoryFieldsLengthValidator = mandatoryFieldsLengthValidator;
        this.userDao = userDao;
    }

    /**
     * @param customer
     * @return
     */
    @Override
    public Customer saveUser(Customer customer) {
        //if (mandatoryFieldsLengthValidator.isValidInput(customer)) {
            if (!EmailValidator.isEmailIdValid(customer.getEmailId(),emailPattern)) {
                throw new InvalidEmailIdPatternException("Email id should in valid format");
            }
            try {
                return userDao.save(customer);
            } catch (Exception exception) {
                throw new UserSaveOrUpdateOperationFailedException("Unable to save customer details", exception);
            }
       /* } else {
            throw new MandatoryFieldMissingException("Mandatory Fields are missing, Please cross verify input fields");
        }*/
    }

    /**
     * @param emailId
     * @return
     */
    @Override
    public void deleteUser(String emailId) {
            if (!EmailValidator.isEmailIdValid(emailId,emailPattern)) {
                throw new InvalidEmailIdPatternException("Email id should in valid format");
            }
            try {
                userDao.deleteUser(emailId);
            } catch (Exception exception) {
                throw new UserSaveOrUpdateOperationFailedException("Unable to delete customer details", exception);
            }
        }
    public List<Customer> getUsers(){
        List<Customer> customers = userDao.fetchAll();
        if(customers.isEmpty())
            throw new UserNotFoundException("No user records available in the table");
        return customers;
    }
}
