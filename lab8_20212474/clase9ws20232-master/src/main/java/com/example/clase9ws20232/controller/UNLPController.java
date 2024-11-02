package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Evento;
import com.example.clase9ws20232.entity.Reserva;
import com.example.clase9ws20232.repository.EventoRepository;
import com.example.clase9ws20232.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/UNLP")

public class UNLPController {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ReservaRepository reservaRepository;


    @GetMapping(value = {"/listaEventos", ""})
    public List<Evento> listaProductos(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            return eventoRepository.findByFecha(localDate);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use el formato dd/MM/yyyy.");
        }
    }

    @PostMapping("/crearEvento")
    public Evento createEvent(@RequestBody Evento evento) {
        evento.setReservasActuales("0");
        return eventoRepository.save(evento);
    }

    @PostMapping("/reservar/{idEvento}")
    public Reserva createReservation( @PathVariable  Integer idEvento, @RequestBody Reserva reservation) {
        Evento event = eventoRepository.findById(idEvento).orElseThrow();
        reservation.setEventoIdevento(event);
        if (Integer.parseInt(event.getReservasActuales()) + Integer.parseInt(reservation.getCupos()) > Integer.parseInt(event.getCapacidadMax())) {
            throw new RuntimeException("No hay cupos disponibles");
        }
        event.setReservasActuales(event.getReservasActuales() + reservation.getCupos());
        eventoRepository.save(event);
        return reservaRepository.save(reservation);
    }

    @PostMapping("/cancelarReserva/{id}")
    public void cancelReservation(@PathVariable Integer id) {
        Reserva reservation = reservaRepository.findById(id).orElseThrow();
        Evento event = eventoRepository.findById(reservation.getEventoIdevento().getId()).orElseThrow();
        event.setReservasActuales(String.valueOf(Integer.parseInt(event.getReservasActuales() )- Integer.parseInt((reservation.getCupos()))));
        eventoRepository.save(event);
        reservaRepository.deleteById(id);
    }
}
