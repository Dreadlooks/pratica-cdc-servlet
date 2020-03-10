package br.com.caelum.cdc.shared;

import com.mysql.cj.util.StringUtils;

public class PrimitiveIntConverter implements Converter<Integer> {

	@Override
	public Integer convert(String value) {
		// TODO Auto-generated method stub
		if (StringUtils.isNullOrEmpty(value)) {
			return 0;
		}
		return Integer.parseInt(value);
	}

}
