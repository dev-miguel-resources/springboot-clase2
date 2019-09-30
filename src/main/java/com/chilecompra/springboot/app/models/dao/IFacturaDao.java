package com.chilecompra.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chilecompra.springboot.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{
	
	@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1") //obtiene las facturas por el id del cliente con todo el detalle
	public Factura fetchByIdWithClienteWhithItemFacturaWithProducto(Long id); //facturas con todas las relaciones de una sola vez
}