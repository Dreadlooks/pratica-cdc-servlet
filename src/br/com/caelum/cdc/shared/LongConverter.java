package br.com.caelum.cdc.shared;

import com.mysql.cj.util.StringUtils;

public class LongConverter implements Converter<Long> {

	@Override
	public Long convert(String value) {
		if (StringUtils.isNullOrEmpty(value)) {
			return null;
		}

		return Long.parseLong(value);
	}

}
