package com.example.passion_flowers.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebFilter(urlPatterns = "/*",
        initParams = @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param"))
public class PassionFilter implements Filter {
    private String enCod;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        enCod = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String enCodRequest = servletRequest.getCharacterEncoding();
        if (enCod != null && !enCod.equalsIgnoreCase(enCodRequest)){
            servletRequest.setAttribute("filter_msg", "Fiter works"); //? why doesn't wor second question
            servletRequest.setCharacterEncoding(enCod);
            servletResponse.setCharacterEncoding(enCod);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
        enCod = null;
    }
}
