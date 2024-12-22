package org.example.accounts.mapper;

import org.example.accounts.dto.CustomerRequestDto;
import org.example.accounts.dto.CustomerDto;
import org.example.accounts.model.Customer;

public class CustomerMapper {
    public static CustomerRequestDto mapToCustomerDto(Customer customer, CustomerRequestDto customerRequestDto) {
        customerRequestDto.setEmail(customer.getEmail());
        customerRequestDto.setName(customer.getName());
        customerRequestDto.setMobileNumber(customer.getMobileNumber());
        return customerRequestDto;
    }

    public static Customer mapToCustomer(CustomerRequestDto customerRequestDto, Customer customer) {
        customer.setEmail(customerRequestDto.getEmail());
        customer.setName(customerRequestDto.getName());
        customer.setMobileNumber(customerRequestDto.getMobileNumber());
        return customer;
    }
    public static Customer mapToCustomers(CustomerDto customerDto, Customer customer) {
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
    public static CustomerDto mapToCustomerResponseDto(Customer customer, CustomerDto customerResponseDto) {
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setMobileNumber(customer.getMobileNumber());
        return customerResponseDto;
    }
}
