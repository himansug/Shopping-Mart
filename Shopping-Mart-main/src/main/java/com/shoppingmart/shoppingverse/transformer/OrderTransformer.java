package com.shoppingmart.shoppingverse.transformer;

import com.shoppingmart.shoppingverse.dto.response.ItemResponseDto;
import com.shoppingmart.shoppingverse.dto.response.OrderResponseDto;
import com.shoppingmart.shoppingverse.model.Item;
import com.shoppingmart.shoppingverse.model.Ordered;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {
    public static OrderResponseDto OrderToOrderResponseDto(Ordered ordered){

        List<ItemResponseDto> items = new ArrayList<>();
        for(Item item : ordered.getItems()){
            items.add(ItemTransformer.ItemToItemResponseDto(item));
        }
        return OrderResponseDto.builder()
                .orderId(ordered.getOrderId())
                .orderDate(ordered.getOrderDate())
                .customerName(ordered.getCustomer().getName())
                .orderTotal(ordered.getOrderTotal())
                .cardUsed(ordered.getCardUsed())
                .items(items)
                .build();
    }
}
