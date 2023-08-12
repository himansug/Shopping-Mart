package com.shoppingmart.shoppingverse.service;

import com.shoppingmart.shoppingverse.dto.request.SellerRequestDto;
import com.shoppingmart.shoppingverse.dto.response.SellerResponseDto;



public interface SellerService {
    SellerResponseDto addCustomer(SellerRequestDto sellerRequestDto);
}
