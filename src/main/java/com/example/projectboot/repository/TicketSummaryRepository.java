package com.example.projectboot.repository;

import com.example.projectboot.model.TicketSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface TicketSummaryRepository extends JpaRepository<TicketSummary, Date> {

    // Consulta para obtener la suma de tickets atendidos
    @Query(value = "SELECT SUM(ticketsatendidos) FROM public.automanagement_dva_tickets(:startDate, :endDate)", nativeQuery = true)
    int findTotalTicketsAtendidos(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}