package com.kristalika.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Clients")
public class Clients {
    public Clients() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long appointmentId;
    private String clientName;
    private String info;
    private String note;

    public Clients(Long appointmentId, String clientName, String info, String note) {
        this.clientName = clientName;
        this.info = info;
        this.note = note;
        this.appointmentId = appointmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
