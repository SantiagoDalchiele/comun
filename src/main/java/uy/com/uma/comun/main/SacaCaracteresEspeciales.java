package uy.com.uma.comun.main;

import java.io.FileWriter;
import java.io.IOException;

import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.comun.util.UtilIO;
import uy.com.uma.comun.util.UtilString;

/**
 * Toma un archivo de texto y graba otro igual, sustituyendo los caracteres especiales, como letras con tíldes,
 * por su correspondiente codigo hexadecimal
 *
 * @author Santiago Dalchiele
 * @see uy.com.uma.comun.util.UtilString
 */
public class SacaCaracteresEspeciales {

	
	/**
	 * Programa principal
	 */
	public static void main(String[] args) {
		if ((args == null) || (args.length < 2) || (UtilFormato.esNulo(args[0])) || (UtilFormato.esNulo(args[1])))
			usage();
		else {
			final String archIn = args[0];
			final String archOut = args[1];
			FileWriter fw = null;
			
			try {
				fw = new FileWriter(archOut);
				System.out.println("Leyendo contenido de [" + archIn + "]");
				final String contenido = UtilString.getTexto(archIn);
				final String contenidoMod = UtilString.reemplazarLetrasEspeciales(contenido);
				System.out.println("Escribiendo resultado en [" + archOut + "]");
				fw.write(contenidoMod);
			} catch (IOException e) {
				System.out.println("Error al procesar el archivo");
				e.printStackTrace();
			} finally {
				UtilIO.closeWriter(fw);
			}
		}
	}
	
	
	
	/**
	 * Muestra el uso de la aplicación
	 */
	private static void usage() {
		System.out.println("Uso: SacaCaracteresEspeciales [archivo_origen] [archivo_destino]");
	}
}
