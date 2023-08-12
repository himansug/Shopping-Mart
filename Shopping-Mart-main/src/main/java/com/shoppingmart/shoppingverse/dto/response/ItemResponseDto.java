package com.shoppingmart.shoppingverse.dto.response;

import com.shoppingmart.shoppingverse.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String itemName;
    int itemPrice;
    int quantityAdded;
    ProductCategory productCategory;
}
