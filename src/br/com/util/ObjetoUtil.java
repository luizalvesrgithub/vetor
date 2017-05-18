package br.com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;

public class ObjetoUtil {

	static final Base64 base64 = new Base64();
	

	public static Map<String, Object> getFieldNamesAndValues(final Object obj, boolean publicOnly)
			throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> c1 = obj.getClass();
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = c1.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			if (publicOnly) {
				if (Modifier.isPublic(fields[i].getModifiers())) {
					Object value = fields[i].get(obj);
					map.put(name, value);
				}
			} else {
				fields[i].setAccessible(true);
				Object value = fields[i].get(obj);
				map.put(name, value);
			}
		}
		return map;
	}
	
	public static Map<String, Object> getValorObjeto(final Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> c1 = obj.getClass();
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = c1.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			if (name.equals("value")) {
				fields[i].setAccessible(true);
				Object value = fields[i].get(obj);
				map.put(name, value);
			}
		}
		return map;
	}

	
	public static String serializeObjectToString(Object object) throws IOException {
		
        try (
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(arrayOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream);
          ) {
            		objectOutputStream.writeObject(object);
            		objectOutputStream.flush();
            		return new String(base64.encode(arrayOutputStream.toByteArray()));
        		}	
		}
	
}

