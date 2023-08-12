package com.shoppingmart.shoppingverse.transformer;

import com.shoppingmart.shoppingverse.dto.request.SellerRequestDto;
import com.shoppingmart.shoppingverse.dto.response.SellerResponseDto;
import com.shoppingmart.shoppingverse.model.Seller;

public class SellerTransformer {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmail())
                .pan(sellerRequestDto.getPan())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmailId())
                .build();
    }
}
