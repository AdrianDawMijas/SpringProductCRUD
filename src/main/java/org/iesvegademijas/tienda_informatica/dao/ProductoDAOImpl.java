package org.iesvegademijas.tienda_informatica.dao;


import java.util.List;
import java.util.Optional;
import org.iesvegademijas.tienda_informatica.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ProductoDAOImpl  implements ProductoDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Inserta en base de datos el nuevo fabricante, actualizando el id en el bean fabricante.
     */
    @Override
    public synchronized void create(Producto producto) {

        jdbcTemplate.update("INSERT INTO producto (nombre, precio, id_fabricante) VALUES (?, ?, ?)",producto.getNombre(),
                producto.getPrecio(),producto.getId_fabricante());

    }

    /**
     * Devuelve lista con todos loa fabricantes.
     */
    @Override
    public List<Producto> getAll() {

        List<Producto> listProd = jdbcTemplate.query(
                "SELECT * FROM producto",
                (rs, rowNum) -> new Producto(rs.getInt("codigo"),rs.getString("nombre"),rs.getDouble("precio"),rs.getInt("id_fabricante"))
        );

        return listProd;

    }

    /**
     * Devuelve Optional de fabricante con el ID dado.
     */
    @Override
    public Optional<Producto> find(int id) {

        Producto prod =  jdbcTemplate
                .queryForObject("SELECT * FROM producto WHERE codigo = ?"
                        , (rs, rowNum) -> new Producto(rs.getInt("codigo"),rs.getString("nombre"),rs.getDouble("precio"),rs.getInt("id_fabricante"))
                        , id
                );

        if (prod != null) return Optional.of(prod);
        else return Optional.empty();

    }
    /**
     * Actualiza fabricante con campos del bean fabricante según ID del mismo.
     */
    @Override
    public void update(Producto producto) {

        int rows = jdbcTemplate.update("UPDATE producto SET nombre = ?, precio = ?, id_fabricante = ? WHERE codigo = ?", producto.getNombre(), producto.getPrecio(), producto.getId_fabricante(), producto.getCodigo());
        if (rows == 0) System.out.println("Update de producto con 0 registros actualizados.");

    }

    /**
     * Borra producto con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        int rows = jdbcTemplate.update("DELETE FROM producto WHERE codigo = ?", id);

        if (rows == 0) System.out.println("Update de producto con 0 registros actualizados.");

    }

}
