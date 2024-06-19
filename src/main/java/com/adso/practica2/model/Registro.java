package com.adso.practica2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String description;
}
