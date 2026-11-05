package com.icop.parcialpromociontroncosol.dto;

public record ResponsePrestamoDto(Long id, String tituloLibro, java.time.LocalDate fechaPrestamo, boolean devuelto, String nombreSocio) {}
