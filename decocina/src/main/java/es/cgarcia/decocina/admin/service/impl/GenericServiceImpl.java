package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.ModelBase;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Implementacion de los métodos CRUD.
 * 
 * @author Miguel Ángel Álvarez García
 *
 * @param <M> Model Modelo con el que va a trabajar el Dao.
 * @param <D> Dao Dao que hará las operaciones del servicio.
 */ 
@Transactional
public class GenericServiceImpl<M extends ModelBase,D extends IGenericDao<M>> implements IGenericService<M>
{

	/**
	 * Interfaz genérica de un Dao.
	 */
	protected D dao;
	
	/**
	 * Construtor. 
	 * 
	 * @param dao Especialización del Dao.
	 */
	public GenericServiceImpl(D dao){
		this.dao = dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countFindAll() {
		return dao.countFindAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<M> findAll() {
		return dao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public M findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(M entity) {
		dao.insert(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public M update(M entity) {
		return dao.update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(M entity) {
		dao.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<M> findAll(Long start, Long limit) {
		return dao.findAll(start, limit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countFindAll(Long start, Long limit) {
		return dao.countFindAll(start, limit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<M> findAll(Paginacion paginacion) {
		return dao.findAll(paginacion);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countFindAll(Paginacion paginacion) {
		return dao.countFindAll(paginacion);
	}
}
