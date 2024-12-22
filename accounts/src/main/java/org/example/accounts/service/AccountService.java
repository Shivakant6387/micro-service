package org.example.accounts.service;

import org.example.accounts.dto.CustomerRequestDto;
import org.example.accounts.dto.CustomerDto;

public interface AccountService {
    public void  createAccount(CustomerRequestDto customerRequestDto);
    public CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerRequestDto);
    boolean deleteAccount(String mobileNumber);
}
