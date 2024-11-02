package com.example.clase9ws20232.repository;

import com.example.clase9ws20232.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Integer> {
    @Query("SELECT e FROM Evento e WHERE e.fecha >= :fecha ORDER BY e.fecha ASC")
    List<Evento> findByFecha(@Param("fecha") LocalDate fecha);
}
