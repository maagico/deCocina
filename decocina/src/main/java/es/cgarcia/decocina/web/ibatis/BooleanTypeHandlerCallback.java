package es.cgarcia.decocina.web.ibatis;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * Manejador de iBatis para los Booleanos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class BooleanTypeHandlerCallback implements TypeHandlerCallback
{
	/**
	 * Convierte un Boolean a int.
	 */
	@Override
	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		
		Boolean parametro = (Boolean) parameter;  
		
		int valor = 0;
		
		if(parametro != null && parametro.equals(Boolean.TRUE)){
			
			valor = 1;
		}
		
		setter.setInt(valor);  
	}
	
	/**
	 * Convierte un int en Boolean.
	 */
	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		
		int valor = getter.getInt();
		
		Boolean resultado = false;
		
		if(valor == 1)
		{   
			resultado = true;
		}
		
		return resultado;  
	}

	/**
	 * ValueOf.
	 */
	@Override
	public Object valueOf(String s) {
		
		return s;
	}
}
