package br.com.caelum.cdc.shared;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

public class RequestProcessor {

	public static <T extends Object> T process(HttpServletRequest request, Class<T> dtoClazz) {
		
		Object object = getClazzInstance(dtoClazz);

		//DTO
		Map<String, Object> dtoParameters = new HashMap<String, Object>();

		//GET THE FIELDS THAT EXISTS IN YOUR CLASS
		List<Field> fields = Arrays.asList(dtoClazz.getDeclaredFields());
		fields.stream().forEach(field -> {
			//GET THE PARAMETERS FROM REQUEST
			request.getParameterMap().forEach((key, value) -> {
				//VERIFY IF THE NAME IS EQUAL TO THE REQUEST KEY
				if (field.getName().equalsIgnoreCase(key)) {
					//VERIFY THE TYPE OF THE PARAMETER
					if (field.getType().isAssignableFrom(String.class)) {
						dtoParameters.put(field.getName(), value[0]);
					} else if (field.getType().isAssignableFrom(double.class)) {
						dtoParameters.put(field.getName(), value[0]);
					}
				}
			});
		});
		
		//GET THE SETTER METHODS
		Set<Method> setters = Arrays.asList(dtoClazz.getMethods()).stream().filter(m -> m.getName().startsWith("set"))
				.collect(Collectors.toSet());		

		//FOR EACH SETTER METHOD 
		setters.forEach(setMethod -> {
			//FOR EACH PARAMETER THAT CAME FROM URL
			dtoParameters.entrySet().stream().forEach(param -> {
				String methodName = getSetterNameFromParameterKey(param.getKey());
				
				//Verify if the set method that exists in your class is the same as your parameter
				if (setMethod.getName().equals(methodName)) {
					try {

						Method method = dtoClazz.getDeclaredMethod(methodName, String.class);
						method.setAccessible(true);
						method.invoke(object, param.getValue());

					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e);
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e);
					}
				}
			});
		});

		return dtoClazz.cast(object);
	}
	
	private static Object getClazzInstance(Class<?> clazz) {
		try {
			return clazz.getDeclaredConstructor().newInstance();		
		} catch (InstantiationException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {			
			throw new RuntimeException(e);
		}
	}
	
	private static String getSetterNameFromParameterKey(String param) {
		return "set" + param.substring(0, 1).toUpperCase() 
				+ param.substring(1).toLowerCase();
	}
	
//	private Map<String, Object> getParametersFromRequest() {
//		
//	}
}
