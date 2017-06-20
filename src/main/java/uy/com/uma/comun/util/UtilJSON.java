package uy.com.uma.comun.util;

import java.io.StringReader;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;



/**
 * Clase de utilería para el manejo de JSON
 *
 * @author Santiago Dalchiele
 */
public abstract class UtilJSON {
	

	/**
	 * Retorna un objeto JSON con una única propiedad y su correspondiente valor
	 */
	public static JsonObject getJSONObject (String prop, String valor) {
		return Json.createObjectBuilder().add(prop, valor).build();
	}
	
	
	
	/**
	 * Retorna un objeto JSON con varias propiedades y sus respectivos valores en la estructura de propiedades
	 */
	public static JsonObject getJSONObject (Map<String, Object> propiedades) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		for (Map.Entry<String, Object> entry : propiedades.entrySet()) {
			String key = entry.getKey();
			Object obj = entry.getValue();			
			
			if (obj instanceof String)
				job.add(key, (String) obj);
			else if (obj instanceof Short)
				job.add(key, (Short) obj);
			else if (obj instanceof Integer)
				job.add(key, (Integer) obj);
			else if (obj instanceof JsonObject)
				job.add(key, (JsonObject) obj);
			else if (obj instanceof Object[])
				job.add(key, getJSONArray((Object []) obj));
		}
		
		return job.build();		
	}
	
	
	
	/**
	 * Retorna un objeto JSON con una única propiedad, el arreglo de objetos
	 */
	public static JsonObject getJSONObject (String prop, Object [] values) {
		JsonObjectBuilder job = Json.createObjectBuilder();		
		job.add(prop, getJSONArray(values));
		return job.build();
	}
	
	
	
	/**
	 * Retorna un JsonArray a partir de un arreglo de objetos
	 */
	public static JsonArray getJSONArray (Object [] values) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Object val : values) {
			if (val instanceof String)
				jab.add((String) val);
			else if (val instanceof Short)
				jab.add((Short) val);
			else if (val instanceof Integer)
				jab.add((Integer) val);
			else if (val instanceof JsonObject)
				jab.add((JsonObject) val);
		}
		
		return jab.build();
	}
	
	
	
	/**
	 * Dado los objetos JSON en un string de la forma {...},{...}, ...,{...} retorna los JsonObject en un arreglo
	 * de objetos
	 */
	public static Object [] getJSONObjects (String valores) {
		if (UtilFormato.esNulo(valores))
			return new Object[0];
		else {
			JsonReader reader = Json.createReader(new StringReader("{\"valores\":[" + valores + "]}"));
			Object [] ret = reader.readObject().getJsonArray("valores").toArray();
			reader.close();
			return ret;
		}		
	}
}
