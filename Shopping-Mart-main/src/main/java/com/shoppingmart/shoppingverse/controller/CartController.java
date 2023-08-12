package com.shoppingmart.shoppingverse.controller;

import com.shoppingmart.shoppingverse.dto.request.CheckoutCartRequestDto;
import com.shoppingmart.shoppingverse.dto.request.ItemRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CartResponseDto;
import com.shoppingmart.shoppingverse.dto.response.OrderResponseDto;
import com.shoppingmart.shoppingverse.model.Item;
import com.shoppingmart.shoppingverse.service.CardService;
import com.shoppingmart.shoppingverse.service.CartService;
import com.shoppingmart.shoppingverse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;
    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto  itemRequestDto){
        try {
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addItemToCart(itemRequestDto, item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto){
        try {
            OrderResponseDto orderResponseDto = cartService.checkoutCart(checkoutCartRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
