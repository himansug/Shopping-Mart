package com.shoppingmart.shoppingverse.config;

import com.shoppingmart.shoppingverse.model.Item;
import com.shoppingmart.shoppingverse.model.Ordered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailing {
    @Autowired
    private JavaMailSender JavaMailSender;

    public void sendMail(Ordered order){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getCustomer().getEmailId());
        message.setFrom("acciojobspring@gmail.com");
        String text = "Congratulations !! your order has been placed\n"+
                "Order Id :"+String.valueOf(order.getOrderId())+"\n";
        for(Item it : order.getItems()){
            text+=it.getProduct().getProductName()+" Quantity : "+it.getRequiredQuantity()+"\n";
        }
        text+="Order Total : Rs "+order.getOrderTotal()+"\n" +
                "Date : "+order.getOrderDate();
        message.setText(text);
        message.setSubject("Shopping Mart");
        JavaMailSender.send(message);
    }
}
