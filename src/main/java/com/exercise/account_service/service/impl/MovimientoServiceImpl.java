package com.exercise.account_service.service.impl;

import com.exercise.account_service.dto.MovimientoDTO;
import com.exercise.account_service.entity.Cuenta;
import com.exercise.account_service.entity.Movimiento;
import com.exercise.account_service.exception.ResourceNotFoundException;
import com.exercise.account_service.exception.SaldoInsuficienteException;
import com.exercise.account_service.mapper.MovimientoMapper;
import com.exercise.account_service.repository.CuentaRepository;
import com.exercise.account_service.repository.MovimientoRepository;
import com.exercise.account_service.service.MovimientoService;
import com.exercise.account_service.util.Constanst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    public MovimientoDTO saveMovimiento(MovimientoDTO movimientoDTO) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getCuenta().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Constanst.ERROR_CUENTA_NO_ENCONTRADA));

        BigDecimal valorMovimiento = movimientoDTO.getValor();
        BigDecimal nuevoSaldo;

        if (valorMovimiento.compareTo(BigDecimal.ZERO) < 0 && cuenta.getSaldoInicial().compareTo(valorMovimiento.abs()) < 0) {
            throw new SaldoInsuficienteException(Constanst.ERROR_SALDO_NO_DISPONIBLE);
        }

        nuevoSaldo = cuenta.getSaldoInicial().add(valorMovimiento);

        Movimiento movimiento = Movimiento.builder()
                .fecha(LocalDate.now())
                .tipoMovimiento(valorMovimiento.compareTo(BigDecimal.ZERO) > 0 ? Constanst.TIPO_MOVIMIENTO_DEPOSITO : Constanst.TIPO_MOVIMIENTO_RETIRO)
                .valor(valorMovimiento)
                .saldo(nuevoSaldo)
                .cuenta(cuenta)
                .build();

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento savedMovimiento = movimientoRepository.save(movimiento);

        return movimientoMapper.toDTO(Optional.of(savedMovimiento)).orElseThrow();
    }

    @Override
    public List<MovimientoDTO> getAllMovimientos() {
        return movimientoRepository.findAll().stream()
                .map(movimiento -> movimientoMapper.toDTO(Optional.of(movimiento)).orElseThrow())
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDTO getMovimientoById(Long id) {
        return movimientoRepository.findById(id)
                .map(movimiento -> movimientoMapper.toDTO(Optional.of(movimiento)).orElseThrow())
                .orElseThrow(() -> new ResourceNotFoundException(Constanst.ERROR_MOVIMIENTO_NO_ENCONTRADO));
    }

    @Override
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}
