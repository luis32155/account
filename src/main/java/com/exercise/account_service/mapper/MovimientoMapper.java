package com.exercise.account_service.mapper;

import com.exercise.account_service.dto.MovimientoDTO;
import com.exercise.account_service.entity.Cuenta;
import com.exercise.account_service.entity.Movimiento;
import com.exercise.account_service.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovimientoMapper {

    private final CuentaRepository cuentaRepository;


    public Optional<MovimientoDTO> toDTO(Optional<Movimiento> movimiento) {
        return movimiento.map(mov -> MovimientoDTO.builder()
                .fecha(mov.getFecha())
                .tipoMovimiento(mov.getTipoMovimiento())
                .valor(mov.getValor())
                .saldo(mov.getSaldo())
                .build());
    }

    public Optional<Movimiento> toEntity(Optional<MovimientoDTO> movimientoDTO) {
        return movimientoDTO.map(dto -> {
            Cuenta cuenta = cuentaRepository.findById(dto.getCuenta().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con ID: " + dto.getCuenta().getId()));
            return Movimiento.builder()
                    .fecha(dto.getFecha())
                    .tipoMovimiento(dto.getTipoMovimiento())
                    .valor(dto.getValor())
                    .saldo(dto.getSaldo())
                    .cuenta(cuenta)
                    .build();
        });
    }
}
