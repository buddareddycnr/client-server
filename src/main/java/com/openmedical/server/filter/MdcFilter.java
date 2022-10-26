package com.openmedical.server.filter;

import org.slf4j.MDC;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 *
 */
public class MdcFilter extends HttpFilter {
    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String correlationId = request.getHeader("CorrelationId");
            if(null == correlationId || correlationId.isBlank())
                correlationId = getCorrelationId();
            MDC.put("CorrelationId", correlationId);
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("CorrelationId");
        }
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
