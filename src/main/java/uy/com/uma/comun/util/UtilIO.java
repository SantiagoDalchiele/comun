package uy.com.uma.comun.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Funciones utiles de IO
 *
 * Creado Aug 9, 2007
 * @author Santiago Dalchiele
 */
public abstract class UtilIO {

	
	/**
	 * Levanta las properties de un recurso
	 */
	@SuppressWarnings("rawtypes")
	public static void loadRecurso (String arch, Properties p) {
		ResourceBundle rb = ResourceBundle.getBundle (arch);
		
		for (Enumeration e = rb.getKeys(); e.hasMoreElements();) {
			String key = e.nextElement().toString();
			p.put (key, rb.getObject(key));
		}
	}	
	
	
	
	/**
	 * Levanta las properties, primero intenta levantarlo por recurso sino lo levanta del file-system
	 * (por compatibilidad con Java Web Start)
	 */
	public static Properties getProperties (String arch) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		
		try {
			UtilIO.loadRecurso (arch, p);
		} catch (Exception e) {
			p.load (new FileInputStream (arch));
		}
		
		return p;
	}



	/**
	 * Cierra un Reader sin tirar excepcion
	 */
	public static void closeReader (Reader r) {
		try {
			if (r != null)
				r.close();
		} catch (IOException e) {			
		}
	}



	/**
	 * Cierra un Writer sin tirar excepcion
	 */
	public static void closeWriter (Writer w) {
		try {
			if (w != null)
				w.close();
		} catch (IOException e) {			
		}
	}
	
	
	
	/**
	 * Cierra un InputStream sin tirar excepcion
	 */
	public static void closeInputStream (InputStream is) {
		try {
			if (is != null)
				is.close();
		} catch (IOException e) {			
		}
	}
	
	
	
	/**
	 * Retorna la extensión del archivo
	 */
	public static String extFile (final String fileName) {
		if (UtilFormato.esNulo(fileName))
			return "";
		else
			if (fileName.indexOf(".") == -1)
				return "";
			else
				return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
