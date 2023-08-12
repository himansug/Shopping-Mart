package com.shoppingmart.shoppingverse.service.serviceimpl;

import com.shoppingmart.shoppingverse.dto.request.CardRequestDto;
import com.shoppingmart.shoppingverse.dto.response.CardResponseDto;
import com.shoppingmart.shoppingverse.exception.CustomerNotFoundException;
import com.shoppingmart.shoppingverse.model.Card;
import com.shoppingmart.shoppingverse.model.Customer;
import com.shoppingmart.shoppingverse.repository.CustomerRepository;
import com.shoppingmart.shoppingverse.service.CardService;
import com.shoppingmart.shoppingverse.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

        Optional<Customer> optionalCustomer = customerRepository.findByMobNo(cardRequestDto.getCustomerMobile());

        if(optionalCustomer.isEmpty()){
            throw  new CustomerNotFoundException("Customer doesn't Exist");
        }
        Customer customer = optionalCustomer.get();

        // create card entity
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);
        List<Card> cards = savedCustomer.getCards();
        Card latestCard = cards.get(cards.size()-1);

        // prepare card response dto
        CardResponseDto cardResponseDto = CardTransformer.CardToCardResponseDto(latestCard);
        cardResponseDto.setCardNo(generateMaskedCard(latestCard.getCardNo()));

        return cardResponseDto;
    }
    public String generateMaskedCard(String cardNo){
        int cardLength = cardNo.length();
        String maskedCard = "";
        for(int i = 0;i<cardLength-4;i++){
            maskedCard += 'X';
        }

        maskedCard += cardNo.substring(cardLength-4);
        return maskedCard;
    }
}
