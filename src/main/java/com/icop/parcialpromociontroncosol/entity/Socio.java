package com.icop.parcialpromociontroncosol.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socio")
public class Socio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100) private String nombre;
    @Column(nullable = false, length = 100) private String apellido;
    @Column(nullable = false, unique = true, length = 20) private String dni;
    @OneToMany(mappedBy = "socio") private List<Prestamo> prestamos = new ArrayList<>();
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public List<Prestamo> getPrestamos() { return prestamos; }
    public String getNombreCompleto() { return nombre + " " + apellido; }
}
