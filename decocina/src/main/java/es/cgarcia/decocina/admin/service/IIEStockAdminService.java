package es.cgarcia.decocina.admin.service;

import java.io.OutputStream;


/**
 * Interfaz que contiene los metodos necesarios para trabajar con la importación y la exportación del stock.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IIEStockAdminService{
	
	/**
	 * Exporta el stock a un fichero excel.
	 * @param rutaImagenes Ruta de las imágenes.
	 * @return Stream que contiene el excel.
	 */
	OutputStream exportarStock(String rutaImagenes);

	/**
	 * Importa el stock contenido en el excel.
	 * @param bFichero Fichero.
	 */
	void importar(byte[] bFichero);
}
