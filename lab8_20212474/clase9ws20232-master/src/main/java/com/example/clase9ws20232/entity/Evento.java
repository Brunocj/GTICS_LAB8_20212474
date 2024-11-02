package com.example.clase9ws20232.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvento", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "categoria", nullable = false, length = 45)
    private String categoria;

    @Column(name = "capacidadMax", nullable = false, length = 45)
    private String capacidadMax;

    @Column(name = "reservasActuales", nullable = false, length = 45)
    private String reservasActuales;

}