package com.bzr.elearning.Dto;

import com.bzr.elearning.enums.Country;
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
