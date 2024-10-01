package com.backEnd.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseUploadRequest {


    private String title;
    private String description;
    private double hours;
    private Boolean isCourseFree;
    private BigDecimal price;
    private int discount;
    private Date discountStartDate;
    private Date discountEndDate;
    // private MultipartFile coverImageFile;
}