package es.cgarcia.decocina.admin.service;


/**
 * Interfaz que contiene los metodos necesarios para trabajar con las Herramientas
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IHerramientaAdminService{
	
	/**
	 * Borra la caché de Ibatis.
	 */
	void borrarCacheIbatis();

	/**
	 * Indica si el cacheador está activo.
	 * @return True si está activo, false lo contrario.
	 */
	Boolean cacheadorEstaActivo();
	
	/**
	 * Borra el trabajo de cacheo de la web.
	 */
	public void cacheadorBorrar();
	
	/**
	 * Cachea la Web.
	 * @param contexto Contexto de la apliación web.
	 */
	void cachearWeb(String contexto);
}
