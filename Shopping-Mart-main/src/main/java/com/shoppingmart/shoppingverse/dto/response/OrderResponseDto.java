package com.shoppingmart.shoppingverse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {

    String customerName;
    String orderId;
    String cardUsed;
    Date orderDate;
    int orderTotal;
    List<ItemResponseDto> items;
}