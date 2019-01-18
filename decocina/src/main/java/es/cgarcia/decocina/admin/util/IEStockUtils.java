/**
 * 
 */
package es.cgarcia.decocina.admin.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Imagen;

/**
 * Utilidades para la importación y la exportación del Stock.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public final class IEStockUtils {

	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(IEStockUtils.class);
	
	/**
	 * Constructor.
	 */
	private IEStockUtils(){	
	}
	
	/**
	 * Exporta a un fichero excel el Stock.
	 * @param rutaImagenes Ruta a las imágenes.
	 * @param articulos Articulos a exportar. 
	 * @return File apuntando al excel que contiene la exportación.
	 */
	public static OutputStream exportar(String rutaImagenes, List<Articulo> articulos){
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Stock decocina");
		
		int numeroFila = 0;
		
		//Crea los títulos de excel.
		crearFilaTitulos(sheet, ++numeroFila);
		
		for (Articulo articulo : articulos) {
		
			Long id = articulo.getId();
			
			Imagen imagenT = articulo.getImagenT();
			String nombreImagen = imagenT.getNombre();
			
			StringBuilder sbRutaImagen = new StringBuilder();
			sbRutaImagen.append(rutaImagenes);
			sbRutaImagen.append('/');
			sbRutaImagen.append(nombreImagen);
			
			String nombre = articulo.getNombre();
			Integer cantidad = articulo.getCantidad();
			Boolean activo = articulo.getActivo();
			
			//Crea la fila con los datos.
			crearFila(workbook, sheet, ++numeroFila, id, sbRutaImagen.toString(), nombre, cantidad, activo);
		}
		
		OutputStream os = null;
		
		try{
			
			sheet.setColumnWidth(1, 4000);
			sheet.autoSizeColumn(2);
			
			os = new ByteArrayOutputStream();
			
			workbook.write(os);
	    	os.close();
	    	workbook.close();
	    	
		}catch(IOException e){
			
			throw new DeCocinaAdminRuntimeException("Error creando excel para la exportación", e);
		}
		
		return os;
	}
	
	/**
	 * Crea los títulos de las columnas del excel.
	 * @return Row ue contiene las columnas con los títulos del excel.
	 */
	private static Row crearFilaTitulos(HSSFSheet sheet, Integer numeroFila){
		
		//Fila del excel.
		Row row = sheet.createRow(numeroFila);
		
		//Id del Artículo.
		Cell idCell = row.createCell(0);
		idCell.setCellValue("Id");
		
		//Imagen.
		Cell rutaImagenCell = row.createCell(1);
		rutaImagenCell.setCellValue("Imagen");
		
		//Nombre del Artículo.
		Cell nombreCell = row.createCell(2);
		nombreCell.setCellValue("Nombre");
		
		//Número de Artículos disponibles.
		Cell cantidadCell = row.createCell(3);
		cantidadCell.setCellValue("Cantidad");
				
		//Número de Artículos disponibles.
		Cell activoCell = row.createCell(4);
		activoCell.setCellValue("Activo");
				
		return row;
	}
	/**
	 * Crea una fila del excel.
	 * @param sheet Hoja de excel.
	 * @param numeroFila Número de la fila.
	 * @param id Id del Artículo.
	 * @param rutaImagen Ruta de la Imagen.
	 * @param nombre Nombre del Artículo.
	 * @param cantidad Cantidad.
	 * @param activo Indica si el Artículo esta activo.
	 * @return Fila de excel.
	 */
	private static Row crearFila(HSSFWorkbook workbook, HSSFSheet sheet, Integer numeroFila, Long id, String rutaImagen, String nombre, Integer cantidad, Boolean activo){
		
		//Fila del excel.
		Row row = sheet.createRow(numeroFila);
		
		//Id del Artículo.
		Cell idCell = row.createCell(0);
		idCell.setCellType(Cell.CELL_TYPE_NUMERIC);
		idCell.setCellValue(id);
		
		//Imagen.
		Cell rutaImagenCell = row.createCell(1);
		rutaImagenCell.setCellValue("");
		rutaImagenCell.setCellType(Cell.CELL_TYPE_BLANK);
		try{
		
			addImagen(workbook, sheet, rutaImagen, numeroFila, 1);
		
		}catch(IOException e){
			
			//No se ha podido cargar la imagen.
			logger.error("Error cargando imagen", e);
		}
		
		//Nombre del Artículo.
		Cell nombreCell = row.createCell(2);
		nombreCell.setCellType(Cell.CELL_TYPE_STRING);
		nombreCell.setCellValue(nombre);
		
		//Número de Artículos disponibles.
		Cell cantidadCell = row.createCell(3);
		cantidadCell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cantidadCell.setCellValue(cantidad);
				
		//Indica si el Artículo está activo.
		Cell activoCell = row.createCell(4);
		activoCell.setCellType(Cell.CELL_TYPE_BOOLEAN);
		activoCell.setCellValue(activo);
		
		row.setHeight((short)1600);
		
		return row;
	}
	
	/**
	 * Añade una imagen a una fila y columna en concreto.
	 * @param workbook HSSFWorkbook.
	 * @param sheet HSSFSheet.
	 * @param rutaImagen Ruta de la imagen.
	 * @param numeroFila Número de fila.
	 * @param numeroColumna Número de columna.
	 * @throws IOException
	 */
	private static void addImagen(HSSFWorkbook workbook, HSSFSheet sheet, String rutaImagen, Integer numeroFila, Integer numeroColumna) throws IOException{
		
		InputStream inputStream = new FileInputStream(rutaImagen);
		
		byte[] bytes = IOUtils.toByteArray(inputStream);
		
		int indice = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		
		inputStream.close();
	 
		CreationHelper creationHelper = workbook.getCreationHelper();
	 
		Drawing drawing = sheet.createDrawingPatriarch();
	 
		ClientAnchor clientAnchor = creationHelper.createClientAnchor();
		clientAnchor.setDx1(0);
        clientAnchor.setDy1(0);
		clientAnchor.setCol1(numeroColumna);
		clientAnchor.setRow1(numeroFila);
		//clientAnchor.setAnchorType(ClientAnchor.DONT_MOVE_AND_RESIZE);
		
		drawing.createPicture(clientAnchor, indice);
		
	}
}
