package com.shoppingmart.shoppingverse.service;

import com.shoppingmart.shoppingverse.dto.request.OrderRequestDto;
import com.shoppingmart.shoppingverse.dto.response.OrderResponseDto;
import com.shoppingmart.shoppingverse.model.Card;
import com.shoppingmart.shoppingverse.model.Cart;
import com.shoppingmart.shoppingverse.model.Ordered;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);

    Ordered placeOrder(Cart cart, Card card);
}
