package com.elearning.Dto;

import com.elearning.enums.Currency;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentIntentDto {

     private String via;
     private Long courseId;
}
