package com.thecodestein.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Configures the Cross Domain Availability
 */
@Slf4j
@Component
public class CorsFilterConfiguration implements Filter {

    /**
     * Adds a filter to the filter chain to add some cross domain headers on the response
     *
     * @param req   Request
     * @param res   Response
     * @param chain filter chain
     * @throws IOException      Exception occurred changing the response
     * @throws ServletException Exception occurred working on the servlet context
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        log.debug("Applying the Cross Domain filter");

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
        // do nothing
    }

    public void destroy() {
        // do nothing
    }

}