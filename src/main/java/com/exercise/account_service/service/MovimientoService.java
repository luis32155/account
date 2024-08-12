package com.exercise.account_service.service;

import com.exercise.account_service.dto.MovimientoDTO;

import java.util.List;

public interface MovimientoService {

    MovimientoDTO saveMovimiento(MovimientoDTO movimientoDTO);
    List<MovimientoDTO> getAllMovimientos();
    MovimientoDTO getMovimientoById(Long id);
    void deleteMovimiento(Long id);
}
