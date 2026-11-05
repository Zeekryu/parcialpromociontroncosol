package com.icop.parcialpromociontroncosol.service;

import com.icop.parcialpromociontroncosol.dto.*;
import com.icop.parcialpromociontroncosol.entity.*;
import com.icop.parcialpromociontroncosol.exception.ReglaNegocioException;
import com.icop.parcialpromociontroncosol.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamos;
    private final SocioService socios;
    public PrestamoService(PrestamoRepository prestamos, SocioService socios) {
        this.prestamos = prestamos; this.socios = socios;
    }
    @Transactional
    public ResponsePrestamoDto crear(RequestPrestamoDto dto) {
        Socio socio = socios.obtenerEntidad(dto.socioId());
        if (prestamos.existsByTituloLibroAndDevueltoFalse(dto.tituloLibro()))
            throw new ReglaNegocioException("El libro " + dto.tituloLibro() + " ya se encuentra prestado");
        Prestamo prestamo = new Prestamo();
        prestamo.setSocio(socio);
        prestamo.setTituloLibro(dto.tituloLibro());
        prestamo.setFechaPrestamo(dto.fechaPrestamo());
        prestamo.setDevuelto(false);
        return convertir(prestamos.save(prestamo));
    }
    @Transactional(readOnly = true)
    public List<ResponsePrestamoDto> porSocio(Long id) {
        Socio socio = socios.obtenerEntidad(id);
        return socio.getPrestamos().stream().map(this::convertir).toList();
    }
    private ResponsePrestamoDto convertir(Prestamo p) {
        return new ResponsePrestamoDto(p.getId(), p.getTituloLibro(), p.getFechaPrestamo(), p.isDevuelto(), p.getSocio().getNombreCompleto());
    }
}
