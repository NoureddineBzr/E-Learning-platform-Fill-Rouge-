package com.elearning.Dto;

import com.elearning.enums.Country;
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
