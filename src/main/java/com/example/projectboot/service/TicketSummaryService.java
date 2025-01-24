package com.example.projectboot.service;

import com.example.projectboot.repository.TicketSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TicketSummaryService {

    @Autowired
    private TicketSummaryRepository ticketSummaryRepository;

    // Método para obtener la suma total de tickets atendidos
    public int getTotalTicketsAtendidos(LocalDate startDate, LocalDate endDate) {
        return ticketSummaryRepository.findTotalTicketsAtendidos(startDate, endDate);
    }
}