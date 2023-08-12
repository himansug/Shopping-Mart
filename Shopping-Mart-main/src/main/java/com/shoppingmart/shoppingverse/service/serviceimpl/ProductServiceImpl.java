package com.shoppingmart.shoppingverse.service.serviceimpl;

import com.shoppingmart.shoppingverse.Enum.ProductCategory;
import com.shoppingmart.shoppingverse.dto.request.ProductRequestDto;
import com.shoppingmart.shoppingverse.dto.response.ProductResponseDto;
import com.shoppingmart.shoppingverse.exception.SellerNotFoundException;
import com.shoppingmart.shoppingverse.model.Product;
import com.shoppingmart.shoppingverse.model.Seller;
import com.shoppingmart.shoppingverse.repository.ProductRepository;
import com.shoppingmart.shoppingverse.repository.SellerRepository;
import com.shoppingmart.shoppingverse.service.ProductService;
import com.shoppingmart.shoppingverse.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        Optional<Seller> optionalSeller = sellerRepository.findByEmailId(productRequestDto.getSellerEmail());
        if(optionalSeller.isEmpty()){
            throw new SellerNotFoundException("Seller doesn't Exist");
        }
        Seller seller = optionalSeller.get();
        // dto -> entity
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller); // save both product and seller
        List<Product> productList = savedSeller.getProducts();
        Product latestProduct = productList.get(productList.size() - 1);

        // prepare response dto
        return ProductTransformer.ProductToProductResponseDto(latestProduct);
    }

    @Override
    public List<ProductResponseDto> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory category) {
        List<Product> products = productRepository.getProdByCategoryAndPriceGreaterThan(price,category);

        // prepare list of response dtos
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }
}
