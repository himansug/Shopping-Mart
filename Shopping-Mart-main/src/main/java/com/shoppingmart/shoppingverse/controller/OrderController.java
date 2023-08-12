package com.shoppingmart.shoppingverse.controller;

import com.shoppingmart.shoppingverse.dto.request.ItemRequestDto;
import com.shoppingmart.shoppingverse.dto.request.OrderRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CartResponseDto;
import com.shoppingmart.shoppingverse.dto.response.OrderResponseDto;
import com.shoppingmart.shoppingverse.model.Item;
import com.shoppingmart.shoppingverse.service.CartService;
import com.shoppingmart.shoppingverse.service.ItemService;
import com.shoppingmart.shoppingverse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        try {
            OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
