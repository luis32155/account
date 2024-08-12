package com.exercise.account_service.service;

import com.exercise.account_service.entity.Cuenta;

import java.util.Optional;

public interface CuentaService {

    Optional<Cuenta> findCuentaByClienteId(Long clienteId);

    Cuenta actualizarCuentaConNuevoCliente(Cuenta cuenta, String nuevoNombreCliente) ;
}
