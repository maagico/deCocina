package es.cgarcia.decocina.admin.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;

/**
 * Utilidades para trabajar con las Imágenes.
 * 
 * @author Miguel Ángel Álvarez García
 */
public final class ImagenUtils { 
	
	/**
	 * Constructor.
	 */
	private ImagenUtils(){
	}
	
	/**
	 * Copia una Imagen en la ruta indicada.
	 * 
	 * @param MultipartFile Contiene la Imagen a copiar.
	 * @param ruta Ruta donde se copiará la Imagen.
	 */
	public static void copiarImagen(MultipartFile mfImagen, String ruta) 
	{
		try{
			
			byte[] bImagen = mfImagen.getBytes();
			
			FileOutputStream fos = new FileOutputStream(new File(ruta));
			fos.write(bImagen);
			fos.flush();
			fos.close();
			
		}catch(IOException e){
			
			throw new DeCocinaAdminRuntimeException("Se ha producido un error grabando la Imagen", e);
		}
	}
	
	/**
	 * Copia una Imagen en la ruta de origen al destino.
	 * 
	 * @param origen File que contiene la Imagen a copiar.
	 * @param destino File donde se copiará la Imagen.
	 */
	public static void copiarImagen(File origen, File destino) 
	{
		try{
			
			FileInputStream fis = new FileInputStream(origen); 
			FileOutputStream fos = new FileOutputStream(destino);
			
			FileChannel inChannel = fis.getChannel(); 
			FileChannel outChannel = fos.getChannel(); 
			
			inChannel.transferTo(0, inChannel.size(), outChannel); 
			
			fis.close(); 
			fos.close();
			
		}catch (IOException e) {
			
			throw new DeCocinaAdminRuntimeException("Se ha producido un error grabando la Imagen", e);
		}
	}
	
	/**
	 * Graba una Imagen con distinto tamaño al original.
	 *
	 * @param Imagen Imagen a copiar y cambiar tamaño.
	 * @param ancho Ancho de la Imagen.
	 * @param alto Alto de la Imagen.
	 * @param rutaGrabacion Ruta donde se grabará la Imagen.
	 */
	public static void cambiaTamYCopiaImagen(MultipartFile mfImagen, Integer ancho, Integer alto, String rutaGrabacion)
	{
		try{
			
			byte[] bImagen = mfImagen.getBytes();
			
			Image imgagen = ImageIO.read(new ByteArrayInputStream(bImagen));
			Image ImagenEscalada = imgagen.getScaledInstance(ancho, alto,Image.SCALE_DEFAULT );
			
			BufferedImage bf = new BufferedImage(ancho, alto,BufferedImage.TYPE_INT_RGB);
			bf.createGraphics().drawImage(ImagenEscalada, 0, 0, null);
			
			ImageIO.write(bf, "jpg", new File(rutaGrabacion));
			
		}catch(IOException e){
			
			throw new DeCocinaAdminRuntimeException("Se ha producido un error grabando la Imagen", e);
		}
	}
	
	/**
	 * Borra la Imagen en la ruta indicada.
	 * @param ruta Ruta y nombre de la Imagen a borrar.
	 */
	public static void borrarImagen(String ruta)
	{
		File f = new File(ruta);
		f.delete();
	}
	
	/**
	 * Cambia el nombre de la Imagen.
	 * @param rutaAntigua Ruta y nombre antiguo de la Imagen. 
	 * @param rutaNueva Ruta y nombre nuevo de la Imagen.
	 */
	public static void cambiaNombreImagen(String rutaAntigua, String rutaNueva) 
	{
        File f = new File(rutaAntigua);
        f.renameTo(new File(rutaNueva));
	}
	
	/**
	 * Devuelve la extension del fichero.
	 * 
	 * @param fichero Fichero del que se recuperea la extensión.
	 * @return Extension del fichero.
	 */
	public static String getExtension(String fichero){
		
		int lastIndexOf = fichero.lastIndexOf('.');
		String extension = fichero.substring(lastIndexOf, fichero.length());
		
		return extension;
	}
}
