package com.finance.base.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

public class RequestLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info(getRequestMessage(request));
		filterChain.doFilter(request, response);
	}

	protected String getRequestMessage(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder("\n");
		sb.append("request:  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		sb.append("url:      ").append(request.getRequestURI()).append("\n");
		sb.append("method:   ").append(request.getMethod()).append("\n");
		sb.append("parameter:");
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			String[] values = request.getParameterValues(name);
			sb.append(name).append("=").append(Arrays.toString(values)).append("\t");
		}
		sb.append("\n");
		sb.append("request:  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
		return sb.toString();
	}

}
