package com.coderhouse.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.coderhouse.java.modelo.Cliente;
import com.coderhouse.java.modelo.Producto;
import com.coderhouse.java.repositorios.ClienteRepositorio;
import com.coderhouse.java.repositorios.ProductoRepositorio;

@Component
public class Runner implements ApplicationRunner {
    @Autowired
    private ClienteRepositorio repositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Date fechaDeNacimiento = Date.from(LocalDate.parse("2001-05-17")
            .atStartOfDay()
            .atZone(ZoneId.systemDefault()).toInstant());
        Cliente cliente = new Cliente();
        cliente.setNombre("Ariana");
        cliente.setApellido("nocetti");
        cliente.setEmail("ariNocetti@gmail.com");
        cliente.setFechaDeNacimiento(fechaDeNacimiento);
        repositorio.save(cliente);

        Producto producto = new Producto();
        producto.setNombre("Jean MOM");
        producto.setStock(20);
        producto.setPrecio(BigDecimal.valueOf(25000));
        productoRepositorio.save(producto);

        Producto productoDos = new Producto();
        productoDos.setNombre("Conjunto Diosa");
        productoDos.setStock(10);
        productoDos.setPrecio(BigDecimal.valueOf(35000));
        productoRepositorio.save(productoDos);

        Producto productoTres = new Producto();
        productoTres.setNombre("Sweater ultrabig");
        productoTres.setStock(24);
        productoTres.setPrecio(BigDecimal.valueOf(16000));
        productoRepositorio.save(productoTres);

        Producto productoCuatro = new Producto();
        productoCuatro.setNombre("Croptop Melanie");
        productoCuatro.setStock(0);
        productoCuatro.setPrecio(BigDecimal.valueOf(6800));
        productoRepositorio.save(productoCuatro);
    }
}
