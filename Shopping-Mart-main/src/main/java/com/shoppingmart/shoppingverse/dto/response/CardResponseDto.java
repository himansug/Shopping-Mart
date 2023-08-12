package com.shoppingmart.shoppingverse.dto.response;

import com.shoppingmart.shoppingverse.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto {

    String customerName;

    String cardNo;  // masked

    Date validTill;

    CardType cardType;
}