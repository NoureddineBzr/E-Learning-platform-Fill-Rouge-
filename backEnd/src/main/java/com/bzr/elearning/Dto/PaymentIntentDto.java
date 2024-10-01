package com.bzr.elearning.Dto;

import com.bzr.elearning.enums.Currency;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentIntentDto {

     private String via;
     private Long courseId;
}
