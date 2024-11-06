package com.coderhouse.java.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.java.DTO.ClienteDTO;
import com.coderhouse.java.modelo.Cliente;
import com.coderhouse.java.servicios.ServicioDeClientes;


@RestController
@RequestMapping("/clientes")
public class ControladorCliente {

    @Autowired
    private ServicioDeClientes servicioDeClientes;

    @GetMapping("/{id}")
    public ClienteDTO obtenerClientePorId(@PathVariable(name = "id")Long id) {
        return this.servicioDeClientes.obtenerClientePorId(id);
    }
    @GetMapping
    public List<Cliente> obtenerTodos() {
        return this.servicioDeClientes.obtenerClientes();
    }
    
    @PostMapping
    public Cliente crearCliente(@RequestBody ClienteDTO clienteDTO) {
        return this.servicioDeClientes.crearCliente(clienteDTO);
    }
}

