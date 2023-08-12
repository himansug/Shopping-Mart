package com.shoppingmart.shoppingverse.service.serviceimpl;

import com.shoppingmart.shoppingverse.dto.request.SellerRequestDto;
import com.shoppingmart.shoppingverse.dto.response.SellerResponseDto;
import com.shoppingmart.shoppingverse.model.Seller;
import com.shoppingmart.shoppingverse.repository.SellerRepository;
import com.shoppingmart.shoppingverse.service.SellerService;
import com.shoppingmart.shoppingverse.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addCustomer(SellerRequestDto sellerRequestDto) {
        // dto -> entity
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);
        //entity -> dto
        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }
}
