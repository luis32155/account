package com.exercise.account_service.service;

import com.exercise.account_service.dto.ReporteDTO;

import java.time.LocalDate;

public interface ReportService {

    ReporteDTO generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);

}
