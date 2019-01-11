package com.sb.registrationweb.converters;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class InstantToStringConverter implements Converter<LocalDateTime, Instant> {

	private static final DateTimeFormatter formatter =
		    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
		                     .withZone( ZoneId.systemDefault() );

	@Override
	public Result<Instant> convertToModel(LocalDateTime value, ValueContext context) {
		return Result.ok(value.atZone(ZoneOffset.systemDefault()).toInstant());
	}

	@Override
	public LocalDateTime convertToPresentation(Instant value, ValueContext context) {
		if(value == null)  return null;
		return LocalDateTime.ofInstant(value, ZoneOffset.systemDefault());
	}


}
