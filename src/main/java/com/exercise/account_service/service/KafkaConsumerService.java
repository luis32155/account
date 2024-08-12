package com.exercise.account_service.service;

import com.exercise.account_service.dto.ClienteDTO;
import com.exercise.account_service.entity.Cuenta;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final CuentaService cuentaService;

    @KafkaListener(topics = "cliente-topic", groupId = "account-service-group")
    public void consumeClienteEvent(ClienteDTO clienteDTO) {
        logger.info("Recibido cliente: {}", clienteDTO);

        // Buscar la cuenta asociada al cliente por clienteId
        Optional<Cuenta> cuentaOptional = cuentaService.findCuentaByClienteId(clienteDTO.getId());

        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();

            // Actualizar la cuenta con el nuevo nombre del cliente
            cuentaService.actualizarCuentaConNuevoCliente(cuenta, clienteDTO.getNombre());

            logger.info("Cuenta actualizada para el cliente ID: {}, nuevo nombre: {}", clienteDTO.getId(), clienteDTO.getNombre());
        } else {
            logger.warn("No se encontró una cuenta para el cliente ID: {}", clienteDTO.getId());
        }
    }
}
