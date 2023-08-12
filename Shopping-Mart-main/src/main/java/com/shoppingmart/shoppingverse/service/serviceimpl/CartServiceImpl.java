package com.shoppingmart.shoppingverse.service.serviceimpl;

import com.shoppingmart.shoppingverse.config.Mailing;
import com.shoppingmart.shoppingverse.dto.request.CheckoutCartRequestDto;
import com.shoppingmart.shoppingverse.dto.request.ItemRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CartResponseDto;
import com.shoppingmart.shoppingverse.dto.response.ItemResponseDto;
import com.shoppingmart.shoppingverse.dto.response.OrderResponseDto;
import com.shoppingmart.shoppingverse.exception.CustomerNotFoundException;
import com.shoppingmart.shoppingverse.exception.EmptyCartFoundException;
import com.shoppingmart.shoppingverse.exception.InvalidCardException;
import com.shoppingmart.shoppingverse.exception.ProductNotFoundException;
import com.shoppingmart.shoppingverse.model.*;
import com.shoppingmart.shoppingverse.repository.*;
import com.shoppingmart.shoppingverse.service.CartService;
import com.shoppingmart.shoppingverse.service.OrderService;
import com.shoppingmart.shoppingverse.transformer.CartTransformer;
import com.shoppingmart.shoppingverse.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Mailing mailing;
    @Override
    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobNo(itemRequestDto.getCustomerMobile());
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        Optional<Product> optionalProduct = productRepository.findById(itemRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }
        Customer customer = optionalCustomer.get();
        Product product = optionalProduct.get();
        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+ product.getPrice()* item.getRequiredQuantity());

        item.setCart(cart);
        item.setProduct(product);

        Item savedItem = itemRepository.save(item);

        cart.getItems().add(savedItem);
        product.getItems().add(savedItem);
        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    @Override
    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobNo(checkoutCartRequestDto.getCustomerMobile());
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        Customer customer = optionalCustomer.get();

        Optional<Card> optionalCard = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());

        Card card = optionalCard.get();

        Date currentDate = new Date();
        if(card==null || card.getCvv()!= checkoutCartRequestDto.getCvv() || currentDate.after(card.getValidTill())){
            throw new InvalidCardException("Card is not valid");
        }

        Cart cart = customer.getCart();
        if(cart.getItems().size()==0){
            throw new EmptyCartFoundException("Sorry! The cart is empty");
        }
        Ordered order = orderService.placeOrder(cart,card);
        resetCart(cart);

        Ordered savedOrder = orderedRepository.save(order);

        //send mail
        mailing.sendMail(savedOrder);

        // prepare response dto
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }
    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());

    }
}
