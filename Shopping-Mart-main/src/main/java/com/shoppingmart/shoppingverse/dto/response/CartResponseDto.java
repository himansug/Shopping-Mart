package com.shoppingmart.shoppingverse.dto.response;

import com.shoppingmart.shoppingverse.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {

    String customerName;
    int cartTotal;
    List<ItemResponseDto> items;
}