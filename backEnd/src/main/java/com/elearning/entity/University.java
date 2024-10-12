package com.elearning.entity;


import com.elearning.enums.Country;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "universities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

    public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Country country;
}
