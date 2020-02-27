package br.com.caelum.cdc.shared;

public class PrimitiveDoubleToDouble implements Converter<Double> {

	@Override
	public Double convert(String value) {
		// TODO Auto-generated method stub
		return Double.parseDouble(value);
	}
	
}
