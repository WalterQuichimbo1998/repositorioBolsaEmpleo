/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import modelo.HojaVidaEstudiante;
import modelo.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthAdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, NullPointerException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
         HttpServletResponse res = (HttpServletResponse) response;
        if (((HttpServletRequest) request).getSession().getAttribute(AccesoBean.USER_KEY) == null) {
                res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            res.setDateHeader("Expires", 0); // Proxies.
            ((HttpServletResponse) response).sendRedirect("../index.xhtml");
        } else {
            Usuario u = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
            HojaVidaEstudiante hv = (HojaVidaEstudiante) ((HttpServletRequest) request).getSession().getAttribute("hojaVida");
            String[] ruta = httpRequest.getRequestURI().split("/");
            String r1 = ruta[2];
            if ("ESTUDIANTE".equals(u.getRol()) && !"estudiante".equals(r1) && hv != null) {
                ((HttpServletResponse) response).sendRedirect("../estudiante/Estudiante.xhtml");
            } else if ("ESTUDIANTE".equals(u.getRol()) && !"estudiante2".equals(r1) && hv == null) {
                ((HttpServletResponse) response).sendRedirect("../estudiante2/Estudiante_D.xhtml");
            } else if ("EMPLEADOR".equals(u.getRol()) && !"empleador".equals(r1)) {
                ((HttpServletResponse) response).sendRedirect("../empleador/Empleador.xhtml");
            } else if ("ADMINISTRADOR".equals(u.getRol()) && !"administrador".equals(r1)) {
                ((HttpServletResponse) response).sendRedirect("../administrador/administrador.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
