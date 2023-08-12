package com.shoppingmart.shoppingverse.transformer;

import com.shoppingmart.shoppingverse.dto.request.CustomerRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CustomerResponseDto;
import com.shoppingmart.shoppingverse.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder().name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .gender(customerRequestDto.getGender())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .email(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .gender(customer.getGender())
                .build();
    }
}
