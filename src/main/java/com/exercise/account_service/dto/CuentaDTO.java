package com.exercise.account_service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CuentaDTO {

    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private boolean estado;
    private List<MovimientoDTO> movimientos;
}
