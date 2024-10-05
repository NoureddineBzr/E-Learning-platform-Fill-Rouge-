package com.mido.elearning.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "acadimic_sepicailizations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AcademicSpecialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
