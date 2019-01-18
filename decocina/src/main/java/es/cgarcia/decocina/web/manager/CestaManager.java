/**
 * 
 */
package es.cgarcia.decocina.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Impuesto;
import es.cgarcia.decocina.web.model.ImpuestoTotal;
import es.cgarcia.decocina.web.wrapper.ArticuloCestaWrapper;

/**
 * Administrador de la cesta.
 * Permite añadir y quitar Artículos. Calcular el peso, el precio total etc.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public class CestaManager implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -3170414431716597457L;
	
	/**
	 * Artículos de la Cesta.
	 */
	private List<ArticuloCestaWrapper> articulos = new ArrayList<ArticuloCestaWrapper>();;
	
	/**
	 * Impuestos Totales de la Cesta.
	 */
	private List<ImpuestoTotal> impuestosTotales = new ArrayList<ImpuestoTotal>();
	
	/**
	 * Cliente de la cesta.
	 */
	private Cliente cliente;
	
	/**
	 * Constructor.
	 * @param cliente Cliente dueño de la cesta.
	 */
	public CestaManager(Cliente cliente){
		this.cliente = cliente;
	}
	
	/**
	 * Añade el cliente a la Cesta.
	 * @param cliente Cliente.
	 */
	public void setCliente(Cliente cliente) {
		
		this.cliente = cliente;

		actualizarClienteArticulos();
	}
	
	/**
	 * Actualiza el Cliente en los Artículos.
	 */
	private void actualizarClienteArticulos(){
		
		List<ArticuloCestaWrapper> articulosCestaWrapper =  getCesta();
		
		for (ArticuloCestaWrapper articuloCestaWrapper : articulosCestaWrapper) {
			
			articuloCestaWrapper.setCliente(cliente);
		}
	}
	
	/**
	 * Añade un Artículo a la Cesta. Si ya existe actualiza la cantidad.
	 * @param id Id del Artículo.
	 * @param cantidad Cantidad del Artículo.
	 */
	public void addArticulo(Articulo articulo, int cantidad){
		
		Long idArticulo = articulo.getId();
		
		ArticuloCestaWrapper articuloCestaWrapper = buscarArticulo(idArticulo);
		
		if(articuloCestaWrapper == null){
			
			articuloCestaWrapper = new ArticuloCestaWrapper(cliente, articulo, cantidad);
			articulos.add(articuloCestaWrapper);
			
		}else
		{
			//Aumentar cantidad.
			Integer cantidadArticulo = articuloCestaWrapper.getCantidad();
			articuloCestaWrapper.setCantidad(cantidadArticulo + cantidad);
		}
	}
	
	/**
	 * Actualiza la cantidad del Artículo en la Cesta.
	 * @param id Id del Artículo.
	 * @param cantidad Cantidad del Artículo.
	 */
	public void actualizarCantidadArticulo(Articulo articulo, int cantidad){
		
		Long idArticulo = articulo.getId();
		
		ArticuloCestaWrapper articuloCestaWrapper = buscarArticulo(idArticulo);
		
		articuloCestaWrapper.setCantidad(cantidad);
		
	}
	
	/**
	 * Elimina un Artículo de la Cesta.
	 * @param articulo Artículo a eliminar. 
	 */
	public void eliminarArticulo(Articulo articulo) {
		
		for(Iterator<ArticuloCestaWrapper> i = articulos.iterator(); i.hasNext();){
			
			ArticuloCestaWrapper articuloCestaWrapper = i.next();
			Articulo articuloCesta =  articuloCestaWrapper.getArticulo();
			
			Long idArticulo = articulo.getId();
			Long idArticuloCesta = articuloCesta.getId();
			
			if(idArticulo.equals(idArticuloCesta)){
				i.remove();
			}
		}
	}
	
	/**
	 * Devuelve la lista de Artículos en orden inverso.
	 * @return Devuelve la lista de Artículos en orden inverso.
	 */
	public List<ArticuloCestaWrapper> getCesta(){
		
		List<ArticuloCestaWrapper> articulosReverse = new ArrayList<ArticuloCestaWrapper>();
		
		for(ArticuloCestaWrapper articuloCestaWrapper : articulos)
		{
			articulosReverse.add(0, articuloCestaWrapper);
		}
		
		return articulosReverse;
	}
	
	/**
	 * Devuelve el número de Artículos en la cesta.
	 * @return Número de Articulos en la cesta.
	 */
	public Integer getNumeroArticulos(){
		
		Integer numeroArticulos = 0;
		
		for(ArticuloCestaWrapper articuloCestaWrapper : articulos)
		{
			numeroArticulos += articuloCestaWrapper.getCantidad();
		}
		
		return numeroArticulos;
	}
	
	/**
	 * Devuelve el precio total de la cesta. Sin los gastos de envío.
	 * @return Devuelve el precio total de la cesta. Sin los gastos de envío.
	 */
	public Double getSubTotal(){
		
		Double subTotal = 0D;
		
		for(ArticuloCestaWrapper articuloCestaWrapper : articulos)
		{
			subTotal += articuloCestaWrapper.getPrecio();
		}
		
		return subTotal;
	}
	
	/**
	 * Devuelve los totales por cada Impuesto. 
	 * @return Totales por cada Impuesto.
	 */
	public List<ImpuestoTotal> getImpuestosTotales(){
		
		//Borramos los Impuestos totales anteriores y volvemos a calcular.
		impuestosTotales.clear();
		
		List<ArticuloCestaWrapper> articulosCestaWrapper = getCesta();
		
		for (ArticuloCestaWrapper articuloCestaWrapper : articulosCestaWrapper) {
			
			Articulo articulo = articuloCestaWrapper.getArticulo();
			
			Impuesto impuesto = articulo.getImpuesto();
			
			Double valor = impuesto.getValor();
			
			Boolean existe = existeImpuesto(valor);
			
			if(!existe){
				
				ImpuestoTotal impuestoTotal = new ImpuestoTotal();
				impuestoTotal.setImpuesto(impuesto);
				
				Double total = getImpuestoTotal(impuestoTotal);
				
				impuestoTotal.setTotal(total);
				
				impuestosTotales.add(impuestoTotal);
			}
		}
		
		return impuestosTotales;
	}
	
	/**
	 * Comprueba si ya existe el Impuesto.
	 * @param valor Valor del impuesto a buscar.
	 * @return True si existe el Impuesto.
	 */
	private Boolean existeImpuesto(Double valor){
		
		for (ImpuestoTotal impuestoTotal : impuestosTotales) {
			
			Impuesto impuesto = impuestoTotal.getImpuesto();
			
			if(impuesto.getValor().equals(valor)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Calcula el Impuesto total de todos los Artículos que tengan el Impuesto.
	 * @param impuestoTotal Impuesto Total.
	 * @return el Impuesto.
	 */
	private Double getImpuestoTotal(ImpuestoTotal impuestoTotal)
	{	
		List<ArticuloCestaWrapper> articulosCestaWrapper = getCesta();
		
		Double total = 0D;
		
		for (ArticuloCestaWrapper articuloCestaWrapper : articulosCestaWrapper) {
			
			Impuesto impuesto = impuestoTotal.getImpuesto();
			
			Double valor = impuesto.getValor();
			
			Articulo articulo = articuloCestaWrapper.getArticulo();
			Impuesto articuloImpuesto = articulo.getImpuesto();
			
			Double precio = articuloCestaWrapper.getPrecio();
			
			if(valor.equals(articuloImpuesto.getValor())){
				total += precio;
			}
		
		}
		
		if(total != 0){
			
			Impuesto impuesto = impuestoTotal.getImpuesto();
			
			Double valor = impuesto.getValor();
			
			total = total - (total / ((valor / 100) + 1));
		}
		
		return total;
	}
	
	/**
	 * Busca un Artículo en la Cesta por su id.
	 * @param id Id del Artículo.
	 * @return Cesta encontrada o null si el Artículo no está es la Cesta.
	 */
	public ArticuloCestaWrapper buscarArticulo(Long id){
		
		for (ArticuloCestaWrapper articuloCestaWrapper : articulos) {
			
			Articulo articuloCesta = articuloCestaWrapper.getArticulo();
			
			Long idArticulo = articuloCesta.getId();
			
			if(idArticulo.equals(id)){
				
				return articuloCestaWrapper;
			}
		}
		//No existe el Articulo en la Cesta. Devolvemos null.
		return null;
	}
	
	/**
	 * Devuelve el peso de todos los articulos de la cesta.
	 * @return Peso de todos los Artículos de la Cesta.
	 */
	public Double getPeso(){
		
		Double peso = 0D;
		
		for(ArticuloCestaWrapper articuloCestaWrapper : articulos)
		{
			peso += articuloCestaWrapper.getPeso();
		}
		
		return peso;
	}

	/**
	 * Elimina todos los Artículos de la Cesta.
	 */
	public void vaciarCesta() {
		
		articulos.clear();
		impuestosTotales.clear();
	}
}
