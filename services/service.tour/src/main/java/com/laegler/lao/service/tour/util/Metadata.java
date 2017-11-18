package com.laegler.lao.service.tour.util;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import lombok.Getter;

@RequestScope
@Component
public class Metadata {

	private static final Logger LOG = LoggerFactory.getLogger(Metadata.class);

	@Value("${google.apikey}")
	@Getter
	private String apiKey;

	private Locale locale;

	public Locale getLocale() {
		String headerValue = getHeaderAttrValue("Accept-Header");
		if (headerValue != null && !headerValue.isEmpty()) {
			LOG.trace("Getting locale from HTTP request header.");
			setLocale(headerValue);
		}

		if (this.locale == null) {
			LOG.warn("Locale is not set; using default {}", Locale.getDefault().toLanguageTag());
			setLocale(Locale.getDefault().toLanguageTag());
		}

		return this.locale;
	}

	/**
	 * Get the locale in custom format with under score (e.g. en_US). This method
	 * exists just for legacy reasons and should not be used in the future.
	 * 
	 * @return Custom Locale tag (e.g. en_US)
	 */
	public String getCustomLocale() {
		return getLocaleString().replaceAll("-", "_");
	}

	/**
	 * Get the locale as string. This method exists just for legacy reasons and
	 * should not be used in the future.
	 * 
	 * @return Java Locale tag (e.g. en-US)
	 */
	public String getLocaleString() {
		return getLocale().toLanguageTag();
	}

	public void setLocale(String localeString) {
		// TODO: validate if language is supported by store
		this.locale = Locale.forLanguageTag(localeString.replaceAll("_", "-"));
	}

	public void setLocale(Locale locale) {
		// TODO: validate if language is supported by store
		this.locale = locale;
	}

	private HttpServletRequest getServletRequest() {
		RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
		if (attribs instanceof ServletRequestAttributes) {
			return ((ServletRequestAttributes) attribs).getRequest();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private String getPathAttrValue(String attrName) {
		try {
			HttpServletRequest httpRequest = getServletRequest();
			if (httpRequest != null) {
				return (String) ((Map<?, ?>) httpRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE))
						.get(attrName);
			}
		} catch (Throwable e) {
			LOG.warn("No thread-bound request found. This could happen on Spring start-up");
		}
		return null;
	}

	private String getHeaderAttrValue(String attrName) {
		try {
			HttpServletRequest httpRequest = getServletRequest();
			if (httpRequest != null) {
				return httpRequest.getHeader(attrName);
			}
		} catch (Throwable e) {
			LOG.warn("No thread-bound request found. This could happen on Spring start-up");
		}
		return null;
	}

}
