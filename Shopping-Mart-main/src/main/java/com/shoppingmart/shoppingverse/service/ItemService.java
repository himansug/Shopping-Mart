package com.shoppingmart.shoppingverse.service;

import com.shoppingmart.shoppingverse.dto.request.ItemRequestDto;
import com.shoppingmart.shoppingverse.model.Item;

public interface ItemService {
    Item createItem(ItemRequestDto itemRequestDto);
}
