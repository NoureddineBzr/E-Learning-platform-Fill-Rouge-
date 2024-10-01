package com.backEnd.dto;

import com.backEnd.enums.Country;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityDto {

    private long id;
    private String name;
    private Country country;
}
