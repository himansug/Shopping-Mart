package com.shoppingmart.shoppingverse.model;


import com.shoppingmart.shoppingverse.Enum.ProductCategory;
import com.shoppingmart.shoppingverse.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productName;
    int price;
    int availableQuantity;
    @Enumerated(value = EnumType.STRING)
    ProductCategory productCategory;
    @Enumerated(value = EnumType.STRING)
    ProductStatus productStatus;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Seller seller;


}
