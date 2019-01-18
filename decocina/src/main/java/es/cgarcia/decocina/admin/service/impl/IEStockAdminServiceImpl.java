package es.cgarcia.decocina.admin.service.impl;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IArticuloAdminDao;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.admin.service.IIEStockAdminService;
import es.cgarcia.decocina.admin.util.IEStockUtils;
import es.cgarcia.decocina.web.model.Articulo;


/**
 * Interfaz que contiene los metodos necesarios para trabajar con la importación y la exportación del stock.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("ieStockAdminService")
public class IEStockAdminServiceImpl implements IIEStockAdminService{
	
	/**
	 * Dao para los Artículos.
	 */
	@Autowired
	private IArticuloAdminDao articuloAdminDao;
	
	/**
	 * {@inheritDoc}
	 */
	public OutputStream exportarStock(String rutaImagenes){
		
		//Recuperamos todos los Articulos.
		List<Articulo> articulos = articuloAdminDao.findAllExport();
		
		//Recuperamos el stream que contiene el excel.
		OutputStream os = IEStockUtils.exportar(rutaImagenes, articulos);
		
		return os;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void importar(byte[] bFichero){
		
		ByteArrayInputStream bais = new ByteArrayInputStream(bFichero);
		
		try{
			
			Workbook workbook = WorkbookFactory.create(bais);
			
			Sheet worksheet = workbook.getSheet("Stock decocina");
		
			Integer numeroFilas = worksheet.getLastRowNum();
			
			for(int i = 2; i < numeroFilas; i++){
				
				Row row = worksheet.getRow(i);
				
				//Recuperamos el id.
				Cell idCell = row.getCell(0);
				
				if(idCell != null){
				
					Integer id = null;
					Integer cantidad = null;
					
					Double dbId = idCell.getNumericCellValue();
					
					if(dbId != null){
						
						id = dbId.intValue();
					}
					
					//Recuperamos la cantidad. 
					Cell cantidadCell = row.getCell(3);
					Double dbCantidad = cantidadCell.getNumericCellValue();
					
					if(dbCantidad != null){
						
						cantidad = dbCantidad.intValue();
					}
					
					//Recuperamos el campo 'activo'. 
					Cell activoCell = row.getCell(4);
					Boolean activo  = activoCell.getBooleanCellValue();
					
					if(id != null && cantidad != null && activo != null){
					
						articuloAdminDao.updateFromImport(id, cantidad, activo);
					}
					
				}
			}
			
			workbook.close();
			
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Se ha producido un error importando el excel.", e);
			
		}
	}
}
