package com.example.iprwcbackend.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "account")

public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String email;
        @Size(min = 6)
        private String password;
        private boolean admin;
}

