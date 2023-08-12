package com.shoppingmart.shoppingverse.dto.response;

import com.shoppingmart.shoppingverse.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDto {
    String name;
    String email;
    String mobNo;
    Gender gender;
}
