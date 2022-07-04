/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/AdvancedFilter.java to edit this template
 */
package Config;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nora
 */
public class LoginFilter implements Filter {

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest reqHttp = (HttpServletRequest) request;
        HttpServletResponse resHttp = (HttpServletResponse) response;

        HttpSession session = reqHttp.getSession(false);

        if (session == null) {
            resHttp.sendRedirect("/CRMSite/login.html");

        } else {
            String loggedIn = (String) session.getAttribute("isAuth");
            System.out.println(loggedIn);
            if (loggedIn != null && loggedIn.equals("true")) {
                chain.doFilter(request, response);

            } else {
                resHttp.sendRedirect("/CRMSite/login.html");
            }
        }

    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

}
