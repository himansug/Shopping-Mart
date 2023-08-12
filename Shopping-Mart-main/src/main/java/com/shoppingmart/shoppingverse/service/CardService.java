package com.shoppingmart.shoppingverse.service;

import com.shoppingmart.shoppingverse.dto.request.CardRequestDto;
import com.shoppingmart.shoppingverse.dto.request.ItemRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CardResponseDto;
import com.shoppingmart.shoppingverse.dto.response.CartResponseDto;
import com.shoppingmart.shoppingverse.model.Item;


public interface CardService {
    CardResponseDto addCard(CardRequestDto cardRequestDto);

    String generateMaskedCard(String cardNo);

}
