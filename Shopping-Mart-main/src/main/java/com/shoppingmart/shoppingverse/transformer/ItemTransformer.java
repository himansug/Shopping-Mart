package com.shoppingmart.shoppingverse.transformer;

import com.shoppingmart.shoppingverse.dto.response.ItemResponseDto;
import com.shoppingmart.shoppingverse.model.Item;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem(int requiredQuantity){
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }
    public static ItemResponseDto ItemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .itemPrice(item.getProduct().getPrice())
                .itemName(item.getProduct().getProductName())
                .quantityAdded(item.getRequiredQuantity())
                .productCategory(item.getProduct().getProductCategory())
                .build();
    }
}
