package com.shoppingmart.shoppingverse.controller;

import com.shoppingmart.shoppingverse.Enum.ProductCategory;
import com.shoppingmart.shoppingverse.dto.request.ProductRequestDto;
import com.shoppingmart.shoppingverse.dto.response.ProductResponseDto;
import com.shoppingmart.shoppingverse.exception.SellerNotFoundException;
import com.shoppingmart.shoppingverse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        try{
            ProductResponseDto response = productService.addProduct(productRequestDto);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_by_category_and_price_greater_than")
    public ResponseEntity getProdByCategoryAndPriceGreaterThan(@RequestParam("price") int price,
                                                               @RequestParam("category") ProductCategory category){

        List<ProductResponseDto> productResponseDtoList =
                productService.getProdByCategoryAndPriceGreaterThan(price,category);
        return new ResponseEntity(productResponseDtoList,HttpStatus.FOUND);
    }
}
