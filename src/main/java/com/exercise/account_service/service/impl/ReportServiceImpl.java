package com.exercise.account_service.service.impl;

import com.exercise.account_service.dto.CuentaDTO;
import com.exercise.account_service.dto.MovimientoDTO;
import com.exercise.account_service.dto.ReporteDTO;
import com.exercise.account_service.entity.Cuenta;
import com.exercise.account_service.entity.Movimiento;
import com.exercise.account_service.exception.ResourceNotFoundException;
import com.exercise.account_service.repository.CuentaRepository;
import com.exercise.account_service.repository.MovimientoRepository;
import com.exercise.account_service.service.ReportService;
import com.exercise.account_service.util.Constanst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;



    @Override
    public ReporteDTO generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        // Obtener las cuentas del cliente
        Optional<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        if (cuentas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron cuentas para el cliente ID: " + clienteId);
        }

        // Crear el reporte utilizando los datos obtenidos
        List<CuentaDTO> cuentaDTOs = cuentas.stream()
                .map(cuenta -> {
                    // Obtener movimientos para cada cuenta en el rango de fechas
                    List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(
                            cuenta.getId(), fechaInicio, fechaFin);

                    // Convertir a DTOs
                    List<MovimientoDTO> movimientoDTOs = movimientos.stream()
                            .map(movimiento -> MovimientoDTO.builder()
                                    .id(movimiento.getId())
                                    .fecha(movimiento.getFecha())
                                    .tipoMovimiento(movimiento.getTipoMovimiento())
                                    .valor(movimiento.getValor())
                                    .saldo(movimiento.getSaldo())
                                    .build())
                            .collect(Collectors.toList());

                    return CuentaDTO.builder()
                            .id(cuenta.getId())
                            .numeroCuenta(cuenta.getNumeroCuenta())
                            .tipoCuenta(cuenta.getTipoCuenta())
                            .saldoInicial(cuenta.getSaldoInicial())
                            .estado(cuenta.isEstado())
                            .movimientos(movimientoDTOs)
                            .build();
                })
                .collect(Collectors.toList());

        // Construir el reporte final
        return ReporteDTO.builder()
                .clienteId(clienteId)
                .clienteNombre(cuentas.get().getClienteNombre())
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .cuentas(cuentaDTOs)
                .build();
    }


}
