package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by R500 on 15.7.2014 г..
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter{
    private ServletContext context;

    public void init(FilterConfig fConfig){
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);

        if((session == null || session.getAttribute("username") == null) && (!(uri.endsWith("index.jsp") || uri.endsWith("newUser.html") || uri.endsWith("/LoginServlet")))){
            this.context.log("Unauthorized access request");
            res.sendRedirect("index.jsp");

        } else {
            chain.doFilter(request, response);
        }
}

    public void destroy(){}
}

