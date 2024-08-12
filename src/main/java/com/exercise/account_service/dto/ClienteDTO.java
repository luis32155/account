package com.exercise.account_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;             // ID del cliente
    private String nombre;       // Nombre del cliente
    private String genero;       // Género del cliente
    private int edad;            // Edad del cliente
    private String identificacion;  // Identificación del cliente (por ejemplo, DNI)
    private String direccion;    // Dirección del cliente
    private String telefono;     // Teléfono del cliente
    private String clienteId;    // Identificador único del cliente (si es diferente de 'id')
    private String contrasena;   // Contraseña del cliente
    private boolean estado;      // Estado del cliente (activo/inactivo)
}
