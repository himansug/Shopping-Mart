package com.shoppingmart.shoppingverse.transformer;

import com.shoppingmart.shoppingverse.dto.request.CardRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CardResponseDto;
import com.shoppingmart.shoppingverse.dto.response.CartResponseDto;
import com.shoppingmart.shoppingverse.dto.response.ItemResponseDto;
import com.shoppingmart.shoppingverse.model.Card;
import com.shoppingmart.shoppingverse.model.Cart;
import com.shoppingmart.shoppingverse.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {


    public static CartResponseDto CartToCartResponseDto(Cart cart){

        List<ItemResponseDto> items = new ArrayList<>();
        for(Item item : cart.getItems()){
            items.add(ItemTransformer.ItemToItemResponseDto(item));
        }
        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(items)
                .build();
    }
}
