package com.icop.parcialpromociontroncosol.controller;

import com.icop.parcialpromociontroncosol.dto.*;
import com.icop.parcialpromociontroncosol.service.SocioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/socios")
public class SocioController {
    private final SocioService servicio;
    public SocioController(SocioService servicio) { this.servicio = servicio; }
    @GetMapping public List<ResponseSocioDto> listar() { return servicio.listar(); }
    @GetMapping("/{id}") public ResponseSocioDto buscar(@PathVariable Long id) { return servicio.buscar(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ResponseSocioDto crear(@RequestBody RequestSocioDto dto) { return servicio.crear(dto); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) { servicio.eliminar(id); return ResponseEntity.noContent().build(); }
}
