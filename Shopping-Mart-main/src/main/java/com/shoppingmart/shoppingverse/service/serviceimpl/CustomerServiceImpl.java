package com.shoppingmart.shoppingverse.service.serviceimpl;

import com.shoppingmart.shoppingverse.dto.request.CustomerRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CustomerResponseDto;
import com.shoppingmart.shoppingverse.model.Cart;
import com.shoppingmart.shoppingverse.model.Customer;
import com.shoppingmart.shoppingverse.repository.CustomerRepository;
import com.shoppingmart.shoppingverse.service.CustomerService;
import com.shoppingmart.shoppingverse.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        //dto -> entity
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);

        //initialize the cart for customer when customer register
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);// save both customer and cart
        // entity -> dto then return
        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }
}
