package com.snuquill.paperdx.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpServletUtils {

	private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
		MediaType.valueOf("text/*"),
		MediaType.APPLICATION_FORM_URLENCODED,
		MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_XML,
		MediaType.valueOf("application/*+json"),
		MediaType.valueOf("application/*+xml"),
		MediaType.MULTIPART_FORM_DATA
	);

	public static String extractBody(HttpServletRequest request) {
		ContentCachingRequestWrapper requestWrapper = wrapRequest(request);
		return extractBody(requestWrapper);
	}

	public static String extractBody(HttpServletResponse response) {
		ContentCachingResponseWrapper responseWrapper = wrapResponse(response);
		return extractBody(responseWrapper);
	}

	public static String extractBody(ContentCachingRequestWrapper requestWrapper) {
		byte[] content = requestWrapper.getContentAsByteArray();
		if (content.length > 0) {
			return getContent(content, requestWrapper.getContentType());
		}
		return "";
	}

	public static String extractBody(ContentCachingResponseWrapper responseWrapper) {
		byte[] content = responseWrapper.getContentAsByteArray();
		if (content.length > 0) {
			return getContent(content, responseWrapper.getContentType());
		}
		return "";
	}

	public static String extractHeader(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			stringBuilder.append(headerName).append(" : ").append(headerValue).append("\n");
		}

		return stringBuilder.toString();
	}

	public static String extractHeader(HttpServletResponse response) {
		StringBuilder stringBuilder = new StringBuilder();

		Collection<String> headerNames = response.getHeaderNames();
		for (String headerName : headerNames) {
			String headerValue = response.getHeader(headerName);
			stringBuilder.append(headerName).append(" : ").append(headerValue).append("\n");
		}

		return stringBuilder.toString();
	}

	public static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
		if (request instanceof ContentCachingRequestWrapper wrappedRequest) {
			return wrappedRequest;
		}
		return new ContentCachingRequestWrapper(request);
	}

	public static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper wrappedResponse) {
			return wrappedResponse;
		}
		return new ContentCachingResponseWrapper(response);
	}

	public static void unwrap(HttpServletResponse response) throws IOException {
		if (response instanceof ContentCachingResponseWrapper responseWrapper) {
			unwrap(responseWrapper);
		}
	}

	public static void unwrap(ContentCachingResponseWrapper responseWrapper) throws IOException {
		responseWrapper.copyBodyToResponse();
	}

	public static boolean isSuccess(HttpServletResponse response) {
		return response.getStatus() < 400;
	}

	private static String getContent(byte[] content, String contentType) {
		MediaType mediaType = MediaType.valueOf(contentType);
		boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
		if (visible) {
			return new String(content, StandardCharsets.UTF_8);
		} else {
			return String.format("[%d bytes content]", content.length);
		}
	}

}
