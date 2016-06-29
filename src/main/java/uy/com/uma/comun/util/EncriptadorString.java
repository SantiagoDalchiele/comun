package uy.com.uma.comun.util;

import java.security.KeyException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Encripta y desencripta cadenas de caracteres
 *
 * @author Santiago Dalchiele
 */
public class EncriptadorString {

	/** Metodo de encriptación / desencriptación */
	public static final String METODO_DES = "DES";
	public static final String METODO_DESEDE = "DESede";
	
	
	/** Datos por defecto */
	private static final String METODO_X_DEFECTO = METODO_DES;
	private static final String CLAVE_X_DEFECTO = "u3b6mS3jYR0=6hJVwyR46F0aZs/n4coLNw=";
	
	/** Formato */
	private static final String FORMATO = "UTF8";
	
	
	/** Variables de clase */
	private Cipher encriptador;
	private Cipher desencriptador;
	
	
	
	/**
	 * Punto de entrada para ejecutar desde línea de comandos (encripta una cadena de caracteres)
	 */
	public static void main(String[] args) {
		if ((args == null) || (args.length == 0) || (args[0] == null))
			usage();
		else
			try {
				System.out.println(encripta(args[0]));
			} catch (KeyException e) {
				e.printStackTrace();
			}
	}
	
	
	
	/**
	 * Muestra el uso de la aplicación
	 */
	private static void usage() {
		System.out.println("Usage:");
		System.out.println("EncriptadorString [cadena]");
	}

	
	
	/**
	 * Constructores
	 */
	public EncriptadorString() throws KeyException {
		this (METODO_X_DEFECTO, CLAVE_X_DEFECTO);
	}
	
	public EncriptadorString (String metodo) throws KeyException {
		this (metodo, CLAVE_X_DEFECTO); 
	}
	
	public EncriptadorString (SecretKey clave) throws KeyException {
		this (METODO_X_DEFECTO, clave);
	}
	
	public EncriptadorString (String metodo, String clave) throws KeyException {
		this (metodo, getSecretKey(metodo, clave));			
	}
	
	public EncriptadorString (String metodo, SecretKey clave) throws KeyException {
		try {
			this.encriptador = Cipher.getInstance (metodo);
			this.encriptador.init (Cipher.ENCRYPT_MODE, clave);
			this.desencriptador = Cipher.getInstance (metodo);
			this.desencriptador.init (Cipher.DECRYPT_MODE, clave);
		} catch (Exception e) {
			throw new KeyException ("Error al encriptar/desencriptar clave", e);
		}			
	}
	
	
	
	/**
	 * Genera una clave secreta a partir de un metodo y una clave en formato String
	 */
	public static SecretKey getSecretKey (String metodo, String clave) throws KeyException {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance (metodo);
			byte [] bc = clave.getBytes(FORMATO);
			KeySpec ks = new DESKeySpec(bc);
			
			if (metodo.equals(METODO_DESEDE))
				ks = new DESedeKeySpec (bc);
			
			return skf.generateSecret(ks);
		} catch (Exception e) {
			throw new KeyException ("Error al encriptar/desencriptar clave", e);
		}
	}
	
	
	
	/**
	 * Retorna el String s encriptado 
	 */
	public static String encripta (String s) throws KeyException {
		return new EncriptadorString().encriptar(s);
	}
	
	
	
	/**
	 * Retorna el String s desencriptado 
	 */
	public static String desencripta (String s) throws KeyException {
		return new EncriptadorString().desencriptar(s);
	}
	
	
	
	/**
	 * Retorna el String s encriptado 
	 */
	public String encriptar (String s) throws KeyException {
		try {
			byte [] enc = this.encriptador.doFinal (s.getBytes (FORMATO));
			return new BASE64Encoder().encode (enc);
		} catch (Exception e) {
			throw new KeyException ("Error al encriptar/desencriptar clave", e);
		}
	}

	
	
	/**
	 * Retorna el String s desencriptado 
	 */	
	public String desencriptar (String s) throws KeyException {
		try {
			byte [] enc = new BASE64Decoder().decodeBuffer (s);
			return new String (this.desencriptador.doFinal (enc), FORMATO);
		} catch (Exception e) {
			throw new KeyException ("Error al encriptar/desencriptar clave", e);
		}
	}
}
