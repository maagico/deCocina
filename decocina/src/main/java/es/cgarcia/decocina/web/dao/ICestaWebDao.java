package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.Cesta;


/**
 * Interfaz del Dao para las Cestas de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICestaWebDao extends IGenericDao<Cesta> {

	/**
	 * Actualiza la cantidad del Artículo.
	 * @param idCliente Id del Cliente.
	 * @param idArticulo Id del Artículo.
	 * @param cantidad Cantidad a actualizar.
	 */
	void actualizarCantidad(Long idCliente, Long idArticulo, Integer cantidad);

	/**
	 * Busca un Artículo en la Cesta por el id del Cliente y el Artículo.
	 * @param idCliente Id del Cliente.
	 * @param idArticulo Id del Artículo.
	 * @return Artículo de la Cesta encontrado. 
	 */
	Cesta findByIdClienteAndIdArticulo(Long idCliente, Long idArticulo);

	/**
	 * Devuelve todos los Artículos de un Cliente.
	 * @param idCliente Id del Cliente.
	 * @return Articulos.
	 */
	List<Cesta> findByIdCliente(Long idCliente);

	/**
	 * Borra un Artículo de la Cesta.
	 * @param idCliente Id del CLiente.
	 * @param idArticulo Id del Artículo.
	 */
	void deleteByIdClienteAndIdArticulo(Long idCliente, Long idArticulo);

	/**
	 * Borra la Cesta de un Cliente.
	 * @param idCliente Id del Cliente.
	 */
	void deleteByIdCliente(Long idCliente);
}