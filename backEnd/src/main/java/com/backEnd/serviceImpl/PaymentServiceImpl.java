package com.backEnd.serviceImpl;

import com.backEnd.entity.AppUser;
import com.backEnd.entity.Course;
import com.backEnd.mapping.CourseMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class PaymentServiceImpl {

    String secretKey ="sk_test_51LYUvnBVDibabXHi0Ay8kwZHh30CH4rl1UDGWqDqRNsXIVv9opL4UbW0LPVwWfT6mgQvxFkP3834prD0BRYTS4RC00ai21Y63S";


    @Autowired
    UserServiceImpl userService;

    @Autowired
    CourseServiceImpl courseService;

    public String getClientSecret() throws StripeException {
        Stripe.apiKey = secretKey;




        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(2000L)
                        .setCurrency("usd")
                        .setCustomer("1")
                        .addAllPaymentMethodType(Collections.singletonList("card"))
                        .build();


        return PaymentIntent.create(params).getClientSecret();
    }

    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey=secretKey;

        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "card");
        paymentIntent.confirm(params);
        return paymentIntent;
    }

    public PaymentIntent cancel(String id) throws StripeException{
        Stripe.apiKey=secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }

    public String checkout(Long courseId) throws StripeException {

        AppUser user = userService.getCurrentAuthUser();
        Course courseDate = CourseMapper.dtoToEntity(courseService.findById(courseId));


        Stripe.apiKey = secretKey;

        CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
                .setName(user.getFirstName() + " "+ user.getLastName())
                .setEmail(user.getEmail())
                .build();

        Customer customer = Customer.create(customerCreateParams);

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(courseDate.getPrice().longValue())
                        .setCurrency("usd")
                        .setCustomer(customer.getId())
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return paymentIntent.getClientSecret();
    }
}
