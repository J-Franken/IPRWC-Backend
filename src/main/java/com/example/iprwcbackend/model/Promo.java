package com.example.iprwcbackend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "promo")

public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 6)
    private String code;
    @Min(value = 0, message = "Value must be non-negative")
    @Max(value = 100, message = "Percentages cannot exceed 100%")
    private Double discount_in_percentage;
}

