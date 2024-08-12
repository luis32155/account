package com.exercise.account_service.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Data
public class ReporteDTO {
    private Long clienteId;
    private String clienteNombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<CuentaDTO> cuentas;  // Lista de cuentas con sus movimientos
}
