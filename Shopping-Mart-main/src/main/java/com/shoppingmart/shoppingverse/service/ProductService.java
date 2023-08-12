package com.shoppingmart.shoppingverse.service;

import com.shoppingmart.shoppingverse.Enum.ProductCategory;
import com.shoppingmart.shoppingverse.dto.request.ProductRequestDto;
import com.shoppingmart.shoppingverse.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory category);
}
