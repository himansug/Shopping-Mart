package com.shoppingmart.shoppingverse.transformer;

import com.shoppingmart.shoppingverse.Enum.ProductStatus;
import com.shoppingmart.shoppingverse.dto.request.ProductRequestDto;
import com.shoppingmart.shoppingverse.dto.response.ProductResponseDto;
import com.shoppingmart.shoppingverse.model.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .productCategory(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .category(product.getProductCategory())
                .availableQuantity(product.getAvailableQuantity())
                .build();
    }
}
