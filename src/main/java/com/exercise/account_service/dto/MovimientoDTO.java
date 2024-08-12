package com.exercise.account_service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovimientoDTO {
    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    private CuentaDTO cuenta;
}
