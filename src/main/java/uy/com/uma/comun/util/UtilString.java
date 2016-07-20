package uy.com.uma.comun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/**
 * Encapsulo funciones de utilería aplicables a cadenas de caracteres
 * 
 * @author Santiago
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class UtilString {

	/** Caracteres especiales para los .xml de especificación de datos */
    public static final String [] CARACTERES_ESPECIALES	= {"&", "<", ">", "Á", "É", "Í", "Ñ", "Ó", "Ú", "á", "é", "í", "ñ", "ó", "ú", "¿"};
    
    public static final String [] LETRAS_ESPECIALES	= {"Á", "É", "Í", "Ñ", "Ó", "Ú", "á", "é", "í", "ñ", "ó", "ú", "¿"};
    
    public static final String [] CARACTERES_ESPECIALES_HEXA	= {
    	"&amp;", "&lt;", "&gt;", 
    	"\\\\u00C1", "\\\\u00C9", "\\\\u00CD", "\\\\u00D1", "\\\\u00D3", "\\\\u00DA",	
    	"\\\\u00E1", "\\\\u00E9", "\\\\u00ED", "\\\\u00F1", "\\\\u00F3", "\\\\u00FA", "\\\\u00BF"};
    
    public static final String [] LETRAS_ESPECIALES_HEXA	= { 
        	"\\\\u00C1", "\\\\u00C9", "\\\\u00CD", "\\\\u00D1", "\\\\u00D3", "\\\\u00DA",	
        	"\\\\u00E1", "\\\\u00E9", "\\\\u00ED", "\\\\u00F1", "\\\\u00F3", "\\\\u00FA", "\\\\u00BF"};
	
    
    
	
	/**
	 * Retorna la cantidad de veces que aparece un caracter en un String
	 */
	public static int cantidadVeces (char c, String en) {
		
		int cant = 0;
		int com = en.indexOf (c);
		
		while (com != -1) {
			cant++;
			com = en.indexOf (c, com+1);
		}
		
		return cant;
	}

	
	
	/**
	 * Retorna los primeros N caracteres a la izquierda del String
	 */
	public static String left (String ori, int n) {
		return ori.substring (0, Math.min (n, ori.length()));
	}



	/**
	 * Retorna un arreglo de Strings con los datos de la coleccion
	 */	
	public static String [] col2ArrayOfString (Collection c) {
	    
	    if (c == null)
	        return new String [0];
	    else {
	        String [] ret = new String [c.size()];
	        int i = 0;
	        
	        for (Iterator it = c.iterator(); it.hasNext(); i++) {
	        	Object o = it.next(); 
	            ret[i] = (o == null) ? "" : o.toString();
	        }
	        
	        return ret;
	    }
	}



	/**
	 * Concatena todos los valores de la coleccion, poniendo entre medio de cada
	 * valor el separador
	 */
	public static String concatenar (Collection c, String sep) {
		
		if (c == null)
			return null;
		else {
			String ret = "";
			if (sep == null) sep = "";
		
			for (Object o : c)
				ret += o.toString() + sep;
			
			return quitarUltimosCaracteres (ret, sep.length());
		}    		
	}



	/**
	 * Retorna TRUE si el primer string es igual a alguno de los del 
	 * arreglo pasado como segundo parámetro
	 */
	public static boolean esUnoDe (String ori, String [] dest) {
	    for (String elem : dest)
	        if (ori.equals (elem))
	            return true;
	    return false;
	}



	/**
	 * Retorna la primera posicion del string en donde se encuentran alguno
	 * de los caracteres pasados como parámetros (la posicion mas al principio)
	 */
	public static int firstIndexOf (String s, char [] chrs) {
		return firstIndexOf (s, chrs, 0);
	}



	public static int firstIndexOf (String s, char [] chrs, int aPartirDe) {
		
		 if ((s == null) || (chrs == null))
			 return -1;
		 else if (s.equals(""))
			 return 0;
		 else {
			 int min = s.length();
			 
			 for (char elem : chrs) {
				 int pos = s.indexOf (elem, aPartirDe);
				 if (pos != -1) min = Math.min (min, pos);
			 }
			 
			 return min;
		 }
	}



	/**
	 * Retorna la primera posicion del string en donde se encuentran alguno
	 * de las cadenas de caracteres pasados como parámetros 
	 * (la posicion mas al principio)
	 */
	public static int firstIndexOf (String s, String [] strs) {
		return firstIndexOf (s, strs, 0);
	}



	public static int firstIndexOf (String s, String [] strs, int aPartirDe) {
		
		 if ((s == null) || (strs == null))
			 return -1;
		 else if (s.equals(""))
			 return 0;
		 else {
			 int min = s.length();
			 
			 for (String elem : strs) {
				 int pos = s.indexOf (elem, aPartirDe);
				 if (pos != -1) min = Math.min (min, pos);
			 }
			 
			 return min;
		 }
	}



	/**
	 * Retorna los distintos caracteres del string en cuestion
	 */	
	public static char [] getDistintosChars (String valor) {
	    
	    List<Character> l = new ArrayList<Character>();        
	    char [] arr = valor.toCharArray(); 
	    
	    for (int i = 0; i < valor.length(); i++)
	        if (! l.contains (new Character (arr[i])))
	            l.add (new Character (arr[i]));                    
	    
	    char [] ret = new char [l.size()];        
	    int i = 0;
	    
	    for (Iterator it = l.iterator(); it.hasNext(); i++)
	        ret[i] = ((Character) it.next()).charValue();
	    
	    return ret;
	}



	/**
	 * Retorna el String dato repetido n veces
	 */
	public static String getNVeces (String dato, int veces) {
		
		if (veces == 0)
			return "";
		else if ((dato == null) || (veces <= 1))
			return dato;
		else {
			String ret = "";    		
			for (int i = 1; i <= veces; i++) ret += dato;    		
			return ret;
		}    	
	}



	/**
	 * Retorna el texto de un archivo
	 */
	public static String getTexto (String archivo) throws IOException {
		return getTexto (new File (archivo));			
	}

	public static String getTexto (String archivo, String ret) throws IOException {
		return getTexto (new File (archivo), ret);			
	}

	public static String getTexto (File archivo) throws IOException {
		return getTexto (archivo, System.getProperty ("line.separator"));
	}

	public static String getTexto (File archivo, String ret) throws IOException {
		return getTexto (new FileReader (archivo), ret, true);
	}
	
	public static String getTexto (Reader r) throws IOException {
		return getTexto (r, System.getProperty ("line.separator"), true);	
	}
	
	public static String getTexto (Reader r, boolean closeReader) throws IOException {
		return getTexto (r, System.getProperty ("line.separator"), closeReader);	
	}
	
	public static String getTexto (Reader r, String ret, boolean closeReader) throws IOException {
		try {	    	
			BufferedReader br = new BufferedReader (r);
			StringBuffer sb = new StringBuffer();
			
			while (br.ready())
				sb.append (br.readLine() + ret);
			
			return sb.toString();
			
		} finally {
			if (closeReader) 
				UtilIO.closeReader (r);
		}
	}



	/**
	 * Retorna la última posicion del string en donde se encuentran alguno
	 * de los caracteres pasados como parámetros (la posicion mas al final)
	 */
	public static int lastIndexOf (String s, char [] chrs) {
		return lastIndexOf (s, chrs, (s == null) ? 0 : s.length()-1);
	}



	public static int lastIndexOf (String s, char [] chrs, int aPartirDe) {		
		 if ((s == null) || (chrs == null))
			 return -1;
		 else if (s.equals(""))
			 return 0;
		 else {
			 int max = -1;
			 
			 for (char c : chrs)
				 max = Math.max (max, s.lastIndexOf (c, aPartirDe));
			 
			 return max;
		 }
	}



	/**
	 * Quita los blancos de un string cubriendose de nulos
	 */
	public static String quitarBlancos (String dato) {
	    return (dato == null) ? null : dato.trim();
	}



	/**
	 * Quita las comillas simples o dobles de un string cubriendose de nulos
	 */
	public static String quitarComillas (String dato) {        
	    if (dato == null)
	        return null;
	    else {            
	        dato = dato.trim();
	        dato = quitarDelimitador (dato, "\"");            
	        dato = quitarDelimitador (dato, "'");
	        dato = quitarDelimitador (dato, "`");
	        return dato;
	    }
	}



	/**
	 * Quita el delimitador del principio y el final del dato
	 */
	public static String quitarDelimitador (String dato, String delimitador) {
		if (dato == null)
			return dato;
		
		if ((dato.startsWith (delimitador)) && (dato.endsWith (delimitador))) { 
			dato = dato.substring(1);
			dato = UtilString.left (dato, dato.length()-1);
		}
		return dato;
	}



	/**
	 * Quita los ultimos Cant caracteres del string
	 */
	public static String quitarUltimosCaracteres (String dato, int cant) {
	    if (dato == null)
	        return null;
	    else if (cant >= dato.length())
	        return "";
	    else 
	        return dato.substring (0, dato.length()-cant);
	}



	/**
	 * Serializo una matriz de objetos
	 */
	public static String toString (Object [][] p) {
		
		if ((p == null) || (p.length == 0) || (p[0] == null))
			return "";
		else {
			String ret = "";
			
			for (int i = 0; i < p.length; i++) {
				ret += "[";
				
				for (int j = 0; j < p[0].length; j++)
					ret += p[i][j] + ",";
				
				if (p[i].length > 0)
					ret = UtilString.quitarUltimosCaracteres (ret, 1) + "], ";				
			}
			
			return UtilString.quitarUltimosCaracteres (ret, 2);
		}
	}
	
	
	
	/**
	 * Retorna un Map con los strings como claves y elementos
	 */
	public static Map array2MapOfStrings (String [] datos) {
		Map ret = new HashMap();
		
		for (String elem : datos)
			if (elem != null)
				ret.put (elem, elem);
		
		return ret;
	}



	/**
	 * Realiza la resta de conjuntos a-b
	 */
	public static String [] menos (String [] a, String [] b) {
		if ((b == null) || (b.length == 0))
			return a;
		else if ((a == null) || (a.length == 0))
			return new String [0];
		else {
			Map aux = new HashMap();
			
			for (String elem : a)
				aux.put (elem, elem);
			
			for (String elem : b)
				if (aux.containsKey (elem))
					aux.remove (elem);			
			
			return col2ArrayOfString (aux.values());
		}
	}
	
	
	
	/**
	 * Retorna de un arreglo de objetos un arreglo de Strings
	 */
	public static String [] getArrayOfString (Object [] datos) {
		if (datos == null)
			return null;
		
		String [] ret = new String [datos.length];
		
		for (int i = 0; i < ret.length; i++)
			ret[i] = datos[i].toString();
		
		return ret;
	}	
	
	
	
	/**
	 * Realiza el union de dos arreglos de String
	 */
	public static String [] union (String [] a, String [] b) {
		if ((a == null) || (a.length == 0))
			return b;
		
		if ((b == null) || (b.length == 0))
			return a;
		
		String [] ret = new String [a.length + b.length];
		
		for (int i = 0; i < a.length; i++)
			ret[i] = a[i];
		
		for (int i = 0; i < b.length; i++)
			ret[i+a.length] = b[i];
		
		return ret;
	}	
		
	
	
	/**
	 * Si es nulo retorna la cadena vacia
	 */
	public static String quitarNulo (String dato) {
		return (dato == null) ? "" : dato;
	}
	
	
	
	/**
	 * Reemplaza cada ocurrencia de caracteres especiales por su correspondencia
	 */
	public static String reemplazarCaracteresEspeciales (String s) {
		if (s == null)
			return s;
		
		for (int i = 0; i < CARACTERES_ESPECIALES.length; i++)
			s = s.replaceAll (CARACTERES_ESPECIALES[i],	CARACTERES_ESPECIALES_HEXA[i]);
		
		return s;
	}
	
	
	
	/**
	 * Reemplaza cada ocurrencia de las letras especiales por su correspondiente código hexadecimal
	 */
	public static String reemplazarLetrasEspeciales (String s) {
		if (s == null)
			return s;
		
		for (int i = 0; i < LETRAS_ESPECIALES.length; i++)
			s = s.replaceAll (LETRAS_ESPECIALES[i],	LETRAS_ESPECIALES_HEXA[i]);
		
		return s;
	}
	
	
	
	/**
	 * Reemplaza cada ocurrencia de caracteres especiales por su correspondencia
	 */
	public static String quitarCaracteresEspeciales (String s) {
		if (s == null)
			return s;
		
		for (int i = 0; i < CARACTERES_ESPECIALES.length; i++)
			s = s.replaceAll (CARACTERES_ESPECIALES_HEXA[i], CARACTERES_ESPECIALES[i]);
		
		return s;
	}
	
	
	
	/**
	 * Reemplaza cada ocurrencia de las letras especiales escapeadas en su código hexadecimal 
	 * por su correspondencia en letras especiales como á, é, ñ, Ó, etc
	 */
	public static String quitarLetrasEspeciales (String s) {
		if (s == null)
			return s;
		
		for (int i = 0; i < LETRAS_ESPECIALES.length; i++)
			s = s.replaceAll (LETRAS_ESPECIALES_HEXA[i],	LETRAS_ESPECIALES[i]);
		
		return s;
	}	
}
