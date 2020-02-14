package br.com.caelum.cdc.shared;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

public class RequestProcessor {

	public static <T extends Object> T process(HttpServletRequest request, Class<T> dtoClazz) {

		Object object = getClazzInstance(dtoClazz);

		// GET THE FIELDS THAT EXISTS IN YOUR CLASS
		List<Field> fields = Arrays.asList(dtoClazz.getDeclaredFields());
		fields.stream().forEach(field -> {
			// GET THE PARAMETERS FROM REQUEST
			request.getParameterMap().forEach((key, value) -> {
				// VERIFY IF THE NAME IS EQUAL TO THE REQUEST KEY
				if (field.getName().equalsIgnoreCase(key)) {
					Converter converter;
					// VERIFY THE TYPE OF THE PARAMETER
					if (field.getType().isAssignableFrom(String.class)) {
						 converter = new StringConverter();
						 populateClass(object, dtoClazz, key, value[0], converter);
					} else if (field.getType().isAssignableFrom(double.class)) {
						 converter = new PrimitiveDoubleToDouble();
						 populateClass(object, dtoClazz, key, value[0], converter);
					}
				}
			});
		});
		
		return dtoClazz.cast(object);
	}
	
	private static void populateClass(Object object, Class<?> dtoClazz, String key, String stringValue, Converter converter) {
		Set<Method> setters = Arrays.asList(dtoClazz.getMethods()).stream().filter(m -> m.getName().startsWith("set"))
				.collect(Collectors.toSet());
		
		String methodName = getSetterNameFromParameterKey(key);
		
		Optional<Method> findFirst = setters.stream()
				.filter(set -> set.getName().equals(methodName)).findFirst();
		
		if (findFirst.isPresent()) {
			Method method;
			try {
				method = dtoClazz.getDeclaredMethod(methodName, converter.getType());
				method.setAccessible(true);
				method.invoke(object, converter.convert(stringValue));
			} catch (NoSuchMethodException | SecurityException |
					IllegalAccessException | IllegalArgumentException | 
					InvocationTargetException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}

	private static Object getClazzInstance(Class<?> clazz) {
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getSetterNameFromParameterKey(String param) {
		return "set" + param.substring(0, 1).toUpperCase() + param.substring(1).toLowerCase();
	}
}
