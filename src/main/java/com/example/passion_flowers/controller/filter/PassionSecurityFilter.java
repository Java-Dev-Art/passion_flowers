package com.example.passion_flowers.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter(urlPatterns = "/jsp/*", initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class PassionSecurityFilter implements Filter {
    private String path;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        path = filterConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + path);
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {
        path = null;
    }
}
