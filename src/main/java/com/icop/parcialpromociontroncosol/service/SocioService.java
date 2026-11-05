package com.icop.parcialpromociontroncosol.service;

import com.icop.parcialpromociontroncosol.dto.*;
import com.icop.parcialpromociontroncosol.entity.Socio;
import com.icop.parcialpromociontroncosol.exception.*;
import com.icop.parcialpromociontroncosol.repository.SocioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SocioService {
    private final SocioRepository socios;
    public SocioService(SocioRepository socios) { this.socios = socios; }
    @Transactional(readOnly = true)
    public List<ResponseSocioDto> listar() { return socios.findAll().stream().map(this::convertir).toList(); }
    @Transactional(readOnly = true)
    public ResponseSocioDto buscar(Long id) { return convertir(obtenerEntidad(id)); }
    @Transactional(readOnly = true)
    public Socio obtenerEntidad(Long id) {
        return socios.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));
    }
    @Transactional
    public ResponseSocioDto crear(RequestSocioDto dto) {
        if (socios.existsByDni(dto.dni())) throw new ReglaNegocioException("Ya existe un socio con el DNI: " + dto.dni());
        Socio socio = new Socio();
        socio.setNombre(dto.nombre()); socio.setApellido(dto.apellido()); socio.setDni(dto.dni());
        return convertir(socios.save(socio));
    }
    @Transactional
    public void eliminar(Long id) {
        Socio socio = obtenerEntidad(id);
        if (!socio.getPrestamos().isEmpty()) throw new ReglaNegocioException("No se puede eliminar el socio con id: " + id + " porque tiene préstamos asociados");
        socios.delete(socio);
    }
    private ResponseSocioDto convertir(Socio socio) { return new ResponseSocioDto(socio.getNombreCompleto(), socio.getDni()); }
}
