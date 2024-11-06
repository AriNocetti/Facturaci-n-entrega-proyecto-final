package com.coderhouse.java.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.java.DTO.ActualizarProductoDTO;
import com.coderhouse.java.excepciones.ProductoInexistenteException;
import com.coderhouse.java.modelo.Producto;
import com.coderhouse.java.repositorios.ProductoRepositorio;

@Service
public class ServicioDeProductos {
    @Autowired
    private ProductoRepositorio productoRepositorio;


    public Producto  obtenerProductoPorId(Long id) {
        Optional<Producto> opcional = productoRepositorio.findById(id);
        return opcional.orElseThrow(ProductoInexistenteException::new);
    }

    public List<Producto>obtenerTodos() {
        return productoRepositorio.findAll();
    }

    public Producto actualizar(Long id, ActualizarProductoDTO productoActualizado) {
        Producto producto = obtenerProductoPorId(id);

        if (productoActualizado.getNombre() != null) {
            producto.setNombre(productoActualizado.getNombre());
        }
        if (productoActualizado.getPrecio() != null) {
            producto.setPrecio(productoActualizado.getPrecio());
        }
        if (productoActualizado.getStock() != null) {
            producto.setStock(productoActualizado.getStock());
        }

        productoRepositorio.save(producto);
        return producto;
    }
}



