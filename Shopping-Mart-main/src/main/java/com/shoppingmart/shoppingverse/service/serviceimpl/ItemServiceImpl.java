package com.shoppingmart.shoppingverse.service.serviceimpl;

import com.shoppingmart.shoppingverse.dto.request.ItemRequestDto;
import com.shoppingmart.shoppingverse.exception.CustomerNotFoundException;
import com.shoppingmart.shoppingverse.exception.InSufficientQuantityException;
import com.shoppingmart.shoppingverse.exception.ProductNotFoundException;
import com.shoppingmart.shoppingverse.model.Customer;
import com.shoppingmart.shoppingverse.model.Item;
import com.shoppingmart.shoppingverse.model.Product;
import com.shoppingmart.shoppingverse.repository.CustomerRepository;
import com.shoppingmart.shoppingverse.repository.ProductRepository;
import com.shoppingmart.shoppingverse.service.ItemService;
import com.shoppingmart.shoppingverse.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Item createItem(ItemRequestDto itemRequestDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobNo(itemRequestDto.getCustomerMobile());
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        Optional<Product> optionalProduct = productRepository.findById(itemRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }
        Product product = optionalProduct.get();
        if(product.getAvailableQuantity()<itemRequestDto.getRequiredQuantity()){
            throw new InSufficientQuantityException("Sorry! Insufficient quantity of product");
        }
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
