package com.icop.parcialpromociontroncosol.controller;

import com.icop.parcialpromociontroncosol.dto.*;
import com.icop.parcialpromociontroncosol.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    private final PrestamoService servicio;
    public PrestamoController(PrestamoService servicio) { this.servicio = servicio; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ResponsePrestamoDto crear(@RequestBody RequestPrestamoDto dto) { return servicio.crear(dto); }
    @GetMapping("/socio/{id}")
    public List<ResponsePrestamoDto> porSocio(@PathVariable Long id) { return servicio.porSocio(id); }
}
