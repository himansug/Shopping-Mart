package com.shoppingmart.shoppingverse.service;

import com.shoppingmart.shoppingverse.dto.request.CheckoutCartRequestDto;
import com.shoppingmart.shoppingverse.dto.request.ItemRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CartResponseDto;
import com.shoppingmart.shoppingverse.dto.response.OrderResponseDto;
import com.shoppingmart.shoppingverse.model.Item;

public interface CartService {
    CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item);

    OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto);
}
