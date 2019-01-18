package es.cgarcia.decocina.web.ibatis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * Manejador iBatis para las fechas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CalendarTypeHandlerCallback implements TypeHandlerCallback
{
	/**
	 * Convierte un Calendar en un Timestamp.
	 */
	@Override
	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		
		GregorianCalendar calendar = (GregorianCalendar) parameter;  
		
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		
		setter.setTimestamp(timestamp);  
	}
	
	/**
	 * Convierte un Timestamp en Calendar.
	 */
	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		
		Timestamp timeStamp = getter.getTimestamp();
		
		Calendar calendar = null;
		
		if(timeStamp != null)
		{   
			calendar = Calendar.getInstance();  
			calendar.setTimeInMillis(timeStamp.getTime());  
		}
		
		return calendar;  
	}

	/**
	 * ValueOf.
	 */
	@Override
	public Object valueOf(String s) {
		
		return s;
	}
}
