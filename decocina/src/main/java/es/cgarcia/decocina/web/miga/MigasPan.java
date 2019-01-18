/**
 * 
 */
package es.cgarcia.decocina.web.miga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Miga de Pan.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class MigasPan implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -4339258896606806625L;
	
	/**
	 * Contiene las Migas de Pan.
	 */
	public List<MigaPan> migas = new ArrayList<MigaPan>();
	
	/**
	 * Añade una Miga de Pan.
	 * @param migaPan Miga de Pan.
	 */
	public void addMiga(MigaPan migaPan){
		migas.add(migaPan);
	}
	
	/**
	 * Devuelve las Migas de Pan.
	 * @return Lista con las Migas de Pan.
	 */
	public List<MigaPan> getMigasPan(){
		return migas;
	}
}
