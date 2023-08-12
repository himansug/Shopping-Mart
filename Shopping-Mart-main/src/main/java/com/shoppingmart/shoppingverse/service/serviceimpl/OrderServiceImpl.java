package com.shoppingmart.shoppingverse.service.serviceimpl;

import com.shoppingmart.shoppingverse.Enum.ProductStatus;
import com.shoppingmart.shoppingverse.config.Mailing;
import com.shoppingmart.shoppingverse.dto.request.OrderRequestDto;
import com.shoppingmart.shoppingverse.dto.response.OrderResponseDto;
import com.shoppingmart.shoppingverse.exception.CustomerNotFoundException;
import com.shoppingmart.shoppingverse.exception.InSufficientQuantityException;
import com.shoppingmart.shoppingverse.exception.InvalidCardException;
import com.shoppingmart.shoppingverse.exception.ProductNotFoundException;
import com.shoppingmart.shoppingverse.model.*;
import com.shoppingmart.shoppingverse.repository.CardRepository;
import com.shoppingmart.shoppingverse.repository.CustomerRepository;
import com.shoppingmart.shoppingverse.repository.OrderedRepository;
import com.shoppingmart.shoppingverse.repository.ProductRepository;
import com.shoppingmart.shoppingverse.service.CardService;
import com.shoppingmart.shoppingverse.service.OrderService;
import com.shoppingmart.shoppingverse.transformer.ItemTransformer;
import com.shoppingmart.shoppingverse.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private Mailing mailing;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobNo(orderRequestDto.getCustomerMobile());
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }
        Optional<Card> optionalCard = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        Date todayDate = new Date();
        if(optionalCard.isEmpty() || optionalCard.get().getCvv()!=orderRequestDto.getCvv()
           || todayDate.after(optionalCard.get().getValidTill())){
            throw new InvalidCardException("Invalid card");
        }
        Customer customer = optionalCustomer.get();
        Product product = optionalProduct.get();
        if(product.getAvailableQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InSufficientQuantityException("Insufficient Quantity available");
        }
        int newQuantity = product.getAvailableQuantity()- orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        //prepare ordered
        Ordered ordered = new Ordered();
        ordered.setOrderId(String.valueOf(UUID.randomUUID()));
        ordered.setCardUsed(cardService.generateMaskedCard(orderRequestDto.getCardUsed()));
        ordered.setOrderTotal(orderRequestDto.getRequiredQuantity()*product.getPrice());

        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setOrdered(ordered);
        item.setProduct(product);
        ordered.setCustomer(customer);
        ordered.getItems().add(item);

        Ordered savedOrder = orderedRepository.save(ordered);  // save order and item


        product.getItems().add(savedOrder.getItems().get(0));
        customer.getOrders().add(savedOrder);

        //send mail
        mailing.sendMail(savedOrder);

        // prepare response Dto
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }
    public Ordered placeOrder(Cart cart, Card card) {

        Ordered order = new Ordered();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(cardService.generateMaskedCard(card.getCardNo()));

        int orderTotal = 0;
        for(Item item: cart.getItems()){

            Product product = item.getProduct();
            if(product.getAvailableQuantity() < item.getRequiredQuantity()){
                throw new InSufficientQuantityException("Sorry! Insufficient quantity available for: "+product.getProductName());
            }

            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            orderTotal += product.getPrice()*item.getRequiredQuantity();
            item.setOrdered(order);
        }

        order.setOrderTotal(orderTotal);
        order.setItems(cart.getItems());
        order.setCustomer(card.getCustomer());

        return order;
    }
}
