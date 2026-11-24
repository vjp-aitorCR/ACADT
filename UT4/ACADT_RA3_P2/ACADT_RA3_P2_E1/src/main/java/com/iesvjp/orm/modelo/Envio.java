package com.iesvjp.orm.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad Envio · ACADT · RA3 · P2.
 *
 * Tabla envios. Las dos direcciones (origen y destino) son del mismo tipo embebido
 * (Direccion), así que cada una necesita @AttributeOverride para tener sus propias columnas.
 */
@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // AUTO_INCREMENT en MySQL
    private Long id;

    @Column(name = "codigo_seguimiento", nullable = false, unique = true, length = 12)
    private String codigoSeguimiento;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    // Peso en kg
    @Column(name = "peso", nullable = false)
    private Double peso;

    // LocalDateTime -> DATETIME
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    // Opcional (nullable por defecto). LocalDate -> DATE
    @Column(name = "fecha_entrega_prevista")
    private LocalDate fechaEntregaPrevista;

    @Embedded
    @AttributeOverride(name = "via", column = @Column(name = "origen_via", nullable = false))
    @AttributeOverride(name = "codigoPostal", column = @Column(name = "origen_cp", nullable = false, length = 5))
    @AttributeOverride(name = "poblacion", column = @Column(name = "origen_poblacion", nullable = false))
    @AttributeOverride(name = "provincia", column = @Column(name = "origen_provincia", nullable = false))
    private Direccion origen;

    @Embedded
    @AttributeOverride(name = "via", column = @Column(name = "destino_via", nullable = false))
    @AttributeOverride(name = "codigoPostal", column = @Column(name = "destino_cp", nullable = false, length = 5))
    @AttributeOverride(name = "poblacion", column = @Column(name = "destino_poblacion", nullable = false))
    @AttributeOverride(name = "provincia", column = @Column(name = "destino_provincia", nullable = false))
    private Direccion destino;

    /** Constructor vacío obligatorio para JPA. */
    protected Envio() {
    }

    public Envio(String codigoSeguimiento, String descripcion, Double peso,
            LocalDateTime fechaRegistro, LocalDate fechaEntregaPrevista,
            Direccion origen, Direccion destino) {
        this.codigoSeguimiento = codigoSeguimiento;
        this.descripcion = descripcion;
        this.peso = peso;
        this.fechaRegistro = fechaRegistro;
        this.fechaEntregaPrevista = fechaEntregaPrevista;
        this.origen = origen;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoSeguimiento() {
        return codigoSeguimiento;
    }

    public void setCodigoSeguimiento(String codigoSeguimiento) {
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaEntregaPrevista() {
        return fechaEntregaPrevista;
    }

    public void setFechaEntregaPrevista(LocalDate fechaEntregaPrevista) {
        this.fechaEntregaPrevista = fechaEntregaPrevista;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Envio[id=" + id + ", código=" + codigoSeguimiento + ", " + descripcion
                + ", " + peso + " kg, registrado el " + fechaRegistro
                + ", entrega prevista: " + (fechaEntregaPrevista != null ? fechaEntregaPrevista : "sin fecha")
                + ", origen: " + origen + ", destino: " + destino + "]";
    }
}
