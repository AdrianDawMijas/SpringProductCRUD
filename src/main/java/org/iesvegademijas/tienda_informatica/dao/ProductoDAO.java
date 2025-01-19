package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Producto;

public interface ProductoDAO{

    public void create(Producto producto);

    public List<Producto> getAll();
    public Optional<Producto>  find(int id);

    public void update(Producto producto);

    public void delete(int id);

}