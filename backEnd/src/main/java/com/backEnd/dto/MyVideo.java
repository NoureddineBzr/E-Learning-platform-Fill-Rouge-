package com.backEnd.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyVideo {

    private double duration;
    private String fileName;

}