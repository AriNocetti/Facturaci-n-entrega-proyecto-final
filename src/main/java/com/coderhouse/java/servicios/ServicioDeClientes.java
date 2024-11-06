package com.coderhouse.java.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.java.DTO.ClienteDTO;
import com.coderhouse.java.modelo.Cliente;
import com.coderhouse.java.repositorios.ClienteRepositorio;

@Service
public class ServicioDeClientes {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public ClienteDTO obtenerClientePorId(Long id) {
        Optional<Cliente> opcional = clienteRepositorio.findById(id);
        if (opcional.isPresent()) {
            Cliente cliente = opcional.get();
            String nombre = cliente.getNombre();
            String apellido = cliente.getApellido();
            Date fechaDeNacimiento = cliente.getFechaDeNacimiento();
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setNombre(nombre);
            clienteDTO.setApellido(apellido);
            clienteDTO.setEdad(calcularEdad(fechaDeNacimiento));
            return clienteDTO;
        }
        return null;
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepositorio.findAll();
    }

    public Cliente crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(""); 
        cliente.setFechaDeNacimiento(calcularFechaNacimiento(clienteDTO.getEdad()));

        return clienteRepositorio.save(cliente);
    }

    private Integer calcularEdad(Date fechaDeNacimiento) {
        LocalDate ahora = LocalDate.now();
        LocalDate fecha = fechaDeNacimiento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period period = Period.between(fecha, ahora);
        return Math.abs(period.getYears());
    }

    private Date calcularFechaNacimiento(Integer edad) {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaNacimiento = hoy.minusYears(edad);
        return Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}