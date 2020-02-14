package br.com.caelum.cdc.shared;

public class StringConverter implements Converter<String> {

	@Override
	public Class<?> getType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public String convert(String value) {
		// TODO Auto-generated method stub
		return value;
	}

}
