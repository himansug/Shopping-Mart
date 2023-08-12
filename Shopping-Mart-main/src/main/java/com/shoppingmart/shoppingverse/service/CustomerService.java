package com.shoppingmart.shoppingverse.service;

import com.shoppingmart.shoppingverse.dto.request.CustomerRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CustomerResponseDto;



public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
