package com.examt2.examt2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID de la orden

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;  // ID del cliente

    @NotNull(message = "El ID del libro es obligatorio")
    private Long libroId;    // ID del libro

    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de la orden es obligatoria")
    private Date fechaOrden;  // Fecha de la orden

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }
}
