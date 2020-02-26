package br.com.caelum.cdc.shared;

public interface Converter<T> {

	T convert(String value);

}