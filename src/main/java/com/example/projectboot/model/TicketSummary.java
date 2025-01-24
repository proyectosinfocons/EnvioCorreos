package com.example.projectboot.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TicketSummary {

    @Id
    private Date fecha;
    private Integer año;
    private Integer mes;
    private Integer dia;
    private String hora;
    private String canal;
    private Integer ticketsatendidos;

    // Getters y setters
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Integer getTicketsatendidos() {
        return ticketsatendidos;
    }

    public void setTicketsatendidos(Integer ticketsatendidos) {
        this.ticketsatendidos = ticketsatendidos;
    }
}