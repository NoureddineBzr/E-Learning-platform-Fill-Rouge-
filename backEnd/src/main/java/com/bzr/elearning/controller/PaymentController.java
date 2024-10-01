package com.bzr.elearning.controller;


import com.bzr.elearning.Dto.PaymentIntentDto;
import com.bzr.elearning.serviceImpl.PaymentServiceImpl;
import com.bzr.elearning.utils.AppResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stripe")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;


    @PostMapping("/getClientSecret")
    public ResponseEntity<Object> payment(@RequestBody PaymentIntentDto data) throws StripeException {

        return AppResponse.generateResponse("review_added_success", HttpStatus.OK, paymentService.checkout(data.getCourseId()) ,true);
    }


    @PostMapping("/confirm/{id}")
    public ResponseEntity<Object> confirm(@PathVariable("id")String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirm(id);
        String paymentStr = paymentIntent.toJson();

        return AppResponse.generateResponse("payment_confirmed_success", HttpStatus.OK, paymentStr,true);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Object> cancel(@PathVariable("id")String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();

        return AppResponse.generateResponse("payment_canceled", HttpStatus.OK, paymentStr,true);
    }

}
