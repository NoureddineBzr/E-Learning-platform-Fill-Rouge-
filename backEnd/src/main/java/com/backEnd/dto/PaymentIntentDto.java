package com.backEnd.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentIntentDto {

    private String via;
    private Long courseId;
}

