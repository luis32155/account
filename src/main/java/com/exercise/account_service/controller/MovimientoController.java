package com.exercise.account_service.controller;


import com.exercise.account_service.dto.MovimientoDTO;
import com.exercise.account_service.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    /**
     * Endpoint para crear un nuevo movimiento.
     * @param movimientoDTO Datos del movimiento a crear.
     * @return MovimientoDTO del movimiento creado.
     */
    @PostMapping
    public ResponseEntity<MovimientoDTO> createMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO createdMovimiento = movimientoService.saveMovimiento(movimientoDTO);
        return new ResponseEntity<>(createdMovimiento, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener todos los movimientos.
     * @return Lista de MovimientoDTO.
     */
    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> getAllMovimientos() {
        List<MovimientoDTO> movimientos = movimientoService.getAllMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    /**
     * Endpoint para obtener un movimiento por su ID.
     * @param id ID del movimiento a buscar.
     * @return MovimientoDTO del movimiento encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> getMovimientoById(@PathVariable Long id) {
        MovimientoDTO movimiento = movimientoService.getMovimientoById(id);
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    /**
     * Endpoint para eliminar un movimiento por su ID.
     * @param id ID del movimiento a eliminar.
     * @return Respuesta sin contenido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
