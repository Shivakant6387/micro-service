package org.example.accounts.service.impl;

import org.example.accounts.constants.AccountConstants;
import org.example.accounts.dto.AccountsRequestDto;
import org.example.accounts.dto.CustomerRequestDto;
import org.example.accounts.dto.CustomerDto;
import org.example.accounts.exception.CustomerAlreadyExistException;
import org.example.accounts.exception.ResourceNotFoundException;
import org.example.accounts.mapper.AccountMapper;
import org.example.accounts.mapper.CustomerMapper;
import org.example.accounts.model.Accounts;
import org.example.accounts.model.Customer;
import org.example.accounts.repository.AccountsRepository;
import org.example.accounts.repository.CustomerRepository;
import org.example.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public void createAccount(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerRequestDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerRequestDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already registered with given mobileNumber : " + customerRequestDto.getMobileNumber());
        }
        Optional<Customer> customerOptional = customerRepository.findByEmail(customerRequestDto.getEmail());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already registered with email : " + customerRequestDto.getEmail());
        }
//        customer.setCreatedAt(LocalDate.now());
//        customer.setCreatedBy("Anonymous");
        Customer saveCustomer = customerRepository.save(customer);
        this.accountsRepository.save(createNewAccounts(saveCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "CustomerId", String.valueOf(customer.getCustomerId())));
        CustomerDto customerResponseDto = CustomerMapper.mapToCustomerResponseDto(customer, new CustomerDto());
        customerResponseDto.setAccountsRequestDto(AccountMapper.mapToAccountsDto(accounts, new AccountsRequestDto()));
        return customerResponseDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerRequestDto) {
        boolean isUpdated = false;
        AccountsRequestDto accountsRequestDto = customerRequestDto.getAccountsRequestDto();
        if (accountsRequestDto != null) {
            Accounts accounts = this.accountsRepository.findById(accountsRequestDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", String.valueOf(accountsRequestDto.getAccountNumber())));
            AccountMapper.mapToAccounts(accountsRequestDto, accounts);
            accounts = this.accountsRepository.save(accounts);
            long customerId = accounts.getCustomerId();
            Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", String.valueOf(customerId)));
            CustomerMapper.mapToCustomers(customerRequestDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = this.customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAccounts(Customer customer) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(AccountConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountConstants.ADDRESS);
//        newAccounts.setCreatedAt(LocalDate.now());
//        newAccounts.setCreatedBy("Anonymous");
        return newAccounts;
    }
}
