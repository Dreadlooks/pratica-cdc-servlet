package br.com.caelum.cdc.shared;

import java.math.BigDecimal;

import com.mysql.cj.util.StringUtils;

public class BigDecimalConverter implements Converter<BigDecimal> {

	@Override
	public BigDecimal convert(String value) {
		if (StringUtils.isNullOrEmpty(value)) {
			return null;
		}

		return new BigDecimal(value);
	}
}
