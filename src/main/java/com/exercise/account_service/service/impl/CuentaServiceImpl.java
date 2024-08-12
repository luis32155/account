package com.exercise.account_service.service.impl;

import com.exercise.account_service.entity.Cuenta;
import com.exercise.account_service.repository.CuentaRepository;
import com.exercise.account_service.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    @Override
    public Optional<Cuenta> findCuentaByClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }

    @Override
    public Cuenta actualizarCuentaConNuevoCliente(Cuenta cuenta, String nuevoNombreCliente) {
        cuenta.setClienteNombre(nuevoNombreCliente);  // Actualiza el nombre del cliente
        return cuentaRepository.save(cuenta);    }
}
