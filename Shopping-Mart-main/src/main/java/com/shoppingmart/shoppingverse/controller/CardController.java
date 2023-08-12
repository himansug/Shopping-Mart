package com.shoppingmart.shoppingverse.controller;

import com.shoppingmart.shoppingverse.dto.request.CardRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CardResponseDto;
import com.shoppingmart.shoppingverse.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
    CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
        return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
    }

}
