package com.techflow.ws.sys.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public abstract class CommonService {

	@Autowired
	private MessageSource messageSource;

	protected String getMessage(String code) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, null, currentLocale);
	}

	protected String getMessage(String code, Object[] args) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, args, currentLocale);
	}
	

}
