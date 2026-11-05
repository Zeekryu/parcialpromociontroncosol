package com.icop.parcialpromociontroncosol.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo_libro", nullable = false, length = 150) private String tituloLibro;
    @Column(name = "fecha_prestamo", nullable = false) private LocalDate fechaPrestamo;
    @Column(nullable = false) private boolean devuelto = false;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socio_id", nullable = false) private Socio socio;
    public Long getId() { return id; }
    public String getTituloLibro() { return tituloLibro; }
    public void setTituloLibro(String tituloLibro) { this.tituloLibro = tituloLibro; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public boolean isDevuelto() { return devuelto; }
    public void setDevuelto(boolean devuelto) { this.devuelto = devuelto; }
    public Socio getSocio() { return socio; }
    public void setSocio(Socio socio) { this.socio = socio; }
}
