package com.example.clase9ws20232.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva", nullable = false)
    private Integer id;

    @Column(name = "nombrePersona", nullable = false, length = 45)
    private String nombrePersona;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @Column(name = "cupos", nullable = false, length = 45)
    private String cupos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Evento_idEvento", nullable = false)
    private Evento eventoIdevento;

}